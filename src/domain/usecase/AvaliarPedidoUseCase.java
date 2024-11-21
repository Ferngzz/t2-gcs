package domain.usecase;

import data.pedido.PedidoRepository;
import data.pedido.Status;

public class AvaliarPedidoUseCase {
    private final PedidoRepository pedidoRepository;

    public AvaliarPedidoUseCase() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void avaliarPedido(int id, String s) {
        Status status = switch (s) {
            case "Aprovado" -> Status.APROVADO;
            case "Reprovado" -> Status.REPROVADO;
            case "Fechado" -> Status.FECHADO;
            default -> Status.ABERTO;
        };

        pedidoRepository.updatePedido(id, status);
    }

}
