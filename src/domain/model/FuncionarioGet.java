package domain.model;

import data.funcionario.Cargo;

public record FuncionarioGet(
        int id,
        String nome,
        String departamento,
        Cargo cargo
) {
}
