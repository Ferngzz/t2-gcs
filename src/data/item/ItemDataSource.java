package data.item;

import db.DatabaseUtils;
import domain.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// CRUD/Integracao do banco
public class ItemDataSource {

    public void createItem(ItemEntity itemEntity) throws SQLException {
        String createQuery = "INSERT INTO FUNCIONARIO (nome, valor_unitario ) VALUES (?, ?)";

        DatabaseUtils.executeUpdate(createQuery,
                itemEntity.nome(),
                itemEntity.valorUnitario()
        );
    }

    public Item getItemById(int id) throws SQLException {

        String getItemByIdQuery = "SELECT * FROM ITEM i WHERE i.id_item = ?";

        ResultSet resultItem = DatabaseUtils.executeQuery(getItemByIdQuery, id);

        return new Item(
                resultItem.getInt(1),
                resultItem.getString(3),
                resultItem.getDouble(2)
        );
    }

    public List<Item> getItens() throws SQLException {

        String getItensQuery = "SELECT * FROM ITEM";
        ResultSet resultItens = DatabaseUtils.executeQuery(getItensQuery);

        List<Item> itens = new ArrayList<>();

        while (resultItens.next()) {


            itens.add(new Item(
                    resultItens.getInt(1),
                    resultItens.getString(3),
                    resultItens.getDouble(2)
            ));
        }

        return itens;
    }

    public void deleteItem(int id) throws SQLException {
        String deleteQuery = "DELETE FROM ITEM i WHERE i.id_item=?";

        DatabaseUtils.executeQuery(deleteQuery, id);

    }
}


