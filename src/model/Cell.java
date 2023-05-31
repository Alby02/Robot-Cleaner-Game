package model;

public abstract class Cell
{   
    protected int i;
    protected int j;

    public Cell(Integer i, Integer j)
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