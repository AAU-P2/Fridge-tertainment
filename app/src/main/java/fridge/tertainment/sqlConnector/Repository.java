package fridge.tertainment.sqlConnector;

public class Repository extends DatabaseConnection {
    
    public Repository() throws Exception {
        super();
    }

    public RecipeRepository recipes = new RecipeRepository();

    
}
