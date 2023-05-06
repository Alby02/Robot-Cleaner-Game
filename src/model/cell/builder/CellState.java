package model.cell.builder;

import model.map.Map;

public abstract class CellState extends Cell implements Interactable {    

    public CellState(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
        state = "base";
    }

    protected String state;

    public String getState()
    {
        return state;
    }

    abstract public Cell Event(Map mappa) throws CantGenerateStateEventException;
    
}
