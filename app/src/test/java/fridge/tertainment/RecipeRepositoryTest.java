package fridge.tertainment;

import org.junit.jupiter.api.Test;

import fridge.tertainment.DataBase.DTO.RecipeDTO;
import fridge.tertainment.sqlConnector.DatabaseConnection;
import fridge.tertainment.sqlConnector.RecipeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;

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
}
