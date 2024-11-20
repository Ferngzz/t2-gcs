package data.departamento;

import domain.model.Departamento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Executa o CRUD
public class DepartamentoRepository {
    DepartamentoDataSource dapartamentoDataSource;
    public DepartamentoRepository() {
       this.dapartamentoDataSource = new DepartamentoDataSource();
    }

    public void createDepartamento(Departamento departamento) throws SQLException {
        DepartamentoEntity departamentoEntity = new DepartamentoEntity(departamento);
        dapartamentoDataSource.createDepartamento(departamentoEntity);
    }

    public Departamento getDepartamentoById(int id) throws SQLException {
        DepartamentoEntity departamentoEntity = dapartamentoDataSource.getDepartamentoById(id);

        Departamento model = new Departamento(departamentoEntity.idDepartamento(),departamentoEntity.nome(),departamentoEntity.limite());
        return model;
    }

    public List<Departamento> getAllDepartamentos() throws SQLException {
        List<DepartamentoEntity> departamentos = dapartamentoDataSource.getDepartamentos();
        List<Departamento> models = new ArrayList<>();

        for (DepartamentoEntity departamentoEntity : departamentos) {
            models.add(new Departamento(departamentoEntity.idDepartamento(),departamentoEntity.nome(),departamentoEntity.limite()));
        }
        return models;
    }
}
