package fridge.tertainment.sqlConnector;

public class Repository extends DatabaseConnection {
    
    public Repository() throws Exception {
        super();
        arla_recipes = new ArlaRepository(this);
        recipes = new RecipeRepository(this);
        ingrediences = new IngredienceRepository(this);
        recipeIngrediences = new RecipeIngredienceRepository(this);
    }

    public ArlaRepository arla_recipes;
    public RecipeRepository recipes;
    public IngredienceRepository ingrediences;
    public RecipeIngredienceRepository recipeIngrediences;
    
}
