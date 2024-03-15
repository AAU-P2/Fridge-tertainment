package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

import fridge.tertainment.DataBase.DTO.RecipeIngredienceDTO;

public class RecipeIngredienceRepository extends DTO2Repository<RecipeIngredienceDTO> {

    public RecipeIngredienceRepository(DatabaseConnection _connection) throws Exception {
        super("recipe_Ingredients", "recipe_id", "Ingredient_id", _connection);
    }

    protected RecipeIngredienceDTO mapResultToDTO(ResultSet rs) throws SQLException{
        return new RecipeIngredienceDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
    }

    @Override
    public boolean Update(RecipeIngredienceDTO dto) throws SQLException {
        int r = connection.createStatement().executeUpdate(String.format(
            "UPDATE recipe_Ingredients \n" +
            "SET recipe_Ingredients_amount = %f, recipe_Ingredients_Unit = '%s' \n" +
            "WHERE recipe_id = %d AND Ingredient_id = %d", 
            dto.amount, dto.type, dto.id1, dto.id2));
        return r == 1;
    }
}
