package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.RecipeDTO;

public class RecipeRepository extends DatabaseConnection implements IDTO1Repository<RecipeDTO>{

    public RecipeRepository() throws Exception {
        super();
    }

    public RecipeRepository(DatabaseConnection dc) {
        super(dc);
    }

    protected static RecipeDTO mapResultToRecipeDTO(ResultSet resultSet) throws SQLException {
        return new RecipeDTO(
            resultSet.getInt(1), 
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getInt(4)
        );
    }

    @Override
    public ArrayList<RecipeDTO> GetAll() throws SQLException {

        ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
        Statement s = connection.createStatement();
        ResultSet resultSet = s.executeQuery("SELECT * FROM opskrift");

        while (resultSet.next()) {
            list.add(mapResultToRecipeDTO(resultSet));
        }

        return list;
    }
    
    @Override
    public RecipeDTO Get(int id) throws SQLException {
        Statement s = connection.createStatement();
        ResultSet resultSet = s.executeQuery("SELECT * FROM opskrift WHERE opskrift_id = " + id);
        if (!resultSet.next()) throw new SQLException("Not found");
        return mapResultToRecipeDTO(resultSet);
    }

    @Override
    public boolean Update(RecipeDTO dto) throws SQLException {
        Statement s = connection.createStatement();
        String sql = String.format("UPDATE opskrift \nSET navn = '%s', instruktioner = '%s', antal_personer = %d \nWHERE opskrift_id = %d", dto.name, dto.text, dto.amount, dto.id);
        int result = s.executeUpdate(sql);
        return result == 1;
    }

    @Override
    public boolean Delete(RecipeDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }

    @Override
    public boolean Delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }

}
