package model.cell.builder;

public abstract class Cell
{   
    public static int iScale = 10;
    public static int jScale = 10;

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

    public abstract javax.swing.ImageIcon getIcon();
} 