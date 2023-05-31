package model;

public abstract class Cell
{   
    protected int i;
    protected int j;
    protected int ID = 0; //nel caso va finta di avere id di vuoto

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

    public int getID()
    {
        return ID;
    }
} 