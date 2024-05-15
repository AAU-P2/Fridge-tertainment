package fridge.tertainment.sqlConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fridge.tertainment.DataBase.DTO.RecipeDTO;

public class RecipeRepository extends DTO1Repository<RecipeDTO>{
    private PreparedStatement updateStatement;

    public RecipeRepository(DatabaseConnection _connection) throws Exception {
        super("recipe", "recipe_id", _connection);
        
        String updateSql = "UPDATE recipe SET recipe_name = ?, recipe_instructions = ?, recipe_Number_of_people = ?, recipe_cooking_time = ?, recipe_calorie = ? WHERE recipe_id = ?";
        updateStatement = connection.prepareStatement(updateSql);
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
        updateStatement.setString(1, dto.name);
        updateStatement.setString(2, dto.text);
        updateStatement.setInt(3, dto.amount);
        updateStatement.setInt(4, dto.cookingTime);
        updateStatement.setInt(5, dto.calories);
        updateStatement.setInt(6, dto.id);
        int result = updateStatement.executeUpdate();
        return result == 1;
    }
    
    @Override
    public boolean Create(RecipeDTO dto) throws SQLException {
        String sql = """
            INSERT INTO recipe
                (recipe_name, recipe_instructions, recipe_Number_of_people, recipe_cooking_time, recipe_calorie)
            VALUES (?, ?, ?, ?, ?)
            """;   

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, dto.name);
            preparedStatement.setString(2, dto.text);
            preparedStatement.setInt(3, dto.amount);
            preparedStatement.setInt(4, dto.cookingTime);
            preparedStatement.setInt(5, dto.calories);
    
            int result = preparedStatement.executeUpdate();
            return result == 1;
        }
    }

    @SuppressWarnings("removal")
    public Integer GetIdByName(String recipe_name) {
        try {

            String sql = SELECT + "WHERE recipe_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipe_name);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new Integer(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Integer(null);
    }
}
