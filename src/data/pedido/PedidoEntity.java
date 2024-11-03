package data.pedido;


import java.time.LocalDate;

public record PedidoEntity(
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        int idDepartamento,
        int idFuncionario
) {
}
