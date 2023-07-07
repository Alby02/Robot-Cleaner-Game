package model.element;

import java.util.Random;

import model.Cell;
import model.Eventable;
import model.Map;
import model.Movable;
import model.exception.CantGenerateEventException;

public class Cat extends Cell implements Eventable, Movable
{
    public static final char ID = 'C';

    @Override
    public char getID()
    {
        return ID;
    }

    private Random nR;

    public Cat(int i, int j) 
    {
        super(i, j);
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
        if(!mappa.isCasellaEmpty(newI, newJ))
        {
            throw new CantGenerateEventException("Illegal Position Game Exception");
        }
        this.i = newI;
        this.j = newJ;
        return this;
    }
}
