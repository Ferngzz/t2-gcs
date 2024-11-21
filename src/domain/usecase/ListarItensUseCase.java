package domain.usecase;

import data.item.ItemRepository;
import domain.model.ItemGet;

import java.sql.SQLException;
import java.util.List;

public class ListarItensUseCase {
    private final ItemRepository itemRepository;

    public ListarItensUseCase() {
        this.itemRepository = new ItemRepository();
    }

    public void listarItens() {
        try {
            List<ItemGet> itens = itemRepository.getItens();;
            itens.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("Nao existem itens");
        }

    }
}
