package data.item;

import domain.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Executa o CRUD
public class ItemRepository {
    private final ItemDataSource itemDataSource;

    public ItemRepository() {
        this.itemDataSource = new ItemDataSource();
    }

    public void cadastraItem(Item item) throws SQLException {
        var entity = new ItemEntity(item);
        itemDataSource.createItem(entity);
    }

    // GET item por id
    public Item getItemById(int id) throws SQLException {
        Item entity = itemDataSource.getItemById(id);

        return new Item(
                id,
                entity.nome(),
                entity.valorUnitario()
        );
    }

    public List<Item> getAllItens() throws SQLException {
        List<Item> entities = itemDataSource.getItens();
        List<Item> itemModels = new ArrayList<>();

        entities.forEach(entity ->
                itemModels.add(new Item(
                        entity.idItem(),
                        entity.nome(),
                        entity.valorUnitario()
                )));
        return itemModels;
    }

    public void deleteItem(int id) throws SQLException {
        itemDataSource.deleteItem(id);
    }

}
