package fridge.tertainment.DataBase.DTO;

public class RecipeIngredienceDTO extends DTO2 {

    public Integer id_recipe;
    public Integer id_ingredience;
    public double amount;
    public String type;

    public RecipeIngredienceDTO(int _id_recipe, int _id_ingredience, double _amount, String _type) {
        super.id1 =  _id_recipe;
        super.id2 = _id_ingredience;
        amount = _amount;
        type = _type;
    }

    @Override
    public String toString(){
        return "ID: [" + id_recipe + "." + id_ingredience + "], amount: " + amount + ", type: " + type;
    }
}
