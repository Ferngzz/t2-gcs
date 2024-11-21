package domain.model;

import data.funcionario.Cargo;

public record FuncionarioInsert(
        String nome,
        String departamento,
        Cargo cargo
) {
}
