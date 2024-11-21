package domain.model;

import data.pedido.Status;

import java.time.LocalDate;

public record PedidoGet(
        int id,
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        String funcionario,
        Status status
) {
}
