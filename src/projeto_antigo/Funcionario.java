package projeto_antigo;

public class Funcionario extends Usuario{
    private final Departamento departamento;
    public Funcionario(String nome, Departamento departamento) {
        super(nome);
        this.departamento = departamento;
    }

    // GET departamento
    public Departamento getDepartamento() {
        return departamento;
    }
}
