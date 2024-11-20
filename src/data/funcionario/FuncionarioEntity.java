package data.funcionario;

import domain.model.Funcionario;

// Entidades do banco de dados
public record FuncionarioEntity(
        String nome,
        String departamento,
        Cargo cargo
) {
    public FuncionarioEntity(Funcionario funcionario) {
        this(
                funcionario.nome(),
                funcionario.departamento(),
                funcionario.cargo());
    }
}
