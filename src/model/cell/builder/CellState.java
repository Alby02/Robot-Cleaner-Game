package model.cell.builder;

public abstract class CellState extends Cell implements Interactable {    

    public CellState(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
        state = "base";
    }

    private String state;

    public String getState()
    {
        return state;
    }
    
}
