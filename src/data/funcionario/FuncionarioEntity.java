package data.funcionario;

import domain.model.Funcionario;

// Entidades do banco de dados
public record FuncionarioEntity(
        int id,
        String nome,
        int idDepartamento,
        Cargo cargo
) {
    public FuncionarioEntity(Funcionario funcionario) {
        this(
                funcionario.id(),
                funcionario.nome(),
                funcionario.idDepartamento(),
                funcionario.cargo());
    }
}
