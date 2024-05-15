package fridge.tertainment.sqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.DTO1;
import fridge.tertainment.DataBase.DTO.IngredienceDTO;

public class IngredienceRepository extends DTO1Repository<IngredienceDTO> {

    private Statement statement;
    public IngredienceRepository(DatabaseConnection dc) throws Exception{
        super("ingredient", "ingredient_id", dc);
    }

    @Override
    public boolean Update(IngredienceDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

    @Override
    public boolean Create(IngredienceDTO dto) throws SQLException {
        statement = connection.createStatement();
        String sql = String.format("INSERT INTO ingredient (ingredient_name) \nVALUES ('%s')", dto.name);
        int result = statement.executeUpdate(sql);
        return result == 1;
    }

    @Override
    protected IngredienceDTO mapResultToDTO(ResultSet rs) throws SQLException {
        return new IngredienceDTO(rs.getInt(1), rs.getString(2));
    }
    
    @SuppressWarnings("removal")
    public Integer GetIdByName(String ingredient_name) {
        try {
            String sql = SELECT + "WHERE ingredient_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ingredient_name);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new Integer(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Integer(null);
    }

}
