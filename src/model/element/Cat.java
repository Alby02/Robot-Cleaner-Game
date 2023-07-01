package model.element;

import model.Cell;
import model.Eventable;
import model.Map;
import model.exception.CantGenerateStateEventException;

public class Cat extends Cell implements Eventable
{

    public Cat(int i, int j, int ID) 
    {
        super(i, j, ID);
    }

    @Override
    public Cell Event(Map mappa) throws CantGenerateStateEventException 
    {
        int direction = mappa.nRandom.nextInt(4);
        int oldI = this.i, oldJ = this.j;
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
        this.vuoto(mappa,oldI, oldJ);
        return this;
    }
}
