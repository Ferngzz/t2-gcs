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

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Data de Abertura: " + dataAbertura + "\n" +
                "Data de Fechamento: " + dataFechamento + "\n" +
                "Funcionário: " + funcionario + "\n" +
                "Status: " + status + "\n";
    }

}
