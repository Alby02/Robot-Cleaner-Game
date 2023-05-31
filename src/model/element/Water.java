package model.element;

import model.Cell;
import model.Removable;
import model.exception.IllegaInteractnGameException;

public class Water extends Cell implements Removable
{    
    public Water(int i, int j)
    {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void interact() throws IllegaInteractnGameException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }
}
