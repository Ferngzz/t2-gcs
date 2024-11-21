package domain.usecase;

import data.pedido.PedidoRepository;

public class ExcluirPedidoUseCase {
    private final PedidoRepository pedidoRepository;

    public ExcluirPedidoUseCase() {
        pedidoRepository = new PedidoRepository();
    }

    public boolean excluirPedido(int id) {
        pedidoRepository.deletePedido(id);
    }
}
