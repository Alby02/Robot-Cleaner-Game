package model.cell.builder;

public abstract class Cell{
   
    private int i;
    private int j;

    public Cell(int i, int j) {
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