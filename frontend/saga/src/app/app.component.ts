import { Component, OnDestroy } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";

import { MessageService } from "./services/message.service";
import { PedidoService } from "./services/pedido.service";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.scss"],
})
export class AppComponent implements OnDestroy {
    title = "saga";
    formGroup: FormGroup;
    private ngUnsubscribe: Subject<any> = new Subject();
    mensagens = [];

    greeting: any;
    constructor(
        public formBuilder: FormBuilder,
        public messageService: MessageService,
        public pedidoService: PedidoService
    ) {
        this.formGroup = formBuilder.group({
            clienteId: [1],
            moedasAzuis: [10],
            moedasVerdes: [20],
        });
        this.messageService.mensagemRecebidaEventEmitter.pipe(takeUntil(this.ngUnsubscribe)).subscribe((mensagem) => {
            console.log("Chegou", mensagem);
            this.mensagens.push(mensagem);
        });
        this.connect();
    }

    ngOnDestroy(): void {
        this.ngUnsubscribe.next();
        this.ngUnsubscribe.complete();
        this.disconnect();
    }

    enviarPedido() {
        console.log("Enviar pedido");
        this.pedidoService
            .enviarPedido(this.formGroup.value)
            .pipe(takeUntil(this.ngUnsubscribe))
            .subscribe((uuidPedido: string) => {
                console.log("Uuid do Pedido", uuidPedido);
            });
    }

    connect() {
        this.messageService.connect();
    }

    disconnect() {
        this.messageService.disconnect();
    }

    sendMessage() {
        this.messageService.send({
            from: "Teste",
            text: "Mensagem",
        });
    }
}
