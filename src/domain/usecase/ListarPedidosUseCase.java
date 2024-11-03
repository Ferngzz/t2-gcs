package domain.usecase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListarPedidosUseCase {
    public void listarPedidos() {
        String dataDeInicio;
        String dataFinal;
        departamento = ((Administrador) usuarioAtivo).getDepartamento();
        List<Pedido> pedidos = departamento.getPedidos();

        System.out.println("Digite a data inicial:(Formato : dia/mes/ano ) ");
        dataDeInicio = scanner.nextLine();
        System.out.println("Digite a data final: (Formato : dia/mes/ano )");
        dataFinal = scanner.nextLine();

        //Definindo a formatação da data
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Convertendo de String para LocalDate
        LocalDate dataIni = LocalDate.parse(dataDeInicio, formatacao);
        LocalDate dataFim = LocalDate.parse(dataFinal, formatacao);

        for (Pedido p : Administrador.listarPedidosEntreDatas(pedidos, dataIni, dataFim)) {
            System.out.println(p);
        }
    }
}
