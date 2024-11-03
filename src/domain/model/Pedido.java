package domain.model;

import java.time.LocalDate;

public record Pedido(
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        Departamento departamento,
        Funcionario funcionario
) {
    public int idDepartamento(Departamento departamento){
        return departamento.id();
    }
    public int idFuncionario(Funcionario funcionario){
        return funcionario.id();
    }
}
