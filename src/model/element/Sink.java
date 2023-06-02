package model.element;

import model.Cell;
import model.CellState;
import model.Map;
import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

public class Sink extends CellState
{

    public static final String states[] = {"base", "broken"}; //TODO immutable list or interface metod for getting state

    public Sink(int i, int j, int ID)
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
            throw new IllegaInteractnGameException("Invalid interaction Sink is not broken");
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
                c = new Water(this.i + 1, this.j, 6);
            }
            else if(j < mappa.getJSize() -1 && mappa.getCasella(this.i, this.j + 1) == null)
            {
                c = new Water(this.i, this.j + 1, 6);
            }
            else if(i > 1 && mappa.getCasella(this.i - 1, this.j) == null)
            {
                c = new Water(this.i - 1, this.j, 6);
            }
            else if(j > 1 && mappa.getCasella(this.i + 1, this.j - 1) == null)
            {
                c = new Water(this.i, this.j - 1, 6);
            }
                else
            {
                throw new CantGenerateStateEventException("All surrounding cell are full");
            }
        }

        return c;
    }
}
