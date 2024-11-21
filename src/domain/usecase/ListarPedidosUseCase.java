package domain.usecase;

import data.pedido.PedidoRepository;
import domain.model.PedidoGet;

import java.util.List;

public class ListarPedidosUseCase {
    private final PedidoRepository pedidoRepository;

    public ListarPedidosUseCase() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void listarPedidos() {
      List<PedidoGet> pedidos = pedidoRepository.getPedidos();

      pedidos.forEach(System.out::println);
    }
}
