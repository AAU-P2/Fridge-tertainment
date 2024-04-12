package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fridge.tertainment.DataBase.DTO.RecipeDTO;

public class RecipeRepository extends DTO1Repository<RecipeDTO>{

    private Statement statement;
    public RecipeRepository(DatabaseConnection _connection) throws Exception {
        super("recipe", "recipe_id", _connection);
    }

    @Override
    protected RecipeDTO mapResultToDTO(ResultSet rs) throws SQLException {
        return new RecipeDTO(
            rs.getInt(1), 
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getInt(5),
            rs.getInt(6)
        );
    }

    @Override
    public boolean Update(RecipeDTO dto) throws SQLException {
        statement = connection.createStatement();
        String sql = """
            UPDATE recipe
            SET recipe_name = '%s', recipe_instructions = '%s', recipe_Number_of_people = %d
            WHERE recipe_id = %d            
            """.formatted(dto.name, dto.text, dto.amount, dto.id);

        int result = statement.executeUpdate(sql);
        return result == 1;
    }

    @Override
    public boolean Create(RecipeDTO dto) throws SQLException {
        statement = connection.createStatement();
        String sql = """
            INSERT INTO recipe
                (recipe_name, recipe_instructions, recipe_Number_of_people, recipe_cocking_time, recipe_calorie)
            VALUES ('%s', '%s', %d, %d, %d)
            """.formatted(dto.name, dto.text, dto.amount, dto.cookingTime, dto.calories);

        int result = statement.executeUpdate(sql);
        return result == 1;

    }

}
