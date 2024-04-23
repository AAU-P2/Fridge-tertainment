package fridge.tertainment.DataBase.DTO;

public class RecipeIngredienceDTO extends DTO2 {

    public Integer id_recipe;
    public Integer id_ingredience;
    public double amount;
    public String type;

    public RecipeIngredienceDTO(int _id_recipe, int _id_ingredience, double _amount, String _type) {
        id1 = id_recipe = _id_recipe;
        id2 = id_ingredience = _id_ingredience;
        amount = _amount;
        type = _type;
    }

    @Override
    public String toString(){
        return "ID: [" + id_recipe + "." + id_ingredience + "], amount: " + amount + ", type: " + type;
    }

    public double getAmount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAmount'");
    }

    public String getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    public int getId1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId1'");
    }

    public int getId2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId2'");
    }

}
