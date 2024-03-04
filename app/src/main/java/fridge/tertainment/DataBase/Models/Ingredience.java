package fridge.tertainment.DataBase.Models;

import java.util.ArrayList;

import fridge.tertainment.DataBase.DTO.IngredienceDTO;

public class Ingredience extends Model<IngredienceDTO> {

    public Ingredience(IngredienceDTO ingredience) {
        super(ingredience);
    }

    public ArrayList<RecipeIngredience> recipe_ingredience;
}
