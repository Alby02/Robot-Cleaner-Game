package model.element;

import model.Cell;
import model.Removable;

public class Fire extends Cell implements Removable
{
    public static final char ID = 'F';

    @Override
    public char getID()
    {
        return ID;
    }

    public Fire(int i, int j)
    {
        super(i, j);
    }
}
