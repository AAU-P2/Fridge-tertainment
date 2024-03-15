package fridge.tertainment.sqlConnector;

public class Repository extends DatabaseConnection {
    
    public Repository() throws Exception {
        super();
        recipes = new RecipeRepository(this);
        ingrediences = new IngredienceRepository(this);
        recipeIngrediences = new RecipeIngredienceRepository(this);
    }

    public RecipeRepository recipes;
    public IngredienceRepository ingrediences;
    public RecipeIngredienceRepository recipeIngrediences;
    
}
