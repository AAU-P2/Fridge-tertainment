package fridge.tertainment.DataBase.DTO;

public class RecipeDTO extends DTO1 {
    
    public String name;
    public String text;
    public int amount;

    public int cookingTime;
    public int calories;

    public String category;


    public RecipeDTO (int _id, String _name, String _text, int _amount, int _calories, int _cookingTime) {
        id = _id; 
        name = _name;
        text = _text;
        amount = _amount;
        calories = _calories;
        cookingTime = _cookingTime;
    }

    // Constructor for creating a new RecipeDTO.
    public RecipeDTO (String _name, String _text, int _amount, int _calories, int _cookingTime) {
        id = 0;
        name = _name;
        text = _text;
        amount = _amount;
        calories = _calories;
        cookingTime = _cookingTime;
    }


    @Override
    public String toString(){
        return "ID: " + id + ", name: " + name + ", text: " + text + " amount: " + amount;
    }
    
}
