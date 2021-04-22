package com.maolabs.etapas.pedido.external.http;

import com.maolabs.etapas.pedido.external.http.dto.input.PedidoInput;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoCriarUseCase;
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

    private final PedidoCriarUseCase pedidoCriarUseCase;

    @PostMapping("/pedidos")
    ResponseEntity criarPedido(@RequestBody PedidoInput pedido) {
        var uuidPedido = UUID.randomUUID();
        pedidoCriarUseCase.criarPedido(pedido.build(), uuidPedido);
        return ResponseEntity.ok(uuidPedido);
    }
}