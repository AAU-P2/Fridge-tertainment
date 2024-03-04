package fridge.tertainment.DataBase.DTO;

public class RecipeIngredienceDTO {

    public int id_recipe;
    public int id_ingredience;
    public double amount;
    public String type;

    public RecipeIngredienceDTO(int _id_recipe, int _id_ingredience, double _amount, String _type) {
        id_recipe = _id_recipe;
        id_ingredience = _id_ingredience;
        amount = _amount;
        type = _type;
    }

    @Override
    public String toString(){
        return "ID: [" + id_recipe + "." + id_ingredience + "], amount: " + amount + ", type: " + type;
    }

}
