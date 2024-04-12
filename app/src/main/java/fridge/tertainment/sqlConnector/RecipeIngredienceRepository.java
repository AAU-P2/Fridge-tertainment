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

        String sql = """
            UPDATE recipe_Ingredients
            SET recipe_Ingredients_amount = %f, recipe_Ingredients_Unit = '%s'
            WHERE recipe_id = %d AND Ingredient_id = %d
            """.formatted(dto.amount, dto.type, dto.id1, dto.id2);

        int r = connection.createStatement().executeUpdate(sql);
        return r == 1;
    }
}
