package io.github.gsogabriel.rest.controller;

import io.github.gsogabriel.domain.entity.ItemPedido;
import io.github.gsogabriel.domain.entity.Pedido;
import io.github.gsogabriel.domain.enums.StatusPedido;
import io.github.gsogabriel.exception.RegraNegocioException;
import io.github.gsogabriel.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.gsogabriel.rest.dto.InformacoesItemPedidoDTO;
import io.github.gsogabriel.rest.dto.InformacoesPedidoDTO;
import io.github.gsogabriel.rest.dto.PedidoDTO;
import io.github.gsogabriel.service.PedidoService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService.obterPedidoCompleto(id)
                .map( p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizarPedido( @PathVariable Integer id,
                                 @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizarStatus(id, StatusPedido.valueOf(novoStatus) );

    }

    private InformacoesPedidoDTO converter(Pedido p) {
        return InformacoesPedidoDTO.builder()
                .codigo(p.getId())
                .dataPedido(p.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(p.getCliente().getCpf())
                .nomeCliente(p.getCliente().getNome())
                .total(p.getTotal())
                .status(p.getStatus().name())
                .itens(converter(p.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(item ->
            InformacoesItemPedidoDTO
                    .builder()
                    .descricaoProduto(item.getProduto().getDescricao())
                    .precoUnitario(item.getProduto().getPreco())
                    .quantidade(item.getQuantidade())
                    .build()
        ).collect(Collectors.toList());
    }

}
