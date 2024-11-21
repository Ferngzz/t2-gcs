package domain.model;

public record ItemGet(
        int id,
        String nome,
        double valorUnitario
) {
    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Valor Unitario: " + valorUnitario + "\n";
    }
}
