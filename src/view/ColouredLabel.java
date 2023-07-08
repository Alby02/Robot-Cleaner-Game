package view;

import javax.swing.*;
import java.awt.*;

import model.Map;

public class ColouredLabel extends JLabel {
        
    private final ImgBuilder imgMatrix;
    private final Map mappa;

    public ColouredLabel(Map mappa, ImgBuilder imgMatrix) {
        this.imgMatrix = imgMatrix;
        this.mappa = mappa;
    }

    public void setByType(int i, int j) {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ImageIcon icon = imgMatrix.getIcon(this.mappa, i, j);
        this.setIcon(icon);
    }

    public void setBlack() {
        this.setIcon(null);
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.repaint();
    }

    public void setSelected()
    {
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }

    public void setRobot()
    {
        this.setBorder(null);
        this.setIcon(imgMatrix.ROBOT);
    }
}
