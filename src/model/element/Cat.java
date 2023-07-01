package model.element;

import java.util.Random;

import model.Cell;
import model.Eventable;
import model.Map;
import model.Movable;
import model.exception.CantGenerateEventException;

public class Cat extends Cell implements Eventable, Movable
{
    private Random nR;

    public Cat(int i, int j, int ID) 
    {
        super(i, j, ID);
        this.nR = new Random();
    }

    @Override
    public Cell Event(Map mappa) throws CantGenerateEventException 
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
        if(!mappa.isCasellaEmpty(i, j))
        {
            throw new CantGenerateEventException("Illegal Position Game Exception");
        }
        this.i = newI;
        this.j = newJ;
        return this;
    }
}
