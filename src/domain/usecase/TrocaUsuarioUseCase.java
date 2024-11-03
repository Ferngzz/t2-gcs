package domain.usecase;

import data.funcionario.FuncionarioDataSource;
import data.funcionario.FuncionarioRepository;
import domain.model.Funcionario;

public class TrocaUsuarioUseCase {
    FuncionarioRepository funcionarioRepository = new FuncionarioRepository(new FuncionarioDataSource());

    public Funcionario trocaUsuario(int id) {
        funcionarioRepository.getFuncionario(id);
        // return funcionario where funcionario.id = id;
        return null;
    }
}
