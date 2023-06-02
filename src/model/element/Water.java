package model.element;

import model.Cell;
import model.Removable;

public class Water extends Cell implements Removable
{    
    public Water(int i, int j, int ID)
    {
        super(i, j, ID);
    }
}
