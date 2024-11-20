package domain.usecase;

import data.funcionario.FuncionarioDataSource;
import data.funcionario.FuncionarioRepository;
import domain.model.Funcionario;

public class TrocaUsuarioUseCase {
    FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    public Funcionario trocaUsuario(int id) {
        funcionarioRepository.getFuncionarioById(id);
        // return funcionario where funcionario.id = id;
        return null;
    }
}
