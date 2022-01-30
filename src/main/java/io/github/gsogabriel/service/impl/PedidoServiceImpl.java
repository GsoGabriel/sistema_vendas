package io.github.gsogabriel.service.impl;

import io.github.gsogabriel.domain.entity.Cliente;
import io.github.gsogabriel.domain.entity.ItemPedido;
import io.github.gsogabriel.domain.entity.Pedido;
import io.github.gsogabriel.domain.entity.Produto;
import io.github.gsogabriel.domain.enums.StatusPedido;
import io.github.gsogabriel.domain.repository.ClientesRepository;
import io.github.gsogabriel.domain.repository.ItemPedidoRepository;
import io.github.gsogabriel.domain.repository.PedidosRepository;
import io.github.gsogabriel.domain.repository.ProdutosRepository;
import io.github.gsogabriel.exception.PedidoNaoEncontradoException;
import io.github.gsogabriel.exception.RegraNegocioException;
import io.github.gsogabriel.rest.dto.ItemPedidoDTO;
import io.github.gsogabriel.rest.dto.PedidoDTO;
import io.github.gsogabriel.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // instancia todos os campos que são necessários serem isntanciados (os que possuem final)
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidoList = salvarItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidoList);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    public void atualizarStatus(Integer id, StatusPedido status) {
        pedidosRepository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(status);
                    return pedidosRepository.save(pedido);
                } ).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> salvarItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens.stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setProduto(produto);
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
