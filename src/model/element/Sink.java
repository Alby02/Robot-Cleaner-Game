package model.element;

import model.Cell;
import model.CellState;
import model.Eventable;
import model.Interactable;
import model.Map;
import model.exception.CantGenerateEventException;
import model.exception.IllegaInteractnGameException;

public class Sink extends CellState implements Interactable, Eventable
{
    private static final String states[] = {"base", "broken"};

    public Sink(int i, int j, int ID)
    {
        super(i, j, ID, 0);
    }

    //@Override
    public static String[] getStates()
    {
        return states.clone(); //immutable list by cloning
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
    public Cell Event(Map mappa) throws CantGenerateEventException 
    {
        Cell c = null;
        if (this.state == 0) {
            this.state = 1;
        }
        else
        {
            if(i < mappa.getISize() - 1 && mappa.isCasellaEmpty(this.i + 1, this.j))
            {
                c = new Water(this.i + 1, this.j, 6);
            }
            else if(j < mappa.getJSize() -1 && mappa.isCasellaEmpty(this.i, this.j + 1))
            {
                c = new Water(this.i, this.j + 1, 6);
            }
            else if(i > 1 && mappa.isCasellaEmpty(this.i - 1, this.j))
            {
                c = new Water(this.i - 1, this.j, 6);
            }
            else if(j > 1 && mappa.isCasellaEmpty(this.i, this.j - 1))
            {
                c = new Water(this.i, this.j - 1, 6);
            }
            else
            {
                throw new CantGenerateEventException("All surrounding cell are full");
            }
            System.out.println("Generated Water " + c.posI() + " " + c.posJ());
        }

        return c;
    }
}
