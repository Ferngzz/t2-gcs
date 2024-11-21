package data.departamento;

import domain.model.DepartamentoGet;
import domain.model.DepartamentoInsert;

public record DepartamentoEntity(
        String nome,
        double limite
){
    public DepartamentoEntity(DepartamentoInsert departamento) {
        this(
                departamento.nome(),
                departamento.limite()
        );
    }
}

