package model;

import model.exception.CantGenerateStateEventException;

public abstract class CellState extends Cell implements Interactable
{
    protected int state;

    public CellState(int i, int j, int state)
    {
        super(i, j);
        this.state = state;
    }

    protected void setState(int s)
    {
        this.state = s;
    }

    public int getState()
    {
        return state;
    }

    abstract public Cell Event(Map mappa) throws CantGenerateStateEventException;
}
