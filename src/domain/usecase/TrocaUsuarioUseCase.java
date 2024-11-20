package domain.usecase;

import data.funcionario.FuncionarioRepository;
import domain.model.Funcionario;

import java.sql.SQLException;

public class TrocaUsuarioUseCase {
    FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    public Funcionario trocaUsuario(int id) {
        try {
            return funcionarioRepository.getFuncionarioById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
