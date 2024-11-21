package domain.model;

import data.funcionario.Cargo;

public record Funcionario(
        int id,
        String nome,
        String departamento,
        Cargo cargo
) {

    public int idFuncionario() {
        return this.id;
    }
}
