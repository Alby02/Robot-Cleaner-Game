package model.cell.builder;

import model.map.Map;

public abstract class CellState extends Cell implements Interactable
{
    public CellState(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor
        state = "base";
    }

    protected String state;

    abstract public Cell Event(Map mappa) throws CantGenerateStateEventException;
}
