package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import fridge.tertainment.DataBase.DTO.DTO1;

public abstract class DTO1Repository<dto1 extends DTO1> extends DatabaseConnection implements IDTO1Repository<dto1>{
    protected abstract dto1 mapResultToDTO(ResultSet rs) throws SQLException;

    protected final String TABLE;
    protected final String SELECT;
    protected final String CREATE;
    protected final String DELETE;
    protected final String WHERE;
    protected PreparedStatement selectStatement;
    protected PreparedStatement deleteStatement;

    public DTO1Repository(String _table, String _id_name, DatabaseConnection _connection) throws Exception {
        super(_connection);
        TABLE = _table;
        SELECT = "SELECT * FROM " + TABLE;
        CREATE = String.format("INSERT INTO %s VALUES ", _table);
        DELETE = String.format("DELETE FROM %s ", _table);
        WHERE = String.format(" WHERE %s = ", _id_name);
        String Sql_select = SELECT + WHERE + "?";
        selectStatement = connection.prepareStatement(Sql_select);

        String Sql_Delete = DELETE + WHERE + "?";
        deleteStatement = connection.prepareStatement(Sql_Delete);
    }

    public dto1 Get(int id) throws SQLException {
        selectStatement.setInt(1, id);
        try (ResultSet rs = selectStatement.executeQuery()) {
            rs.next();
            return mapResultToDTO(rs);
        }
    };

    public boolean Delete(dto1 dto) throws SQLException {

        return Delete(dto.id);
    };

    public boolean Delete(int id) throws SQLException {
        deleteStatement.setInt(1, id);
        int result = deleteStatement.executeUpdate();
        return result == 1;
    };

    public ArrayList<dto1> GetAll() throws SQLException {
        ArrayList<dto1> list = new ArrayList<dto1>();
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT);

        while (resultSet.next()) {
            list.add(mapResultToDTO(resultSet));
        }

        return list;
    };

}
