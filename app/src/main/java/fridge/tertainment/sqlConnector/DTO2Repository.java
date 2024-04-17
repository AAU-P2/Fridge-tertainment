package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.DTO2;

public abstract class DTO2Repository<dto2 extends DTO2> extends DatabaseConnection implements IDTO2Repository<dto2>{
    
    public DTO2Repository(String _table, String _id_name_1, String _id_name_2, DatabaseConnection _connection) throws Exception {
        super(_connection);
        TABLE = _table;
        SELECT = "SELECT * FROM " + TABLE;
        CREATE = String.format("INSERT INTO %s VALUES ", _table);
        DELETE = String.format("DELETE FROM %s ", _table);
        WHERE = String.format(" WHERE %s = %s AND %s = %s", _id_name_1, "%d", _id_name_2, "%d");
        SELECT_BY_ID1 = SELECT + String.format(" WHERE %s = ", _id_name_1);
        SELECT_BY_ID2 = SELECT + String.format(" WHERE %s = ", _id_name_2);
    }

    protected abstract dto2 mapResultToDTO(ResultSet rs) throws SQLException;

    protected final String TABLE;
    protected final String SELECT;
    protected final String CREATE;
    protected final String DELETE;
    protected final String WHERE;
    protected final String SELECT_BY_ID1;
    protected final String SELECT_BY_ID2;

    @Override
    public dto2 Get(int id1, int id2) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(SELECT + String.format(WHERE, id1, id2));
        rs.next();
        return mapResultToDTO(rs);
    };

    @Override
    public boolean Delete(dto2 dto) throws SQLException {
        return Delete(dto.id1, dto.id2);
    };

    @Override
    public boolean Delete(int id1, int id2) throws SQLException {
        int result = connection.createStatement().executeUpdate(DELETE + String.format(WHERE,id1, id2));
        return result == 1;
    };

    @Override
    public ArrayList<dto2> GetAll() throws SQLException {
        ArrayList<dto2> list = new ArrayList<dto2>();
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT);

        while (resultSet.next()) {
            list.add(mapResultToDTO(resultSet));
        }

        return list;
    };

    @Override
    public ArrayList<dto2> GetAllById(Integer id1, Integer id2) throws SQLException {
        if (id1 == null && id2 == null) return GetAll();

        ArrayList<dto2> list = new ArrayList<dto2>();
        if (id1 != null && id2 != null) {
            list.add(Get(id1, id2)); // pack single item result into a list
            return list;
        }

        ResultSet rs;
        if (id1 != null) {
            rs = connection.createStatement().executeQuery(SELECT_BY_ID1 + id1);
        }
        else
            rs = connection.createStatement().executeQuery(SELECT_BY_ID2 + id2);

        while (rs.next()) {
            list.add(mapResultToDTO(rs));
        }
        return list;
    }
}
