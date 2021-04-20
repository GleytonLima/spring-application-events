package com.maolabs.etapas.pedido.external.http;

import com.maolabs.etapas.pedido.external.http.dto.input.PedidoInput;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.CriarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;

    @PostMapping("/pedidos")
    ResponseEntity criarPedido(@RequestBody PedidoInput pedido) {
        criarPedidoUseCase.criarPedido(pedido.build(), UUID.randomUUID());
        return ResponseEntity.ok().build();
    }
}