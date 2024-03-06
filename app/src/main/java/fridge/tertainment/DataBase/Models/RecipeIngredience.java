package fridge.tertainment.DataBase.Models;

import fridge.tertainment.DataBase.DTO.RecipeIngredienceDTO;

public class RecipeIngredience extends Model<RecipeIngredienceDTO> {

    public RecipeIngredience(RecipeIngredienceDTO recipe_ingredience) {
        super(recipe_ingredience);
    }

    public Recipe recipe;
    public Ingredience ingredience;
}
