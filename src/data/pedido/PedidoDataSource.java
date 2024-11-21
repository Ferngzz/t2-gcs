package data.pedido;

import db.DatabaseUtils;
import domain.model.PedidoGet;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// CRUD/Integracao do banco
public class PedidoDataSource {

    public int createPedido(PedidoEntity pedido, int idFuncionario) {
        String query = "INSERT INTO PEDIDO(data_abertura, data_fechamento, id_funcionario) VALUES (?, ?, ?)";

        try {
            System.out.println("Pedido criado com sucesso!");
            return DatabaseUtils.executeInsert(query, pedido.dataAbertura(), pedido.dataFechamento(), idFuncionario);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar pedido: " + e.getMessage());
        }
    }

    public PedidoGet getPedidoById(int id) {
        String query = "SELECT f.nome as 'NomeFuncionario' , d.nome as 'NomeDepartamento' , p.* FROM FUNCIONARIO f " +
                "JOIN DEPARTAMENTO d ON f.id_departamento = d.id_departamento " +
                "JOIN PEDIDO p ON f.id_funcionario = p.id_funcionario where p.id_pedido = ?";

        try {
            ResultSet result = DatabaseUtils.executeQuery(query, id);
            result.next();
            //Pedido
            int id_pedido = result.getInt("id_pedido");
            Date dataAbertura = result.getDate("data_abertura");
            LocalDate localDate = dataAbertura.toLocalDate();
            Date dataFechamento = result.getDate("data_fechamento");
            LocalDate localDate2 = dataFechamento.toLocalDate();
            //Funcionario
            String nomeFuncionario = result.getString("NomeFuncionario");

            Status status = Status.ABERTO;

            if (result.getString("Status").equals("APROVADO")) {
                status = Status.APROVADO;
            } else if (result.getString("Status").equals("FECHADO")) {
                status = Status.FECHADO;
            } else if (result.getString("Status").equals("REPROVADO")) {
                status = Status.REPROVADO;
            }

            return new PedidoGet(id_pedido, localDate, localDate2, nomeFuncionario, status);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public List<PedidoGet> getPedidos() {
        String query = "SELECT f.nome as 'NomeFuncionario' , d.nome as 'NomeDepartamento' , p.* FROM FUNCIONARIO f " +
                "JOIN DEPARTAMENTO d ON f.id_departamento = d.id_departamento " +
                "JOIN PEDIDO p ON f.id_funcionario = p.id_funcionario";
        List<PedidoGet> pedidosBanco = new ArrayList<>();

        try {
            ResultSet result = DatabaseUtils.executeQuery(query);
            while (result.next()) {
                //Pedido
                int id_pedido = result.getInt("id_pedido");
                Date dataAbertura = result.getDate(2);
                LocalDate localDate = dataAbertura.toLocalDate();
                Date dataFechamento = result.getDate(3);
                LocalDate localDate2 = dataFechamento.toLocalDate();

                //Funcionario
                String nomeFuncionario = result.getString("NomeFuncionario");

                //Departamento
                String nomeDepartamento = result.getString("NomeDepartamento");
                Status status = Status.ABERTO;

                if (result.getString("Status").equals("APROVADO")) {
                    status = Status.APROVADO;
                } else if (result.getString("Status").equals("FECHADO")) {
                    status = Status.FECHADO;
                } else if (result.getString("Status").equals("REPROVADO")) {
                    status = Status.REPROVADO;
                }

                pedidosBanco.add(new PedidoGet(id_pedido, localDate, localDate2, nomeFuncionario, status));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosBanco;
    }

    public void updatePedido(int id, Status novoStatus) {
        String updateQuery = "UPDATE PEDIDO p SET STATUS = ? WHERE p.id_pedido = ? ";

        try {
            DatabaseUtils.executeUpdate(updateQuery, novoStatus, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void deletePedido(int id) {
        String deleteQuery = "DELETE FROM PEDIDO p WHERE P.id = ?";
        try {
            DatabaseUtils.executeUpdate(deleteQuery, id);
            System.out.println("Pedido excluído com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
