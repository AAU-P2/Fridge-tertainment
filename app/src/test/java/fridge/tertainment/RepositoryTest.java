package fridge.tertainment;

import org.junit.jupiter.api.Test;

import fridge.tertainment.sqlConnector.DatabaseConnection;
import fridge.tertainment.sqlConnector.Repository;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    DatabaseConnection CreateConnection() throws Exception {
        return new DatabaseConnection(
                "jdbc:mysql://localhost:3306/recipe_database",
                "root",
                "gruppe6aaut"
            );
    }

    @Test void ConnectionTest() throws Exception {
        var con = CreateConnection(); 
        assertNotNull(con.connection, "Connection to Database not established");
    }

    @
}