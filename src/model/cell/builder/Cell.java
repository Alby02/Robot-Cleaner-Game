package model.cell.builder;

public abstract class Casella{
   
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