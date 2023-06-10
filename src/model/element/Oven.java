package model.element;

import model.Cell;
import model.CellState;
import model.Map;
import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

public class Oven extends CellState
{
    public static final String states[] = {"base", "broken"}; //TODO immutable list or interface metod for getting state

    public Oven(int i, int j, int ID)
    {
        super(i, j, ID, 0);
    }

    @Override
    public void interact() throws IllegaInteractnGameException 
    {

        if (this.state == 1) {
            this.state = 0;
        }
        else
        {
            throw new IllegaInteractnGameException("Invalid interaction Oven is not broken");
        }
    }

    @Override
    protected Cell Event(Map mappa) throws CantGenerateStateEventException
    {
        Cell c = null;
        if (this.state == 0) {
            this.state = 1;
        }
        else
        {
            if(i < mappa.getISize() - 1 && mappa.getCasella(this.i + 1, this.j) == null)
            {
                c = new Fire(this.i + 1, this.j, 2);
            }
            else if(j < mappa.getJSize() -1 && mappa.getCasella(this.i, this.j + 1) == null)
            {
                c = new Fire(this.i, this.j + 1, 2);
            }
            else if(i > 1 && mappa.getCasella(this.i - 1, this.j) == null)
            {
                c = new Fire(this.i - 1, this.j, 2);
            }
            else if(j > 1 && mappa.getCasella(this.i, this.j - 1) == null)
            {
                c = new Fire(this.i, this.j - 1, 2);
            }
                else
            {
                throw new CantGenerateStateEventException("All surrounding cell are full");
            }
        }
        return c;
    }
}