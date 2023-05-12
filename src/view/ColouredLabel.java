package view;

import javax.swing.*;
import java.awt.*;

import model.Cell;
import model.element.Wall;

public class ColouredLabel extends JLabel {
        
    private final ImageIcon ROBOT = new ImageIcon(new ImageIcon("src/img/Wall-E.png").getImage().getScaledInstance(1024/12, 1024/12, Image.SCALE_DEFAULT));

    public ColouredLabel(Cell cell) {}

    public void setByType(Cell tipo) {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(false);
        if (tipo instanceof Wall) {
            this.setBorder(null);
        }
        if(tipo != null)
            this.setIcon(tipo.getIcon());
        else
            this.setIcon(null);
    }

    public void setSelected()
    {
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }

    public void setRobot()
    {
        this.setBorder(null);
        this.setIcon(ROBOT);
    }
}
