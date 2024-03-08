package fridge.tertainment.sqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.RecipeIngredienceDTO;

public class RecipeIngredienceRepository extends DatabaseConnection implements IDTO2Repository<RecipeIngredienceDTO> {

    RecipeIngredienceRepository() throws Exception {
        super();
    }

    RecipeIngredienceRepository(DatabaseConnection dc) {
        super(dc);
    }

    private static RecipeIngredienceDTO mapResultSetToDTO(ResultSet rs) throws SQLException{
        return new RecipeIngredienceDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
    }

    @Override
    public RecipeIngredienceDTO Get(int id1, int id2) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(String.format(
            "SELECT * FROM opskriftsingredienser WHERE opskrift_id = %d AND ingrediens_id = %d", id1, id2));
        rs.next();
        return mapResultSetToDTO(rs);
    }

    @Override
    public boolean Update(RecipeIngredienceDTO dto) throws SQLException {
        int r = connection.createStatement().executeUpdate(String.format(
            "UPDATE opskriftsingredienser \n" +
            "SET maengde = %f, enhed = '%s' \n" +
            "WHERE opskrift_id = %d AND Ingrediens_id = %d", 
            dto.amount, dto.type, dto.id1, dto.id2));
        return r == 1;
    }

    @Override
    public boolean Delete(RecipeIngredienceDTO dto) throws SQLException {
        return Delete(dto.id1, dto.id2);
    }

    @Override
    public boolean Delete(int id1, int id2) throws SQLException {
        int r = connection.createStatement().executeUpdate(String.format(
            "DELETE FROM opskriftsingredienser WHERE opskrift_id = %d AND Ingrediens_id = %d", id1, id2));
        return r == 1;
    }

    @Override
    public ArrayList<RecipeIngredienceDTO> GetAll() throws SQLException {
        ArrayList<RecipeIngredienceDTO> list = new ArrayList<RecipeIngredienceDTO>();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM opskriftsingredienser");
        while (rs.next()) {
            list.add(mapResultSetToDTO(rs));
        }
        return list;
    }

    @Override
    public ArrayList<RecipeIngredienceDTO> GetAllById(Integer id1, Integer id2) throws SQLException {
        if (id1 == null && id2 == null) return GetAll();

        ArrayList<RecipeIngredienceDTO> list = new ArrayList<RecipeIngredienceDTO>();
        if (id1 != null && id2 != null) {
            list.add(Get(id1, id2));
            return list;
        }
        ResultSet rs;
        if (id1 != null)
            rs = connection.createStatement().executeQuery("SELECT * FROM opskriftsingredienser WHERE opskrift_id = " + id1);
        else
            rs = connection.createStatement().executeQuery("SELECT * FROM opskriftsingredienser WHERE Ingrediens_id = " + id1);

        while (rs.next()) {
            list.add(mapResultSetToDTO(rs));
        }
        return list;
    }
    
}
