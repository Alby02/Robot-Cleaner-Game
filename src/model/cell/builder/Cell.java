package model.cell.builder;

public abstract class Cell
{   
    protected int i;
    protected int j;

    public Cell(int i, int j)
    {
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