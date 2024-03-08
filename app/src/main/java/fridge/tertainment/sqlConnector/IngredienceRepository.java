package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.IngredienceDTO;

public class IngredienceRepository extends DatabaseConnection implements IDTO1Repository<IngredienceDTO>{

    public IngredienceRepository() throws Exception {
        super();
    }

    public IngredienceRepository(DatabaseConnection dc) {
        super(dc);
    }

    private static IngredienceDTO mapResultToIngredienceDTO(ResultSet rs) throws SQLException{
        return new IngredienceDTO(rs.getInt(1), rs.getString(2));
    }

    @Override
    public IngredienceDTO Get(int id) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM ingredienser where ingrediens_id = " + id);
        rs.next();
        return mapResultToIngredienceDTO(rs);
    }

    @Override
    public boolean Update(IngredienceDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

    @Override
    public boolean Delete(IngredienceDTO dto) throws SQLException {
        return Delete(dto.id);
    }

    @Override
    public boolean Delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }

    @Override
    public ArrayList<IngredienceDTO> GetAll() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ingredienser");
        resultSet.next();

        ArrayList<IngredienceDTO> list = new ArrayList<IngredienceDTO>();
        while (resultSet.next()) {
            list.add(mapResultToIngredienceDTO(resultSet));
        }
        return list;
    }
    
}
