package fridge.tertainment.DataBase.DTO;

import fridge.tertainment.sqlConnector.Repository;

public class ArlaIngredient {
    String name;
    int amount;
    String unit;

    IngredienceDTO asIngredienceDTO;

    public ArlaIngredient(String name, int amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        asIngredienceDTO = convertToIngredienceDTO();
    }

    // Returns ID of ingredient in the SQL DB.
    int addIngredientToDb(Repository repo) {
        try {
            repo.ingrediences.Create(asIngredienceDTO);
            return repo.ingrediences.GetIdByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    IngredienceDTO convertToIngredienceDTO() {
        return new IngredienceDTO(name);
    }
}
