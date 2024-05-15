package fridge.tertainment.sqlConnector;

import fridge.tertainment.DataBase.DTO.ArlaDTO;
import fridge.tertainment.DataBase.DTO.ArlaIngredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArlaRepository extends DTO1Repository<ArlaDTO> {

    ArlaRepository(DatabaseConnection dc) throws Exception{
        super("arla_recipes", "id", dc);
    }

    @Override
    public boolean Create(ArlaDTO dto) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArlaDTO Get(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean Update(ArlaDTO dto) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<ArlaDTO> GetAll() throws SQLException{
        String sql = """
                SELECT * FROM arla_recipes
                """;
        ArrayList<ArlaDTO> list = new ArrayList<ArlaDTO>();
        ResultSet rs = connection.createStatement().executeQuery(sql);

        while (rs.next()) {
            list.add(mapResultToDTO(rs));
        }

        return list;

    }

    @Override
    protected ArlaDTO mapResultToDTO(ResultSet rs) throws SQLException {
        return new ArlaDTO(rs.getString(1), rs.getString(2), rs.getString(3));
    }

    public ArrayList<ArlaIngredient> getIngredients(ArlaDTO dto) {
        
        ArrayList<ArlaIngredient> ingredients = new ArrayList<ArlaIngredient>();
        String[] tokens = dto.ingredients_String.split("|");
        ArrayList<String> toks = new ArrayList<String>();
        
        for (String s : tokens) {
            toks.add(s);
        }
        
        int i = 0;

        while (i < toks.size()) {
            
            String name = "name";
            int amount = 0;
            String unit = "unit";

            name = toks.get(i++);
            amount = Integer.parseInt(toks.get(i++));
            unit = toks.get(i++);

            ArlaIngredient ing = new ArlaIngredient(name, amount, unit);
            ingredients.add(ing);
        }

        return ingredients;
        
    }
}
