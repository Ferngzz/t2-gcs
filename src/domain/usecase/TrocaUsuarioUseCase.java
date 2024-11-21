package domain.usecase;

import data.funcionario.FuncionarioRepository;
import domain.model.FuncionarioGet;

import java.sql.SQLException;

public class TrocaUsuarioUseCase {
    private final FuncionarioRepository funcionarioRepository;

    public TrocaUsuarioUseCase() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    public FuncionarioGet trocaUsuario(int id) {
        try {
            return funcionarioRepository.getFuncionarioById(id);
        } catch (SQLException e) {
            System.err.println("Usuario nao encontrado com o id informado: " + id);
        }
        return null;
    }
}
