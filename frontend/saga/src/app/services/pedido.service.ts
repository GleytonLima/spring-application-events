import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { environment } from "../../environments/environment";
import { Pedido } from "../model/model";

@Injectable({
    providedIn: "root",
})
export class PedidoService {
    constructor(private http: HttpClient) {}

    enviarPedido(pedido: Pedido): Observable<string> {
        return this.http.post<string>(environment.urlPedido, pedido);
    }
}
