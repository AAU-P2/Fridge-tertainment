package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.DTO1;
import fridge.tertainment.DataBase.DTO.IngredienceDTO;

public class IngredienceRepository extends DTO1Repository<IngredienceDTO> {

    public IngredienceRepository(DatabaseConnection dc) throws Exception{
        super("ingredients", "Ingredient_id", dc);
    }

    @Override
    public boolean Update(IngredienceDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

    @Override
    public boolean Create(IngredienceDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Create'");
    }

    @Override
    protected IngredienceDTO mapResultToDTO(ResultSet rs) throws SQLException {
        return new IngredienceDTO(rs.getInt(1), rs.getString(2));
    }
    
}
