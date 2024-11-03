package dao;

import database.DatabaseUtils;
import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

public class ItemDAO implements GenericDAO<Item> {
    @Override
    public void insert(Item item) throws SQLException {
        String sql = "INSERT INTO usuarios (valor_unitario, nome) VALUES (?, ?)";
        DatabaseUtils.executeUpdate(sql, item.getValor_unitario(), item.getNome());
    }
}
