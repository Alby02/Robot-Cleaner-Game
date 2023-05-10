package model;

import model.exception.CantGenerateStateEventException;

public abstract class CellState extends Cell implements Interactable
{
    protected String state;

    public CellState(int i, int j, String state)
    {
        super(i, j);
        this.state = state;
    }

    abstract public Cell Event(Map mappa) throws CantGenerateStateEventException;
}
