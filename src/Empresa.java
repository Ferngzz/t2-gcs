import db.DatabaseUtils;
import domain.model.FuncionarioGet;
import domain.usecase.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static data.funcionario.Cargo.ADMINISTRADOR;

public class Empresa {
    private final Scanner scanner;
    private FuncionarioGet usuarioAtivo;

    public Empresa() {
        this.scanner = new Scanner(System.in);
    }

    public void executa() {
        try {
            DatabaseUtils.openConnection();
            System.out.println("Conectou com o banco");
        } catch (SQLException e) {
            System.err.println("Falha na conexao com o banco");
        }

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
                    case "1":
                        trocaUsuario();
                        break;
                    case "2":
                        registrarPedido();
                        break;
                    case "3":
                        excluirPedido();
                        break;
                    case "4":
                        avaliarPedido();
                        break;
                    case "5":
                        listarPedidos();
                        break;
                    case "6":
                        listarPedidosPorItem();
                        break;
                    case "7":
                        listarPedidosComItem();
                        break;
                    case "8":
                        listarItens();
                        break;
                    default:
                        break;
                }

            } else {
                menuFuncionario();
                opcao = scanner.nextLine();
                switch (opcao) {
                    case "1":
                        trocaUsuario();
                        break;
                    case "2":
                        registrarPedido();
                        break;
                    case "3":
                        excluirPedido();
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
        System.out.println("║ 6 - Listar Pedidos Por Item                          ║");
        System.out.println("║ 7 - Listar Pedidos Com Item                          ║");
        System.out.println("║ 8 - Listar Itens                                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: \n");
    }

    public void menuFuncionario() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU PRINCIPAL                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Trocar Usuário                                   ║");
        System.out.println("║ 2 - Registrar Pedido                                 ║");
        System.out.println("║ 3 - Exclui Pedido                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: \n");
    }

    private boolean trocaUsuario() {
        System.out.println("Digite o Codigo do usuario");
        try {

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

        } catch (InputMismatchException e) {
            System.err.println("ID deve ser um numero inteiro \n");
            scanner.nextLine();
            return false;
        }

    }

    //TODO
    private boolean registrarPedido() {
        RegistraPedidoUseCase registraPedidoUseCase = new RegistraPedidoUseCase();
        return true;
    }

    private void excluirPedido() {
        ExcluirPedidoUseCase excluirPedidoUseCase = new ExcluirPedidoUseCase();
        try {
            System.out.println("Digite o codigo do Pedido");
            int codigo = scanner.nextInt();
            scanner.nextLine();

            excluirPedidoUseCase.excluirPedido(codigo);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("Codigo deve ser um numero inteiro");
        }
    }

    private void avaliarPedido(){
        AvaliarPedidoUseCase avaliarPedidoUseCase = new AvaliarPedidoUseCase();
        try {
            System.out.println("Digite o codigo do Pedido");
            int codigo = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite o status do pedido");
            String status = scanner.nextLine();

            avaliarPedidoUseCase.avaliarPedido(codigo, status);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("Codigo deve ser um numero inteiro");
        }
    }

    private void listarPedidos(){
        ListarPedidosUseCase listarPedidosUseCase = new ListarPedidosUseCase();
        listarPedidosUseCase.listarPedidos();
    }

    private void listarPedidosPorItem(){
        ListarPedidosPorItemUseCase listarPedidosPorItemUseCase = new ListarPedidosPorItemUseCase();

        System.out.println("Digite o nome do item");
        String item = scanner.nextLine();

        listarPedidosPorItemUseCase.listarPedidosPorItem(item);
    }

    private void listarItens(){
        ListarItensUseCase listarItensUseCase = new ListarItensUseCase();
        listarItensUseCase.listarItens();
    }
    private void listarPedidosComItem(){
        ListarPedidosComItemUseCase listarPedidosComItemUseCase = new ListarPedidosComItemUseCase();
        listarPedidosComItemUseCase.listarPedidosComItem();

    }
}
