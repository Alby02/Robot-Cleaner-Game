package src.model.element;

import src.model.Elemento;

public abstract class Casella implements Elemento{
   
    private int i;
    private int j;

    public Casella(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int posI()
    {
        return i;
    }

    public int posJ()
    {
        return j;
    }
} 