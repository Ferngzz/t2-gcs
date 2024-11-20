package data.funcionario;

import domain.model.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Executa o CRUD
public class FuncionarioRepository {
    private final FuncionarioDataSource funcionarioDataSource;

    public FuncionarioRepository(FuncionarioDataSource funcionarioDataSource) {
        this.funcionarioDataSource = funcionarioDataSource;
    }

    public void cadastraFuncionario(Funcionario funcionario) throws SQLException {
        var entity = new FuncionarioEntity(funcionario);
        funcionarioDataSource.createFuncionario(entity);
    }

    // GET funcionario por id
    public Funcionario getFuncionarioById(int id) throws SQLException{
        FuncionarioEntity entity = funcionarioDataSource.getFuncionarioById(id);

        Funcionario model = new Funcionario(
                id,
                entity.nome(),
                entity.idDepartamento(),
                entity.cargo()
        );


        return model;
    }

    public List<Funcionario> getAllFuncionarios() throws SQLException{
        List<FuncionarioEntity> entities = funcionarioDataSource.getFuncionarios();
        List<Funcionario> funcionarioModels = new ArrayList<>();

        entities.forEach(entity -> {
            funcionarioModels.add(new Funcionario(
                    entity.id(),
                    entity.nome(),
                    entity.idDepartamento(),
                    entity.cargo()
            ));
        });
        return funcionarioModels;
    }

    public void deleteFuncionario(int id) throws SQLException {
        funcionarioDataSource.deleteFuncionario(id);
    }

}
