package model.element;

import model.Cell;

public class Wall extends Cell
{
    public static final char ID = 'w';

    @Override
    public char getID()
    {
        return ID;
    }

    public Wall(int i, int j) {
        super(i, j);
    }
}
