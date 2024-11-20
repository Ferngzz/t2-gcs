package projeto_antigo;

import data.funcionario.FuncionarioDataSource;
import data.funcionario.FuncionarioRepository;
import db.DatabaseUtils;
//import domain.usecase.InitializeUseCase;
//import domain.usecase.TrocaUsuarioUseCase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Empresa {
    private final Scanner scanner;
    private Departamento departamento;
    private Usuario usuarioAtivo;
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

        int opcao;
        boolean trocaDeUsuario = false;

        while (!trocaDeUsuario) {
            trocaDeUsuario = trocaUsuario();
        }

        while (true) {
            if (usuarioAtivo instanceof Administrador) {
                menuAdministrador();
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        trocaUsuario();
                        break;
                    case 2:
                        registrarPedido();
                        break;
                    case 3:
                        excluiPedido();
                        break;
                    case 4:
//                        avaliaPedido();
                        break;
                    case 5:
                        listarPedidos();
                        break;
                    case 6:
//                        buscaPedidosPorFuncionario();
                        break;
                    case 7:
                        buscaPedidosPorDescricao();
                        break;
                    case 8:
//                        mostraEstatisticas();
                        break;
                    default:
                        break;
                }
            } else {
                menuFuncionario();
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        trocaUsuario();
                        break;
                    case 2:
                        registrarPedido();
                        break;
                    case 3:
                        excluiPedido();
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
        System.out.println("║ 2 - Registrar projeto_antigo.Pedido                                 ║");
        System.out.println("║ 3 - Exclui projeto_antigo.Pedido                                    ║");
        System.out.println("║ 4 - Avalia projeto_antigo.Pedido                                    ║");
        System.out.println("║ 5 - Listar Pedidos                                   ║");
        System.out.println("║ 6 - Busca Pedidos Por projeto_antigo.Funcionario                    ║");
        System.out.println("║ 7 - Busca Pedidos Por Descricao                      ║");
        System.out.println("║ 8 - Mostra Estatisticas                              ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    public void menuFuncionario() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU PRINCIPAL                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Trocar Usuário                                   ║");
        System.out.println("║ 2 - Registrar projeto_antigo.Pedido                                 ║");
        System.out.println("║ 3 - Exclui projeto_antigo.Pedido                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    private boolean trocaUsuario() {
        System.out.println("Digite o Codigo do usuario");
        int codigoUsuario = scanner.nextInt();


        if (true) {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("        BEM VINDO " + this.usuarioAtivo.getNome() + " :)");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            return true;
        }


        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     FALHA AO LOGAR                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        return false;
    }


    private void excluiPedido() {
        Funcionario funcionario = (Funcionario) usuarioAtivo;
        List<Pedido> pedidos = funcionario.getDepartamento().getPedidos();
        Pedido pedidoExcluido;
        int id = 0;
        while (true) {
            System.out.println("Digite o ID do pedido que você quer excluir");
            id = scanner.nextInt();
            if (id < 0) {
                System.out.println("Número digitado invalido!");

            } else {
                break;
            }

        }

        for (Pedido p : pedidos) {
            if (p.getId() == id && p.getStatus().equals(Status.ABERTO) && p.getFuncionario().equals(funcionario)) {
                pedidoExcluido = p;
                funcionario.getDepartamento().getPedidos().remove(pedidoExcluido);
                System.out.println("projeto_antigo.Pedido Excluído com sucesso!");
                return;
            } else {
                System.out.println("projeto_antigo.Pedido não encontrado ou não pode ser excluído!");
                return;
            }
        }
    }

//    private void avaliaPedido() {
//        System.out.println("Informe a ID do pedido: ");
//        int codigoPedido = scanner.nextInt();
//        scanner.nextLine();
//        for (Departamento d : departamentos) {
//            if (((Funcionario) usuarioAtivo).getDepartamento().equals(d)) {
//                boolean pedidoEncontrado = false;
//                for (Pedido p : d.getPedidos()) {
//                    if (p.getId() == codigoPedido && p.getStatus().equals(Status.ABERTO)) {
//                        System.out.println("projeto_antigo.Pedido " + p.getId() + " encontrado. \n" +
//                                p.toString() +
//                                "\nSelecione uma opção: ");
//                        System.out.println("1 - Aprovar pedido.");
//                        System.out.println("2 - Reprovar pedido.");
//                        System.out.println("3 - Sair");
//                        int opcao = scanner.nextInt();
//                        scanner.nextLine();
//                        switch (opcao) {
//                            case 1:
//                                p.setStatus(Status.APROVADO);
//                                System.out.println("projeto_antigo.Pedido aprovado com sucesso!");
//                                break;
//                            case 2:
//                                p.setStatus(Status.REPROVADO);
//                                p.setIsAberto(false);
//                                System.out.println("projeto_antigo.Pedido reprovado com sucesso!");
//                                break;
//                            case 3:
//                                return;
//                            default:
//                                break;
//                        }
//                        pedidoEncontrado = true;
//                        break;
//                    } else if (p.getId() == codigoPedido && !p.isAberto()) {
//                        System.out.println("projeto_antigo.Pedido " + p.getId() + " encerrado.");
//                        pedidoEncontrado = true;
//                        break;
//                    } else if (p.getId() == codigoPedido && !p.getStatus().equals(Status.ABERTO) && p.isAberto()) {
//                        System.out.println("projeto_antigo.Pedido " + p.getId() + " já avaliado.");
//                        pedidoEncontrado = true;
//                        break;
//                    }
//                }
//                if (!pedidoEncontrado) {
//                    System.out.println("projeto_antigo.Pedido não encontrado!");
//                }
//            }
//        }
//    }
//
//    private void buscaPedidosPorFuncionario() {
//        System.out.println("Digite o codigo do usuario");
//        int codigoUsuario = scanner.nextInt();
//        scanner.nextLine();
//        Funcionario usuario = null;
//        for (Usuario u : usuarios) {
//            if (u.getId() == codigoUsuario) {
//                usuario = (Funcionario) u;
//                break;
//            }
//        }
//
//        if (usuario == null) {
//            System.out.println("projeto_antigo.Usuario não encontrado");
//            return;
//        }
//
//        List<Pedido> pedidosDepartamento = usuario.getDepartamento().getPedidos();
//
//        List<Pedido> pedidoUsuario = ((Administrador) usuarioAtivo).buscarPedidosPorFuncionario(pedidosDepartamento, usuario);
//
//        for (Pedido p : pedidoUsuario) {
//            System.out.println("╔══════════════════════════════════════════════════════╗");
//            System.out.println("║                      PEDIDO                          ║");
//            System.out.println("╚══════════════════════════════════════════════════════╝");
//
//            System.out.println(p);
//        }
//    }

    private void buscaPedidosPorDescricao() {

        Administrador funcionario = (Administrador) usuarioAtivo;
        List<Pedido> pedidos = funcionario.getDepartamento().getPedidos();

        System.out.println("Insira o item que deseja buscar:");
        String descricaoItem = scanner.nextLine();

        List<Pedido> pedidosPorItem = funcionario.buscarPedidosPorItem(pedidos, descricaoItem);

        for (Pedido p : pedidosPorItem) {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                      PEDIDO                          ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            System.out.println(p);
        }
    }

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

//    private void mostraEstatisticas() {
//
//        Map<Status, List<Pedido>> pedidos = ((Administrador) usuarioAtivo).getPedidos();
//        List<Pedido> aprovados = pedidos.get(Status.APROVADO);
//        List<Pedido> reprovados = pedidos.get(Status.REPROVADO);
//        List<Pedido> total = pedidos.get(Status.ABERTO);
//        List<Pedido> pedidosDoMes = ((Administrador) usuarioAtivo).getPedidosDoMes();
//        List<String> valorTotalCadaItem = ((Administrador) usuarioAtivo).getValorCadaItem();
//
//        double valorTotal = 0;
//
//        for (Pedido p : pedidosDoMes) {
//            valorTotal += p.getValorTotal();
//        }
//
//        double valorMedio = valorTotal / pedidosDoMes.size();
//
//        Pedido pedidoMaisCaro = ((Administrador) usuarioAtivo).getPedidoMaisCaro();
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║                     ESTATISTICAS                     ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║                   PEDIDOS APROVADO                   ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//        for (Pedido pedidoAprovado : aprovados) {
//            System.out.println(pedidoAprovado);
//        }
//        double aprovadosPercent = ((double) aprovados.size() / total.size()) * 100;
//        System.out.println("Aprovados: " + aprovadosPercent + "%");
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║                   PEDIDOS REPROVADO                  ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//        for (Pedido pedidoReprovado : reprovados) {
//            System.out.println(pedidoReprovado);
//        }
//        double reprovadosPercent = ((double) reprovados.size() / total.size()) * 100;
//        System.out.println("Reprovados: " + reprovadosPercent + "%");
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║                PEDIDO DE MAIOR VALOR                 ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//        if (pedidoMaisCaro == null) {
//            System.out.println("Não há pedido");
//        } else {
//            System.out.println(pedidoMaisCaro);
//        }
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║                    PEDIDOS DO MES                    ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//        for (Pedido pedidoDoMes : pedidosDoMes) {
//            System.out.println(pedidoDoMes);
//        }
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║              VALOR MEDIO DOS PEDIDOS                 ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//        System.out.println(valorMedio);
//
//        System.out.println("╔══════════════════════════════════════════════════════╗");
//        System.out.println("║                  VALOR DE CADA ITEM                  ║");
//        System.out.println("╚══════════════════════════════════════════════════════╝");
//        for (String valorCadaItem : valorTotalCadaItem) {
//            System.out.println(valorCadaItem);
//        }
//
//    }

    private void registrarPedido() {
        Funcionario funcionario = (Funcionario) usuarioAtivo;
        Pedido pedido = new Pedido(funcionario);

        while (true) {
            System.out.println("Insira um item no pedido");
            System.out.println("Descricao do item (nome):");
            String descricao = scanner.nextLine();
            System.out.println("Valor unitario");
            double valorUnitario = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Quantidade deste item");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            Item item = new Item(descricao, valorUnitario);

            pedido.addItem(item, quantidade);

            System.out.println("Deseja continuar? Se sim, digite qualquer coisa. Se nao, digite -1");
            if (scanner.nextLine().equalsIgnoreCase("-1")) {
                break;
            }
        }

        funcionario.getDepartamento().getPedidos().add(pedido);
    }

}
