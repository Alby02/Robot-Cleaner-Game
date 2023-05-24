package model;

public abstract class Cell
{   
    public static int Scale = 10;

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

    public abstract char getC();

    public abstract javax.swing.ImageIcon getIcon();
} 