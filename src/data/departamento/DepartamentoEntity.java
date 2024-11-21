package data.departamento;

import domain.model.Departamento;

public record DepartamentoEntity(
        String nome,
        double limite
){
    public DepartamentoEntity(Departamento departamento) {
        this(
                departamento.nome(),
                departamento.limite()
        );
    }
}

