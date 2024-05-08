package fridge.tertainment.DataBase.DTO;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Col {

    String name();
}
