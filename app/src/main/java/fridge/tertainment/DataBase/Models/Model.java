package fridge.tertainment.DataBase.Models;

public abstract class Model<T> {
    
    protected T model;

    public Model(T t){
        model = t;
    }

}
