package data.funcionario;

import db.DatabaseUtils;
import domain.model.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// CRUD/Integracao do banco
public class FuncionarioDataSource {

    public void createFuncionario(FuncionarioEntity funcionarioEntity) throws SQLException {
        String createQuery = "INSERT INTO FUNCIONARIO (nome, cargo, id_departamento) VALUES (?, ?, ?)";

        DatabaseUtils.executeUpdate(createQuery,
                funcionarioEntity.nome(),
                funcionarioEntity.cargo(),
                funcionarioEntity.departamento());
    }

    public Funcionario getFuncionarioById(int id) throws SQLException {

        String getFuncionarioByIdQuery = "SELECT f.*, d.nome FROM FUNCIONARIO f " +
                "JOIN DEPARTAMENTO d ON f.id_departamento = d.id_departamento " +
                "WHERE f.id_funcionario=?";

        ResultSet resultFuncionario = DatabaseUtils.executeQuery(getFuncionarioByIdQuery, id);

        Cargo cargo;

        if (resultFuncionario.getString(4).equals("Funcionario")) {
            cargo = Cargo.FUNCIONARIO;
        } else {
            cargo = Cargo.ADMINISTRADOR;
        }

        return new Funcionario(
                resultFuncionario.getInt(1),
                resultFuncionario.getString(2),
                resultFuncionario.getString(5),
                cargo
        );
    }

    public List<Funcionario> getFuncionarios() throws SQLException {

        String getFuncionariosQuery = "SELECT f.*, d.nome FROM FUNCIONARIO f " +
                "JOIN DEPARTAMENTO d ON f.id_departamento = d.id_departamento ";
        ResultSet resultFuncionario = DatabaseUtils.executeQuery(getFuncionariosQuery);

        List<Funcionario> funcionarios = new ArrayList<>();

        while (resultFuncionario.next()) {
            Cargo cargo;

            if (resultFuncionario.getString(4).equals("Funcionario")) {
                cargo = Cargo.FUNCIONARIO;
            } else {
                cargo = Cargo.ADMINISTRADOR;
            }

            funcionarios.add(new Funcionario(
                    resultFuncionario.getInt(1),
                    resultFuncionario.getString(2),
                    resultFuncionario.getString(5),
                    cargo
            ));
        }

        return funcionarios;
    }

    public void deleteFuncionario(int id) throws SQLException {
        String deleteQuery = "DELETE FROM FUNCIONARIO WHERE id_funcionario=?";

        ResultSet resultFuncionario = DatabaseUtils.executeQuery(deleteQuery, id);

    }
}
