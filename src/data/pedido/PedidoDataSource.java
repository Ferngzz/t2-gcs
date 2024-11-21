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

    public List<PedidoGet> getPedidosByItem(String item) {
        String query = "SELECT DISTINCT p.*, ip.*, i.*, f.nome AS 'NomeFuncionario' " +
                "FROM ITEM i JOIN ITEM_PEDIDO ip ON i.id_item = ip.id_item " +
                "JOIN PEDIDO p ON p.id_pedido = ip.id_pedido " +
                "JOIN FUNCIONARIO f ON f.id_funcionario = p.id_funcionario " +
                "WHERE i.nome LIKE ? ORDER BY ip.id_pedido  ";

        List<PedidoGet> lista = new ArrayList<>();

        try {
            ResultSet result = DatabaseUtils.executeQuery(query, item);

            while (result.next()) {
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

                lista.add(new PedidoGet(
                        id_pedido,
                        localDate,
                        localDate2,
                        nomeFuncionario,
                        status
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Nao existe pedidos com o item: " + item);
        }
        return lista;
    }


    public List<PedidoGet> getPedidos() {
        String query = "SELECT p.*, f.nome as 'NomeFuncionario' FROM FUNCIONARIO f " +
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
            DatabaseUtils.executeUpdate(updateQuery, novoStatus.toString(), id);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean deletePedido(int id) {
        String deleteQuery = "DELETE FROM PEDIDO p WHERE p.id_pedido = ?";
        try {

            if (DatabaseUtils.executeUpdate(deleteQuery, id) != 0) {
                System.out.println("Pedido excluído com sucesso!");
                return true;
            }
            return false;

        } catch (
                SQLException e) {
            System.err.println("Pedido nao encontrado pelo id: " + id);
        }
        return false;

    }
}
