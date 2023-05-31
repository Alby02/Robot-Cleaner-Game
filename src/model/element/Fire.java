package model.element;

import model.Cell;
import model.Removable;
import model.exception.IllegaInteractnGameException;

public class Fire extends Cell implements Removable
{
    public Fire(Integer i, Integer j)
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
