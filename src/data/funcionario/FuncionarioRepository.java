package data.funcionario;

import domain.model.FuncionarioGet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Executa o CRUD
public class FuncionarioRepository {
    private final FuncionarioDataSource funcionarioDataSource;

    public FuncionarioRepository() {
        this.funcionarioDataSource = new FuncionarioDataSource();
    }

    public void cadastraFuncionario(FuncionarioGet funcionario) throws SQLException {
        var entity = new FuncionarioEntity(funcionario);
        funcionarioDataSource.createFuncionario(entity);
    }

    // GET funcionario por id
    public FuncionarioGet getFuncionarioById(int id) throws SQLException{
        FuncionarioGet entity = funcionarioDataSource.getFuncionarioById(id);

        return new FuncionarioGet(
                id,
                entity.nome(),
                entity.departamento(),
                entity.cargo()
        );


    }

    public List<FuncionarioGet> getAllFuncionarios() throws SQLException{
        List<FuncionarioGet> entities = funcionarioDataSource.getFuncionarios();
        List<FuncionarioGet> funcionarioModels = new ArrayList<>();

        entities.forEach(entity ->
                funcionarioModels.add(new FuncionarioGet(
                entity.id(),
                entity.nome(),
                entity.departamento(),
                entity.cargo()
        )));
        return funcionarioModels;
    }

    public void deleteFuncionario(int id) throws SQLException {
        funcionarioDataSource.deleteFuncionario(id);
    }

}
