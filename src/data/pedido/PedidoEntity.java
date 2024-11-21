package data.pedido;

import domain.model.PedidoInsert;

import java.time.LocalDate;

public record PedidoEntity(
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        int idFuncionario
) {
    public PedidoEntity(PedidoInsert pedido, int idFuncionario) {
        this(
                pedido.dataAbertura(),
                pedido.dataFechamento(),
                idFuncionario
        );
    }
}
