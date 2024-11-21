package data.departamento;

import domain.model.DepartamentoGet;

public record DepartamentoEntity(
        String nome,
        double limite
){
    public DepartamentoEntity(DepartamentoGet departamento) {
        this(
                departamento.nome(),
                departamento.limite()
        );
    }
}

