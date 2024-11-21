package data.departamento;

import domain.model.DepartamentoGet;
import domain.model.DepartamentoInsert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Executa o CRUD
public class DepartamentoRepository {
    DepartamentoDataSource dapartamentoDataSource;
    public DepartamentoRepository() {
       this.dapartamentoDataSource = new DepartamentoDataSource();
    }

    public void createDepartamento(DepartamentoInsert departamento) throws SQLException {

        DepartamentoEntity departamentoinsert = new DepartamentoEntity(departamento);
        dapartamentoDataSource.createDepartamento(departamentoinsert);
    }

    public DepartamentoGet getDepartamentoById(int id) throws SQLException {
        return dapartamentoDataSource.getDepartamentoById(id);
    }

    public List<DepartamentoGet> getAllDepartamentos() throws SQLException {
        return dapartamentoDataSource.getDepartamentos();
    }
}
