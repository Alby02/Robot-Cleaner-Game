package model.element;

import java.util.Random;

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

    //@Override
    public static String[] getStates()
    {
        return states.clone(); //immutable list by cloning
    }

    public static final char ID = 'o';

    @Override
    public char getID()
    {
        return ID;
    }

    private Random nR;

    public Oven(int i, int j, int state)
    {
        super(i, j, state);
        this.nR = new Random();
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
            int direction = this.nR.nextInt(4);
            int newI = this.i, newJ = this.j;
            switch (direction) {
                case 0:
                    newI--;//Muovi verso l'alto 
                    break;
                case 1:
                    newI++;//Muovi verso il basso
                    break;
                case 2:
                    newJ--;//Muovi verso sinistra
                    break;
                case 3:
                    newJ++;//Muovi verso destra
                    break;
            }

            try{
                if(mappa.isCasellaEmpty(newI, newJ))
                    c = new Fire(newI, newJ);
                else
                    throw new CantGenerateEventException("The random chosen cell is not empty");
            }catch(IndexOutOfBoundsException e)
            {
                throw new CantGenerateEventException("The random chosen cell does not exist");
            }
            System.out.println("Generated Fire " + c.posI() + " " + c.posJ());
        }
        return c;
    }
}
