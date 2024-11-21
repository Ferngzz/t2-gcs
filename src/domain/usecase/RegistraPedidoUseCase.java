package domain.usecase;

import data.pedido.PedidoEntity;
import data.pedido.PedidoRepository;
import domain.model.PedidoInsert;

public class RegistraPedidoUseCase {
    private final PedidoRepository pedidoRepository ;

    public RegistraPedidoUseCase() {
        this.pedidoRepository = new PedidoRepository();
    }

    public int registraPedido(PedidoInsert pedido, int idFuncionario){
        var entity = new PedidoEntity(pedido, idFuncionario);
        return pedidoRepository.createPedido(entity, idFuncionario);
    }

}
