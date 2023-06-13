package model;

public abstract class Cell
{   
    protected int i;
    protected int j;
    protected int ID;

    public Cell(int i, int j, int ID)
    {
        this.i = i;
        this.j = j;
        this.ID = ID;
    }

    public int posI()
    {
        return i;
    }

    public int posJ()
    {
        return j;
    }

    public int getID()
    {
        return ID;
    }
}
