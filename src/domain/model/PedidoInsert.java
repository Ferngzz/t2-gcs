package domain.model;

import projeto_antigo.Funcionario;

import java.time.LocalDate;

public record PedidoInsert(
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        Funcionario funcionario
) {
}
