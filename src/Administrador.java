import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;

public class Administrador extends Funcionario {

    public Administrador(String nome, Departamento departamento) {
        super(nome, departamento);
    }

    public Map<Status, List<Pedido>> getPedidos() {
        List<Pedido> pedidos = super.getDepartamento().getPedidos();
        List<Pedido> aprovados = new ArrayList<>();
        List<Pedido> reprovados = new ArrayList<>();
        Map<Status, List<Pedido>> result = new HashMap<>();

        for (Pedido p : pedidos) {
            if (p.getStatus() == Status.APROVADO) {
                aprovados.add(p);
            } else if (p.getStatus() == Status.REPROVADO) {
                reprovados.add(p);
            }
        }

        result.put(Status.APROVADO, aprovados);
        result.put(Status.REPROVADO, reprovados);
        result.put(Status.ABERTO, pedidos);

        return result;
    }

    public static List<Pedido> listarPedidosEntreDatas(List<Pedido> listaPedidos, LocalDate dataInicio, LocalDate dataFim) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (!pedido.getDataAbertura().isBefore(dataInicio) && !pedido.getDataFechamento().isAfter(dataFim)) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public static List<Pedido> buscarPedidosPorFuncionario(List<Pedido> listaPedidos, Funcionario funcionario) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getFuncionario().getNome().equalsIgnoreCase(funcionario.getNome())) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public static List<Pedido> buscarPedidosPorItem(List<Pedido> listaPedidos, String descricaoItem) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            for (Item item : pedido.getItens()) {
                if (item.getDescricao().equalsIgnoreCase(descricaoItem)) {
                    pedidosFiltrados.add(pedido);
                    break;
                }
            }
        }
        return pedidosFiltrados;
    }

    public static String visualizarPedido(Pedido pedido) {
        return pedido.toString();
    }

    public void getPedidosDoMes() {
        List<Pedido> result = new ArrayList<>();
        double valorMedio = 0;

        for (Pedido p : getDepartamento().getPedidos()) {
            if (p.getDataAbertura().isBefore(LocalDate.now().minusDays(30))) {
                result.add(p);
            }
        }

        for (Pedido p : result) {
            valorMedio += p.getValorTotal();
        }

        List<Item> itens = new ArrayList<>();
        List<String> valores = new ArrayList<>();


        for (Pedido p : result) {
            for (Item i : p.getItens()) {
                if (!itens.contains(i)) {
                    itens.add(i);
                }
            }

            for (Item item : itens) {
                double valor = 0;

                for (Item i : p.getItens()) {
                    if (item.getDescricao().equals(i.getDescricao())) {
                        valor += i.getValorTotal();
                    }
                }

                String itemValue = "Item: %s , Valor: %d ";
                String itemValueFormat = String.format(itemValue, item.getDescricao(), valor);

                valores.add(itemValueFormat);
            }
        }

        System.out.println("Pedidos dos ultimos 30 dias: ");
        System.out.println(result);
        System.out.println("Valor medio dos pedidos: " + valorMedio / result.size());
        System.out.println("Valor de cada item: " + valores);
    }

    public Pedido getPedidoMaisCaro(){
        List<Pedido> pedidos = super.getDepartamento().getPedidos();
        Pedido pedidoMaisCaro = pedidos.getFirst();

            for (Pedido p : pedidos) {
                if (p.getStatus() == Status.ABERTO &&
                        p.getValorTotal() > pedidoMaisCaro.getValorTotal()) {
                    pedidoMaisCaro = p;
                }
            }

        if (pedidoMaisCaro.getStatus() == Status.ABERTO) {
            return pedidoMaisCaro;
        } else {
            return null;
        }
    }
}
