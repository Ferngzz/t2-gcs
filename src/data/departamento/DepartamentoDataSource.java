package data.departamento;

import db.DatabaseUtils;
import domain.model.DepartamentoGet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// CRUD/Integracao do banco
public class DepartamentoDataSource {

    public int createDepartamento(DepartamentoEntity departamentoEntity) throws SQLException {
        String query = "INSERT INTO DEPARTAMENTO (limite,nome) SET (?,?)";
        return DatabaseUtils.executeInsert(query,departamentoEntity.limite(),departamentoEntity.nome());
    }

    public DepartamentoGet getDepartamentoById(int id) throws SQLException {
        String query = "SELECT * FROM DEPARTAMENTO WHERE id_departamento = ?";

        ResultSet rs = DatabaseUtils.executeQuery(query,id);
        rs.next();
        int idDepartamento = rs.getInt(1);
        double limite = rs.getDouble(2);
        String nomeDepartamento = rs.getString(3);

        return new DepartamentoGet(idDepartamento,nomeDepartamento,limite);
    }

    public List<DepartamentoGet> getDepartamentos() throws SQLException {
        String query = "SELECT * FROM DEPARTAMENTO";
        ResultSet rs = DatabaseUtils.executeQueryNoParam(query);
        List<DepartamentoGet> departamentos= new ArrayList<DepartamentoGet>();

        while(rs.next()){
            int idDepartamento = rs.getInt(1);
            double limite = rs.getDouble(2);
            String nomeDepartamento = rs.getString(3);

            departamentos.add(new DepartamentoGet(idDepartamento,nomeDepartamento,limite));
        }
        return departamentos;
    }



    public void deleteDepartamento(int id) throws SQLException {
        String query = "DELETE FROM DEPARTAMENTO WHERE id_departamento = ?";
        DatabaseUtils.executeUpdate(query,id);
    }
}
