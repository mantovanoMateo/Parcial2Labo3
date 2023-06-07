package Models;

import java.util.ArrayList;

public class ArregloGenerico<T>{
    private ArrayList<T> arrayGenerico;

    public ArregloGenerico() {
        arrayGenerico=new ArrayList<T>();
    }

    public ArrayList<T> getArrayGenerico() {
        return arrayGenerico;
    }

    public void setArrayGenerico(ArrayList<T> arrayGenerico) {
        this.arrayGenerico = arrayGenerico;
    }

    public void agregar(T t){
        this.arrayGenerico.add(t);
    }

    @Override
    public String toString() {
        return "arregloGenerico{" +
                "arrayGenerico=" + arrayGenerico +
                '}';
    }
}
