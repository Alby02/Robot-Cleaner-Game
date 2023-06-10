package model.element;

import model.Cell;
import model.CellState;
import model.Map;
import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

public class Cat extends CellState 
{
    public static final String states[] = {"base"};//TODO orribile da sistemare

    public Cat(int i, int j, int ID) 
    {
        super(i, j, ID, 0);
    }

    @Override
    public void interact() throws IllegaInteractnGameException {
        //doNothing
    }

    @Override
    protected Cell Event(Map mappa) throws CantGenerateStateEventException 
    {
        Cell c = null;
        int oldI = i;
        int oldJ = j;

        int direction = mappa.nRandom.nextInt(4);

        switch (direction) {
            case 0:
                if (mappa.getCasella(this.i - 1, this.j) == null)
                    this.i--;//Muovi verso l'alto 
                else
                    throw new CantGenerateStateEventException("Illegal Position Game Exception");
                break;
            case 1:
                if (mappa.getCasella(this.i + 1, this.j) == null)
                    this.i++;//Muovi verso il basso
                else
                    throw new CantGenerateStateEventException("Illegal Position Game Exception");
                break;
            case 2:
                if (mappa.getCasella(this.i, this.j - 1) == null)
                    this.j--;//Muovi verso sinistra
                else
                    throw new CantGenerateStateEventException("Illegal Position Game Exception");
                break;
            case 3:
                if (mappa.getCasella(this.i, this.j + 1) == null )
                    this.j++;//Muovi verso destra
                else
                    throw new CantGenerateStateEventException("Illegal Position Game Exception");
                break;
        }

        mappa.setNewCatPosition(oldI, oldJ, this.i, this.j);

        return c;
    }
}
