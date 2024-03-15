package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fridge.tertainment.DataBase.DTO.RecipeDTO;

public class RecipeRepository extends DTO1Repository<RecipeDTO>{

    public RecipeRepository(DatabaseConnection _connection) throws Exception {
        super("recipe", "recipe_id", _connection);
    }

    @Override
    protected RecipeDTO mapResultToDTO(ResultSet rs) throws SQLException {
        return new RecipeDTO(
            rs.getInt(1), 
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4)
        );
    }


    @Override
    public boolean Update(RecipeDTO dto) throws SQLException {
        Statement s = connection.createStatement();
        String sql = String.format(
            "UPDATE recipe \nSET recipe_name = '%s', recipe_instructions = '%s', recipe_Number_of_people = %d \nWHERE recipe_id = %d", 
            dto.name, dto.text, dto.amount, dto.id);
        int result = s.executeUpdate(sql);
        return result == 1;
    }


    @Override
    public boolean Create(RecipeDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Create'");
    }

}
