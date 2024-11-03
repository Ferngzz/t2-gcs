package projeto_antigo;

import data.pedido.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pedido {
    private final int id;
    private final Funcionario funcionario;
    private final Departamento departamento;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
    private final LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private Status status;
    private double valorTotal;
    private final List<Item> itens;
    private static int count= 0;
    private boolean isAberto;

    public Pedido (Funcionario funcionario) {
        this.id = count++;
        this.funcionario = funcionario;
        this.departamento = funcionario.getDepartamento();
        this.dataAbertura = LocalDate.now();
        this.valorTotal = 0;
        this.itens = new ArrayList<>();
        if (valorTotal > departamento.getLimite()) {
            this.dataFechamento = LocalDate.now();
            this.status = Status.REPROVADO;
            this.isAberto = false;
        } else {
            this.status = Status.ABERTO;
            this.isAberto = true;
        }
    }

    public void addItem(Item item, int quantidade){
        item.setValorTotal(quantidade);
        this.valorTotal += item.getValorTotal();
        for (int i=0; i < quantidade; i++){
            itens.add(item);
        }
    }

    public int getId() {
        return id;
    }


    // GET funcionario que fez o pedido
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    // GET departamento do funcionario que fez o pedido
    public Departamento getDepartamento() {
        return this.departamento;
    }

    // GET data de abertura
    public LocalDate getDataAbertura() {
        return this.dataAbertura;
    }

    // GET data de fechamento
    public LocalDate getDataFechamento() {
        return this.dataFechamento;
    }

    // GET status do pedido
    public Status getStatus() {
        return this.status;
    }

    // GET soma dos valores unitarios de cada item do pedido
    public double getValorTotal() {
        return this.valorTotal;
    }

    // GET dos itens do pedido
    public List<Item> getItens(){
        return itens;
    }

    // GET pedidos que estão Abertos / Aprovados
    public boolean isAberto() {
        return isAberto;
    }

    // UPDATE data fechamento
    public void setDataFechamento(final LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    // UPDATE status
    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setIsAberto (boolean isAberto) {
        this.isAberto = isAberto;
    }

    public String toString() {
        String dataAberturaString = dataAbertura.format(formatter);
        String dados = "ID: " + this.getId() + "\n"
                + "projeto_antigo.Funcionario: " + getFuncionario().getNome() + "\n"
                + "projeto_antigo.Departamento: " + departamento.getNome() + "\n"
                + "Data de Abertura: " + dataAberturaString + "\n"
                + "assets.Status: " + status + "\n"
                + "Valor Total: " + valorTotal + "\n";
        if (dataFechamento == null) {
            dados += "Data de Fechamento: em Aberto" + "\n";
        } else {
            String dataFechamentoString = dataFechamento.format(formatter);
            dados += "Data de Fechamento: " + dataFechamentoString + "\n";
        }
        return dados;
    }
}

// Arrumar o construtor para setar o departamento
// Verificar se o valor total do pedido ultrapassa o limite definido