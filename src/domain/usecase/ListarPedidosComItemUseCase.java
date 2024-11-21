package domain.usecase;

import data.pedido.PedidoRepository;
import db.DatabaseUtils;
import domain.model.PedidoGet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarPedidosComItemUseCase {
    private final PedidoRepository pedidoRepository;

    public ListarPedidosComItemUseCase() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void listarPedidosComItem() {
        String query = "SELECT ip.id_pedido, i.nome FROM ITEM_PEDIDO ip " +
                "JOIN ITEM i ON ip.id_item = i.id_item ORDER BY ip.id_pedido";

        try {
            ResultSet result = DatabaseUtils.executeQueryNoParam(query);
            Map<Integer, ArrayList<String>> pedidos = new HashMap<>();

            while (result.next()) {
                int idPedido = result.getInt("id_pedido");
                String nomeItem = result.getString("nome");

                pedidos.computeIfAbsent(idPedido, _ -> new ArrayList<>()).add(nomeItem);
            }

            pedidos.forEach((idPedido, nomesItens) -> {
                System.out.println("ID Pedido: " + idPedido);
                System.out.println("Itens: " + String.join(", ", nomesItens) + "\n");
            });

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Nao foram encontrados pedidos");
        }


    }
}
