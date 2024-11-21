package data.pedido;

import domain.model.Pedido;
import domain.model.Departamento;
import domain.model.Funcionario;
import data.item.ItemEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;

import javax.management.RuntimeErrorException;
import db.DatabaseUtils;
//import java.util.*;
import java.sql.*;
import java.time.LocalDate;


// CRUD/Integracao do banco
public class PedidoDataSource {

    public void createPedido(PedidoEntity pedidoEntity) {
    String query = "INSERT INTO PEDIDO(data_abertura, data_fechamento, id_funcionario) VALUES (?, ?, ?)";

    try {
        DatabaseUtils.executeUpdate(query, pedidoEntity.dataAbertura(), pedidoEntity.dataFechamento(), pedidoEntity.idFuncionario());
        System.out.println("Pedido criado com sucesso!");

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao criar pedido: " + e.getMessage());
    }
}

public Pedido getPedidoById(int id){
    String query = "SELECT f.nome as 'NomeFuncionario' , d.nome as 'NomeDepartamento' , p.* FROM FUNCIONARIO f " +
            "JOIN DEPARTAMENTO d ON f.id_departamento = d.id_departamento " +
            "JOIN PEDIDO p ON f.id_funcionario = p.id_funcionario where p.id_pedido = ?";

    try{
        ResultSet result = DatabaseUtils.executeQuery(query, id);
        result.next();
           //Pedido
            int id_pedido = result.getInt("id_pedido");
            Date dataAbertura = result.getDate("data_abertura");
            LocalDate localDate = dataAbertura.toLocalDate();
            Date dataFechamento = result.getDate("data_fechamento");
            LocalDate localDate2 =  dataFechamento.toLocalDate();

            //Funcionario
            String nomeFuncionario = result.getString("NomeFuncionario");
            
           //Departamento
            String nomeDepartamento = result.getString("NomeDepartamento");

        return new Pedido(id_pedido, localDate, localDate2, nomeDepartamento, nomeFuncionario);
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


}


    public List<Pedido> getPedidos(){
        String query = "SELECT f.nome as 'NomeFuncionario' , d.nome as 'NomeDepartamento' , p.* FROM FUNCIONARIO f " +
            "JOIN DEPARTAMENTO d ON f.id_departamento = d.id_departamento " +
            "JOIN PEDIDO p ON f.id_funcionario = p.id_funcionario";
        List<Pedido> pedidosBanco = new ArrayList<>();

        try{
            ResultSet result = DatabaseUtils.executeQuery(query);
            while(result.next()){
                //Pedido
                int id_pedido = result.getInt("id_pedido");
                Date dataAbertura = result.getDate(2);
                LocalDate localDate = dataAbertura.toLocalDate();
                Date dataFechamento = result.getDate(3);
                LocalDate localDate2 =  dataFechamento.toLocalDate();

                //Funcionario
                String nomeFuncionario = result.getString("NomeFuncionario");

                //Departamento
                String nomeDepartamento = result.getString("NomeDepartamento");

                pedidosBanco.add(new Pedido(id_pedido, localDate, localDate2, nomeDepartamento , nomeFuncionario));
            }  
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosBanco;
    }

    public void updatePedido(int id, Status novoStatus){
        String updateQuery = "UPDATE PEDIDO p SET STATUS = ? WHERE p.id_pedido = ? ";

        try{
            DatabaseUtils.executeUpdate(updateQuery, novoStatus, id);
            
        } catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    public void deletePedido(int id){
        String deleteQuery = "DELETE FROM PEDIDO p WHERE P.id = ?";
        try{
        DatabaseUtils.executeUpdate(deleteQuery, id);
        System.out.println("Pedido excluído com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
