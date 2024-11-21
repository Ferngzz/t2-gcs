package data.departamento;

import domain.model.DepartamentoGet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Executa o CRUD
public class DepartamentoRepository {
    DepartamentoDataSource dapartamentoDataSource;
    public DepartamentoRepository() {
       this.dapartamentoDataSource = new DepartamentoDataSource();
    }

    public void createDepartamento(DepartamentoGet departamento) throws SQLException {
        DepartamentoEntity departamentoEntity = new DepartamentoEntity(departamento);
        dapartamentoDataSource.createDepartamento(departamentoEntity);
    }

    public DepartamentoGet getDepartamentoById(int id) throws SQLException {
        DepartamentoEntity departamentoEntity = dapartamentoDataSource.getDepartamentoById(id);

        return new DepartamentoGet(departamentoEntity.idDepartamento(),departamentoEntity.nome(),departamentoEntity.limite());
    }

    public List<DepartamentoGet> getAllDepartamentos() throws SQLException {
        List<DepartamentoEntity> departamentos = dapartamentoDataSource.getDepartamentos();
        List<DepartamentoGet> models = new ArrayList<>();

        for (DepartamentoEntity departamentoEntity : departamentos) {
            models.add(new DepartamentoGet(departamentoEntity.idDepartamento(),departamentoEntity.nome(),departamentoEntity.limite()));
        }
        return models;
    }
}
