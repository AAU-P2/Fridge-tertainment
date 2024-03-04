package fridge.tertainment.DataBase.Models;

import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.RecipeDTO;

public class Recipe extends Model<RecipeDTO> {

    public Recipe(RecipeDTO recipe) {
        super(recipe);
    }

    public ArrayList<RecipeIngredience> recipe_ingredience;
}
