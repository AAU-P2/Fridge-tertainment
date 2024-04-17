package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fridge.tertainment.DataBase.DTO.RecipeIngredienceDTO;

public class RecipeIngredienceRepository extends DTO2Repository<RecipeIngredienceDTO> {

    private PreparedStatement updateStatement;
    public RecipeIngredienceRepository(DatabaseConnection _connection) throws Exception {
        super("recipe_Ingredients", "recipe_id", "Ingredient_id", _connection);

        String updateSql = "UPDATE recipe_Ingredients SET recipe_Ingredients_amount = ?, recipe_Ingredients_Unit = ? WHERE recipe_id = ? AND Ingredient_id = ?";
        updateStatement = connection.prepareStatement(updateSql);    
    }

    protected RecipeIngredienceDTO mapResultToDTO(ResultSet rs) throws SQLException{
        return new RecipeIngredienceDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
    }

    @Override
    public boolean Update(RecipeIngredienceDTO dto) throws SQLException {
        updateStatement.setDouble(1, dto.getAmount());
        updateStatement.setString(2, dto.getType());
        updateStatement.setInt(3, dto.getId1());
        updateStatement.setInt(4, dto.getId2());
        int result = updateStatement.executeUpdate();
        return result == 1;
    }
}
