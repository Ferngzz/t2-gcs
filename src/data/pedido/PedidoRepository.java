package data.pedido;

import domain.model.PedidoGet;
import domain.model.PedidoInsert;

import java.util.List;

// Executa o CRUD
public class PedidoRepository {
    PedidoDataSource pedidoDataSource = new PedidoDataSource();

    public int createPedido(PedidoInsert pedidoInsert, int idFuncionario) {
        var pedido = new PedidoEntity(pedidoInsert, idFuncionario);
        return pedidoDataSource.createPedido(pedido, idFuncionario);
    }

    public void deletePedido(int id) {
        pedidoDataSource.deletePedido(id);
    }

    public void updatePedido(int id, Status status) {
        pedidoDataSource.updatePedido(id, status);
    }

    public List<PedidoGet> getPedidos() {
        return pedidoDataSource.getPedidos();
    }

    public PedidoGet getPedido(int id) {
        return pedidoDataSource.getPedidoById(id);
    }

}
