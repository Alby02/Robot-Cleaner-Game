package model.element;

import model.Cell;
import model.Removable;

public class Water extends Cell implements Removable
{    
    public static final char ID = 'A';

    @Override
    public char getID()
    {
        return ID;
    }

    public Water(int i, int j)
    {
        super(i, j);
    }
}
