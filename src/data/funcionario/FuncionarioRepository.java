package data.funcionario;

import domain.model.Funcionario;


// Executa o CRUD
public class FuncionarioRepository {
    private final FuncionarioDataSource funcionarioDataSource;

    public FuncionarioRepository(FuncionarioDataSource funcionarioDataSource) {
        this.funcionarioDataSource = funcionarioDataSource;
    }

    public void cadastra(Funcionario funcionario) {
        var entity = new FuncionarioEntity(funcionario);
        funcionarioDataSource.cadastra(entity);
    }

    public Funcionario getFuncionario(int id){
        // GET funcionario por id

        return new Funcionario();
    }
}
