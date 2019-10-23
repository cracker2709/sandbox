package generics;

import lombok.Data;

@Data
public class Solo<T> {

    //Variable d'instance de type T
    private T value;

    public Solo(T value){
        this.value = value;
    }


}