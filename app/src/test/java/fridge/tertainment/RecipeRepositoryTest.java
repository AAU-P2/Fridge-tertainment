package fridge.tertainment;

import org.junit.jupiter.api.Test;

import fridge.tertainment.DataBase.DTO.Col;
import fridge.tertainment.DataBase.DTO.DTO1;
import fridge.tertainment.DataBase.DTO.RecipeDTO;
import fridge.tertainment.DataBase.DTO.Table;
import fridge.tertainment.sqlConnector.DatabaseConnection;
import fridge.tertainment.sqlConnector.RecipeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RecipeRepositoryTest {

    Integer last_id = null;
    DatabaseConnection connection;
    RecipeRepository repository;

    public RecipeRepositoryTest() {

    }

    void TearDown() {
        try {
            if (last_id != null) repository.Delete(last_id);
        } catch (Exception e) { }
    }

    DatabaseConnection CreateConnection() throws Exception {
        connection = connection != null?
            connection :
            new DatabaseConnection(
                "jdbc:mysql://localhost:3306/recipe_database",
                "root",
                "gruppe6aaut"
            );
        return connection;
    }

    RecipeRepository CreateRepository() throws Exception {
        repository = repository != null? repository : new RecipeRepository(CreateConnection());
        return repository;
    }

    @Test boolean ConnectionTest() throws Exception {
        var con = CreateConnection(); 
        assertNotNull(con.connection, "Connection to Database not established");
        return con.connection != null;
    }

    @Test boolean RepositoryTest1() throws Exception {
        CreateRepository(); 
        assertNotNull(repository, "Repository not established");
        return repository != null;
    }

    @Test ArrayList<RecipeDTO> SelectAllTest() throws Exception {
        assumeTrue(RepositoryTest1());
        var list = repository.GetAll();
        assertTrue(list.size() > 0, "Repository is empty");
        return list;
    }

    @Test void CreateTest() throws Exception {
        ArrayList<RecipeDTO> list1;
        assumeTrue((list1 = SelectAllTest()).size() > 0);
        assertTrue(
            repository.Create(new RecipeDTO("Test", "test description", 1, 100, 5)),
            "Recipe creation failed"
        );
        var list2 = repository.GetAll();
        assertTrue(list1.size() < list2.size(), "Repository unchecked");

        list2.removeAll(list1);
        assertTrue(list2.size() == 1, "hi");

        last_id = list2.getFirst().id;
        TearDown();
    }

    @Test void SelectTest() throws Exception {
        assumeTrue(RepositoryTest1());
        var result = repository.Get(1);
        assertTrue(result.equals(new RecipeDTO(
            1,
            "Spaghetti Bolognese", 
            "Kog spaghetti. Brun hakket oksekød i en gryde, tilsæt løg, hvidløg, tomatpuré, flåede tomater og krydderier. Lad simre. Server bolognese over spaghetti.",
            0,
            45,
            500))
        );
    }

    @Test void UpdateTest() throws Exception {
        assumeTrue(RepositoryTest1());
        var old = repository.Get(1);
        var dto = new RecipeDTO(1, "name", "text", 2, 3, 4);
        repository.Update(dto);
        var result = repository.Get(1);
        repository.Update(old);
        assertTrue(dto.equals(result));
    }

    public static <T extends DTO1> List<T> convertSQLResultSetToObject(ResultSet resultSet, Class<T> clazz) 
        throws NullPointerException, SQLException, NoSuchMethodException, SecurityException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        /*for(Field field: fields) { field.setAccessible(true); }*/

        String id_col = clazz.getAnnotation(Table.class).name() + "_id";

        List<T> list = new ArrayList<>();
        while(resultSet.next()) {
            T dto = clazz.getConstructor().newInstance();
            dto.id = resultSet.getInt(id_col); // since id_field is part of the superclass it won't be picked up by the list of fields

            for(Field field: fields) {
                try{
                    String columnName = field.getAnnotation(Col.class).name();
                    var value = resultSet.getObject(columnName, field.getType());
                    if (value == null) System.err.println(String.format("Column %s to field [%s] failed convertion", columnName, field.getType().getName()));
                    else field.set(dto, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            list.add(dto);
        }

        return list;
    }

    @Test void TestConvertResultToDTO() throws Exception {
        assumeTrue(RepositoryTest1());

        ResultSet rs = connection.connection.createStatement().executeQuery("SELECT * FROM recipe");

        convertSQLResultSetToObject(rs, RecipeDTO.class);
    }
}
