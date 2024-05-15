package fridge.tertainment.DataBase.DTO;

import java.util.ArrayList;

import java.sql.Statement;

import fridge.tertainment.sqlConnector.Repository;

public class ArlaDTO extends DTO1 {
    String name;
    String url;
    public String ingredients_String;

    static Boolean repo_exists;
    static Boolean database_cleared = false;
    static Repository repo;

    ArrayList<ArlaIngredient> ingredients;

    RecipeDTO asRecipeDTO;
    
    public ArlaDTO(String name, String url, String _ingredients) {
        if (!repo_exists) {
            try {
                repo = new Repository();
                repo_exists = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.name = name;
        this.url = url;
        this.ingredients_String = _ingredients;
        this.ingredients = repo.arla_recipes.getIngredients(this);
        asRecipeDTO = convertToRecipeDTO();
        
    }

    RecipeDTO convertToRecipeDTO() {
        RecipeDTO rDto = new RecipeDTO(this.name, this.url, 0, 0, 0);
        return rDto;
    }

    int getRecipeDbId() {
        try {
            return repo.recipes.GetIdByName(name).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    void addToDb() {
        int recipeId;

        if (!database_cleared) {
            String sqlTrunc = """
                TRUNCATE TABLE ingredient;
                TRUNCATE TABLE recipe;
                TRUNCATE TABLE recipe_ingredient;
                """;
            
            try {
                
                Statement statement = repo.connection.createStatement();
                statement.execute(sqlTrunc);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       

        try {
            repo.recipes.Create(asRecipeDTO);
            recipeId = getRecipeDbId();
            
            for (ArlaIngredient ingredient : ingredients) {
                int ingredientId;
                ingredientId = ingredient.addIngredientToDb(repo);
                repo.recipeIngrediences.Create(new RecipeIngredienceDTO(recipeId, ingredientId, ingredient.amount, ingredient.unit));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
