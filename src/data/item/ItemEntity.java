package data.item;

import domain.model.ItemInsert;

public record ItemEntity(
        String nome,
        double valorUnitario
) {
    public ItemEntity(ItemInsert item) {
        this(
                item.nome(),
                item.valorUnitario()
        );
    }
}