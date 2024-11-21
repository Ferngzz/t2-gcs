package domain.model;

import java.time.LocalDate;

public record PedidoInsert(
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        String funcionario
) {
}
