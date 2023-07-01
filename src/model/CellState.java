package model;

public abstract class CellState extends Cell implements Interactable, Eventable
{
    protected int state;

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
}
