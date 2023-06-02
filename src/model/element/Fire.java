package model.element;

import model.Cell;
import model.Removable;

public class Fire extends Cell implements Removable
{
    public Fire(int i, int j, int ID)
    {
        super(i, j, ID);
    }
}
