package model.element;

import model.Cell;
import model.CellState;
import model.Map;
import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

public class Sink extends CellState
{
    private final String states[] = {"base", "broken"}; 
    
    public Sink(Integer i, Integer j)
    {
        super(i, j, 0);
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
    public Cell Event(Map mappa) throws CantGenerateStateEventException 
    {
        Cell c = null;

        if (this.state == 0) {
            this.state = 1;
        }
        else
        {
            if(i < mappa.getISize() - 1 && mappa.getCasella(this.i + 1, this.j) == null)
            {
                c = new Water(this.i + 1, this.j);
            }
            else if(j < mappa.getJSize() -1 && mappa.getCasella(this.i, this.j + 1) == null)
            {
                c = new Water(this.i, this.j + 1);
            }
            else if(i > 1 && mappa.getCasella(this.i - 1, this.j) == null)
            {
                c = new Water(this.i - 1, this.j);
            }
            else if(j > 1 && mappa.getCasella(this.i + 1, this.j - 1) == null)
            {
                c = new Water(this.i, this.j - 1);
            }
                else
            {
                throw new CantGenerateStateEventException("All surrounding cell are full");
            }
        }

        return c;
    }
}
