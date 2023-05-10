package model.element;

import javax.swing.ImageIcon;

import model.Cell;
import model.Removable;
import model.exception.IllegaInteractnGameException;

public class Water extends Cell implements Removable
{
    private static ImageIcon Icon;

    static
    {
        Icon = new ImageIcon(new ImageIcon("src/img/Water.png").getImage().getScaledInstance(1024/iScale, 1024/jScale, java.awt.Image.SCALE_DEFAULT));
    }
    
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

    @Override
    public ImageIcon getIcon() {
        return Icon;
    }
}
