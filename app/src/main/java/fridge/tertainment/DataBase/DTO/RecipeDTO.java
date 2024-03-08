package fridge.tertainment.DataBase.DTO;

public class RecipeDTO extends DTO1 {
    
    public String name;
    public String text;
    public int amount;

    public RecipeDTO (int _id, String _name, String _text, int _amount) { 
        id = _id; 
        name = _name;
        text = _text;
        amount = _amount;
    }

    @Override
    public String toString(){
        return "ID: " + id + ", name: " + name + ", text: " + text + " amount: " + amount;
    }
    
}
