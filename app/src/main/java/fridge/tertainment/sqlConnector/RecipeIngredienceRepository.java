package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fridge.tertainment.DataBase.DTO.RecipeIngredienceDTO;

public class RecipeIngredienceRepository extends DTO2Repository<RecipeIngredienceDTO> {

    private PreparedStatement updateStatement;
    public RecipeIngredienceRepository(DatabaseConnection _connection) throws Exception {
        super("recipe_ingredient", "recipe_id", "ingredient_id", _connection);

        String updateSql = "UPDATE recipe_ingredient SET recipe_ingredient_amount = ?, recipe_ingredient_Unit = ? WHERE recipe_id = ? AND ingredient_id = ?";
        updateStatement = connection.prepareStatement(updateSql);    
    }

    protected RecipeIngredienceDTO mapResultToDTO(ResultSet rs) throws SQLException{
        return new RecipeIngredienceDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
    }

    @Override
    public boolean Update(RecipeIngredienceDTO dto) throws SQLException {
        updateStatement.setDouble(1, dto.amount);
        updateStatement.setString(2, dto.type);
        updateStatement.setInt(3, dto.id1);
        updateStatement.setInt(4, dto.id2);
        int result = updateStatement.executeUpdate();
        return result == 1;
    }
}
