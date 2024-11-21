package data.item;

import domain.model.Item;

public record ItemEntity(
        String nome,
        double valorUnitario
) {
    public ItemEntity(Item item) {
        this(
                item.nome(),
                item.valorUnitario()
        );
    }
}