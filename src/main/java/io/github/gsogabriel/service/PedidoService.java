package io.github.gsogabriel.service;

import io.github.gsogabriel.domain.entity.Pedido;
import io.github.gsogabriel.domain.enums.StatusPedido;
import io.github.gsogabriel.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id, StatusPedido status);
}
