package data.pedido;

import domain.model.PedidoInsert;

// Executa o CRUD
public class PedidoRepository {
    PedidoDataSource pedidoDataSource = new PedidoDataSource();

    public boolean createPedido(PedidoEntity pedido, int idFuncionario) {
        return pedidoDataSource.createPedido(pedido, idFuncionario) != -1;
    }
}
