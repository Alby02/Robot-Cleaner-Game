package model.element;

import model.Cell;
import model.CellState;
import model.Eventable;
import model.Interactable;
import model.Map;
import model.exception.CantGenerateEventException;
import model.exception.IllegaInteractnGameException;

public class Oven extends CellState implements Interactable, Eventable
{
    private static String states[] = {"base", "broken"};

    public Oven(int i, int j, int ID)
    {
        super(i, j, ID, 0);
        getStates();
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
            throw new IllegaInteractnGameException("Invalid interaction Oven is not broken");
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
                c = new Fire(this.i + 1, this.j, 2);
            }
            else if(j < mappa.getJSize() -1 && mappa.isCasellaEmpty(this.i, this.j + 1))
            {
                c = new Fire(this.i, this.j + 1, 2);
            }
            else if(i > 1 && mappa.isCasellaEmpty(this.i - 1, this.j))
            {
                c = new Fire(this.i - 1, this.j, 2);
            }
            else if(j > 1 && mappa.isCasellaEmpty(this.i, this.j - 1))
            {
                c = new Fire(this.i, this.j - 1, 2);
            }
                else
            {
                throw new CantGenerateEventException("All surrounding cell are full");
            }
            System.out.println("Generated Fire " + c.posI() + " " + c.posJ());
        }
        return c;
    }
}