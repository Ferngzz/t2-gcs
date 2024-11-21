package domain.usecase;

import data.pedido.PedidoEntity;
import data.pedido.PedidoRepository;
import domain.model.PedidoInsert;

public class RegistraPedidoUseCase {
    PedidoRepository pedidoRepository = new PedidoRepository();;

    public boolean registraPedido(PedidoInsert pedido, int idFuncionario){
        var entity = new PedidoEntity(pedido);
        return pedidoRepository.createPedido(entity, idFuncionario);
    }


}
