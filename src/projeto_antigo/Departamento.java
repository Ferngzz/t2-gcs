package projeto_antigo;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private final String nome;
    private final List<Funcionario> funcionarios;
    private final List<Pedido> pedidos;
    private final double limite; // Valor limitante do pedido

    public Departamento(String nome, double limite){
        this.funcionarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.nome = nome;
        this.limite = limite;
    }

    public String getNome(){
        return nome;
    }

    public double getLimite(){
        return limite;
    }

    // INSERT funcionario
    public boolean cadastraFuncionario(Funcionario funcionario){
        return funcionarios.add(funcionario);   
    }

    // GET ALL Pedidos que são de usuarios do departamento
    public List<Pedido> getPedidos(){
        return pedidos;
    }

    // GET ALL Funcionarios
    public List<Funcionario> getFuncionarios(){
        return funcionarios;
    }
    
}
