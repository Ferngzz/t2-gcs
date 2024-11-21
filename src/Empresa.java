import db.DatabaseUtils;
import domain.model.DepartamentoGet;
import domain.model.FuncionarioGet;
import domain.model.PedidoInsert;
import domain.usecase.RegistraPedidoUseCase;
import domain.usecase.TrocaUsuarioUseCase;
//import domain.usecase.InitializeUseCase;
//import domain.usecase.TrocaUsuarioUseCase;

import java.sql.SQLException;
import java.util.*;

import static data.funcionario.Cargo.*;

public class Empresa {
    private final Scanner scanner;
    private DepartamentoGet departamento;
    private FuncionarioGet usuarioAtivo;
//    private InitializeUseCase initializeUseCase;
//    private TrocaUsuarioUseCase trocaUsuarioUseCase;

    public Empresa() {
        this.scanner = new Scanner(System.in);
//        this.initializeUseCase = new InitializeUseCase(new FuncionarioRepository(new FuncionarioDataSource()));
//        this.trocaUsuarioUseCase = new TrocaUsuarioUseCase();
    }

    public void executa() {

        try {
            DatabaseUtils.openConnection();
            System.out.println("Conectou porra");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        String query = "SELECT * FROM FUNCIONARIO";
//        String queryParam = "SELECT * FROM FUNCIONARIO f WHERE f.nome = ? AND f.cargo = ?";
//
//
//        try {
//            ResultSet result = DatabaseUtils.executeQuery(queryParam, "Michael", "Administrador");
//            while (result.next()) {
//                System.out.println(
//                        "ID: " + result.getString(1) + "\n" +
//                                "Nome: " + result.getString(2) + "\n" +
//                                "Cargo: " + result.getString(3) + "\n" +
//                                "ID_Departamento: " + result.getString(4) + "\n"
//                );
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


//        try {
//            ResultSet result = DatabaseUtils.executeQueryNoParam(query);
//
//            while (result.next()) {
//                System.out.println(
//                        "ID: " + result.getString(1) + "\n" +
//                        "Nome: " + result.getString(2) + "\n" +
//                        "Cargo: " + result.getString(3) + "\n" +
//                        "ID_Departamento: " + result.getString(4) + "\n"
//                );
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        String opcao;
        boolean trocaDeUsuario = false;

        while (!trocaDeUsuario) {
            trocaDeUsuario = trocaUsuario();
        }

        while (true) {
            if (usuarioAtivo.cargo().equals(ADMINISTRADOR)) {
                menuAdministrador();
                opcao = scanner.nextLine();

                switch (opcao) {
                    case "Trocar Usuario":
                        trocaUsuario();
                        break;
                    case "Registrar Pedido":
                        registrarPedido();
                        break;
                    case "Excluir Pedido":
//                        excluirPedido();
                        break;
                    case "Avaliar Pedido":
//                        avaliarPedido();
                        break;
                    case "Listar Pedidos":
//                        listarPedidos();
                        break;
                    case "Buscar pedido Por Funcionario":
//                        buscarPedidosPorFuncionario();
                        break;
                    case "Buscar Pedido Por Descricao":
//                        buscarPedidosPorDescricao();
                        break;
                    case "Mostrar Estatisticas":
//                        mostrarEstatisticas();
                        break;
                    default:
                        break;
                }

            } else {
                menuFuncionario();
                opcao = scanner.nextLine();
                switch (opcao) {
                    case "Trocar Usuario":
                        trocaUsuario();
                        break;
                    case "Registrar Pedido":
                        registrarPedido();
                        break;
                    case "Excluir Pedido":
//                        excluirPedido();
                        break;
                    default:
                        break;
                }
            }
        }
    }


    public void menuAdministrador() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU PRINCIPAL                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Trocar Usuário                                   ║");
        System.out.println("║ 2 - Registrar Pedido                                 ║");
        System.out.println("║ 3 - Excluir Pedido                                   ║");
        System.out.println("║ 4 - Avaliar Pedido                                   ║");
        System.out.println("║ 5 - Listar Pedidos                                   ║");
        System.out.println("║ 6 - Buscar Pedidos Por Funcionario                   ║");
        System.out.println("║ 7 - Buscar Pedidos Por Descricao                     ║");
        System.out.println("║ 8 - Mostrar Estatisticas                             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    public void menuFuncionario() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU PRINCIPAL                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Trocar Usuário                                   ║");
        System.out.println("║ 2 - Registrar Pedido                                 ║");
        System.out.println("║ 3 - Exclui Pedido                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    private boolean trocaUsuario() {
        System.out.println("Digite o Codigo do usuario");
        int codigoUsuario = scanner.nextInt();
        scanner.nextLine();

        TrocaUsuarioUseCase trocaUsuarioUseCase = new TrocaUsuarioUseCase();
        FuncionarioGet newFuncionario = trocaUsuarioUseCase.trocaUsuario(codigoUsuario);

        if (newFuncionario != null) {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("            BEM VINDO " + newFuncionario.nome() + " :) ");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            usuarioAtivo = newFuncionario;

            return true;
        }

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     FALHA AO LOGAR                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        return false;
    }
    //TODO
    private boolean registrarPedido() {
        RegistraPedidoUseCase registraPedidoUseCase = new RegistraPedidoUseCase();
        return true;
    }
}
