package domain.model;

import data.funcionario.Cargo;

public record Funcionario(
        int id,
        String nome,
        Departamento departamento,
        Cargo cargo
) {
    public int idDepartamento() {
        return departamento.id();
    }

    public int idFuncionario(){
        return this.id;
    }
}
