package generics;

import lombok.Data;

@Data
public class Duo<T, S> {
    //Variable d'instance de type T
    private T value1;

    //Variable d'instance de type S
    private S value2;


    //Constructeur avec paramètres
    public Duo(T val1, S val2){
        this.value1 = val1;
        this.value2 = val2;
    }


}