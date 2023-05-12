package model.element;

import javax.swing.ImageIcon;

import model.Cell;

public class Wall extends Cell
{
    public static int sas = 1;
    private static ImageIcon Icon;

    static
    {
        Icon = new ImageIcon("src/img/Wall.png");
    }
    
    public Wall(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ImageIcon getIcon() {
        return Icon;
    }
}
