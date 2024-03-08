package fridge.tertainment.DataBase.DTO;

public class IngredienceDTO extends DTO1 {

    public String name;

    public IngredienceDTO(int _id, String _name){
        id = _id;
        name = _name;
    }

    @Override
    public String toString(){
        return "ID: " + id + " , name: " + name;
    }

}
