package view;

import javax.swing.*;
import java.awt.*;

import model.Cell;

public class ColouredLabel extends JLabel {
        
    private static final ImageIcon ROBOT = new ImageIcon(new ImageIcon("img/Wall-E.png").getImage().getScaledInstance(1024/12, 1024/12, Image.SCALE_DEFAULT));
    private final ImageIcon imgMatrix[][];

    public ColouredLabel(ImageIcon imgMatrix[][]) {
        this.imgMatrix = imgMatrix;
    }

    public void setByType(Cell tipo) {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ImageIcon icon = ImgBuilder.getIcon(imgMatrix, tipo);
        this.setIcon(icon);        
    }

    public void setBlack() {
        /*this.isBlack = isBlack;
        repaint();*/
        this.setIcon(null);
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        repaint();
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
