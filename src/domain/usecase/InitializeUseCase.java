package domain.usecase;
import data.funcionario.Cargo;
import data.funcionario.FuncionarioRepository;
import domain.model.Departamento;
import domain.model.Funcionario;

import java.util.Random;


// Regras de negócio
public class InitializeUseCase {
    private final FuncionarioRepository funcionarioRepository;

    public InitializeUseCase(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void initialize(){
        Random geraNumeros = new Random();
        String[] nomesPossiveis = {
                "Ana", "Carlos", "Fernanda", "João", "Mariana", "Ricardo", "Sofia", "Lucas", "Bruno", "Juliana",
                "Gabriel", "Laura", "Mateus", "Isabela", "Pedro", "Clara", "Felipe", "Renata", "Vinícius", "Tatiane",
                "Roberto", "Amanda", "Thiago", "Camila", "Júlio", "Rafael", "Bianca", "André", "Lúcia", "Gustavo",
                "Julio", "Luciana", "Érica", "Murilo", "Natália", "Alberto", "Priscila", "Leonardo", "Nathalia", "Cíntia"
        };

        int countNames = nomesPossiveis.length;

        for (int j = 1; j <= 5; j++) {
            for (int i = 0; i < 2; i++) {
                int indexRandom = geraNumeros.nextInt(countNames);
                String nomeFuncionario = nomesPossiveis[indexRandom];
                Funcionario funcionario = new Funcionario(nomeFuncionario, new Departamento(j), Cargo.FUNCIONARIO);
                funcionarioRepository.cadastra(funcionario);
            }
        }
    }
}
