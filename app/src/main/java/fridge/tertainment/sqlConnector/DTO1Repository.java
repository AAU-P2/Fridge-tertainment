package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;

import fridge.tertainment.DataBase.DTO.Col;
import fridge.tertainment.DataBase.DTO.DTO1;
import fridge.tertainment.DataBase.DTO.Table;

public abstract class DTO1Repository<dto1 extends DTO1> extends DatabaseConnection implements IDTO1Repository<dto1>{
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

    protected void initUpdateStatement(Class<dto1> clazz) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        String tablename = clazz.getAnnotation(Table.class).name() ;
        String preparedSql = String.format("UPDATE %s SET ", tablename);
        for(Field field: fields) {
            String columnName = field.getAnnotation(Col.class).name();
            preparedSql += " " + columnName + " = ?,";
        }
        preparedSql = preparedSql.substring(0, preparedSql.length() - 2) + String.format(" WHERE %s = ?", tablename + "_id");
    }

    protected abstract dto1 mapResultToDTO(ResultSet rs) throws SQLException;

    protected ArrayList<dto1> mapResultToDTOList(ResultSet resultSet, Class<dto1> clazz) 
        throws NullPointerException, SQLException, NoSuchMethodException, SecurityException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        /*for(Field field: fields) { field.setAccessible(true); }*/

        String id_col = clazz.getAnnotation(Table.class).name() + "_id";

        var list = new ArrayList<dto1>();
        while(resultSet.next()) {
            dto1 dto = clazz.getConstructor().newInstance();
            dto.id = resultSet.getInt(id_col); // since id_field is part of the superclass it won't be picked up by the list of fields

            for(Field field: fields) {
                try{
                    String columnName = field.getAnnotation(Col.class).name();
                    var value = resultSet.getObject(columnName, field.getType());
                    if (value == null) System.err.println(String.format("Column %s to field [%s] failed convertion", columnName, field.getType().getName()));
                    else field.set(dto, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            list.add(dto);
        }

        return list;
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
