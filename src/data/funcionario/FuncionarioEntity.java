package data.funcionario;

import domain.model.FuncionarioGet;

// Entidades do banco de dados
public record FuncionarioEntity(
        String nome,
        String departamento,
        Cargo cargo
) {
    public FuncionarioEntity(FuncionarioGet funcionario) {
        this(
                funcionario.nome(),
                funcionario.departamento(),
                funcionario.cargo());
    }
}
