package view;

import javax.swing.*;
import java.awt.*;

import model.Map;

public class ColouredLabel extends JLabel {
        
    private static final ImageIcon ROBOT = new ImageIcon(new ImageIcon("img/Wall-E.png").getImage().getScaledInstance(1024/12, 1024/12, Image.SCALE_DEFAULT));
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

    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isBlack) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }*/

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
