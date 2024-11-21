package domain.usecase;

import data.pedido.PedidoRepository;
import domain.model.PedidoGet;

import java.util.List;

public class ListarPedidosPorItemUseCase {
    private final PedidoRepository pedidoRepository;

    public ListarPedidosPorItemUseCase() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void listarPedidosPorItem(String item) {
        List<PedidoGet> pedidos = pedidoRepository.getPedidosByItem(item);

        pedidos.forEach(System.out::println);
    }
}
