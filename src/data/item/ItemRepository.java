package data.item;

import domain.model.ItemGet;
import domain.model.ItemInsert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Executa o CRUD
public class ItemRepository {
    private final ItemDataSource itemDataSource;

    public ItemRepository() {
        this.itemDataSource = new ItemDataSource();
    }

    public void cadastraItem(ItemInsert item) throws SQLException {
        var entity = new ItemEntity(item);
        itemDataSource.createItem(entity);
    }

    // GET item por id
    public ItemGet getItemById(int id) throws SQLException {
        ItemGet entity = itemDataSource.getItemById(id);

        return new ItemGet(
                id,
                entity.nome(),
                entity.valorUnitario()
        );
    }

    public List<ItemGet> getAllItens() throws SQLException {
        List<ItemGet> entities = itemDataSource.getItens();
        List<ItemGet> itemModels = new ArrayList<>();

        entities.forEach(entity ->
                itemModels.add(new ItemGet(
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
