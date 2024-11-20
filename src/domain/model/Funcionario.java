package domain.model;

import data.funcionario.Cargo;

public record Funcionario(
        int id,
        String nome,
        int idDepartamento,
        Cargo cargo
) {

    public int idFuncionario(){
        return this.id;
    }
}
