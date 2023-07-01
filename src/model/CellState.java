package model;

public abstract class CellState extends Cell
{
    protected int state;
    private static String[] states = {"Base"};

    public CellState(int i, int j, int ID, int state)
    {
        super(i, j, ID);
        this.state = state;
    }

    protected void setState(int s)
    {
        this.state = s;
    }

    public int getState()
    {
        return this.state;
    }

    public static String[] getStates()
    {
        return states.clone();
    }
}
