package data.pedido;

import data.item.ItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public PedidoEntity getPedidoById(int id){
        String query = "SELECT * FROM PEDIDO p where p.id = ?";

        try{
            ResultSet result = DatabaseUtils.executeQuery(query, id);
            result.next();
                Date dataAbertura = result.getDate(2);
                LocalDate localDate = dataAbertura.toLocalDate();
                Date dataFechamento = result.getDate(3);
                LocalDate localDate2 =  dataFechamento.toLocalDate();
                int idFuncionario = result.getInt(4);
                int status = result.getInt(5);

            PedidoEntity pedido = new PedidoEntity(localDate, localDate2, idFuncionario, status);
            return pedido;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<PedidoEntity> getPedidos(){
        String query = "SELECT * FROM PEDIDO";
        List<PedidoEntity> pedidosBanco = new ArrayList<>();

        try{
            ResultSet result = DatabaseUtils.executeQuery(query);
            while(result.next()){
                Date dataAbertura = result.getDate(2);
                LocalDate localDate = dataAbertura.toLocalDate();
                Date dataFechamento = result.getDate(3);
                LocalDate localDate2 =  dataFechamento.toLocalDate();
                int idFuncionario = result.getInt(4);
                int status = result.getInt(5);
                pedidosBanco.add(new PedidoEntity(localDate, localDate2, status, idFuncionario));
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
