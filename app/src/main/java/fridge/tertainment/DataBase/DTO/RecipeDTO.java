package fridge.tertainment.DataBase.DTO;

@Table(name = "recipe")
public class RecipeDTO extends DTO1 {
    
    @Col(name = "recipe_name")
    public String name;

    @Col(name = "recipe_instructions")
    public String text;

    @Col(name = "recipe_number_of_people")
    public int amount;

    @Col(name = "recipe_cooking_time")
    public int cookingTime;

    @Col(name = "recipe_calorie")
    public int calories;

    public RecipeDTO (int _id, String _name, String _text, int _amount, int _cookingTime, int _calories) {
        id = _id; 
        name = _name;
        text = _text;
        amount = _amount;
        cookingTime = _cookingTime;
        calories = _calories;
    }

    // Constructor for creating a new RecipeDTO.
    public RecipeDTO (String _name, String _text, int _amount, int _cookingTime, int _calories) {
        this(0, _name, _text, _amount, _calories, _cookingTime);
    }

    public RecipeDTO () {
        this(0, null, null, 0, 0, 0);
    }

    @Override
    public String toString(){
        return "ID: " + id + ", name: " + name + ", text: " + text + " amount: " + amount;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true; // matching address
        if (!(o instanceof RecipeDTO)) return false; // not matching types
        RecipeDTO oDTO = (RecipeDTO)o;
        return id == oDTO.id &&
            name.equals(oDTO.name) &&
            text.equals(oDTO.text) &&
            amount == oDTO.amount &&
            calories == oDTO.calories &&
            cookingTime == oDTO.cookingTime;
    }
}
