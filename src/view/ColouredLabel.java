package view;

import javax.swing.*;
import java.awt.*;

import model.Cell;
import model.element.Wall;

public class ColouredLabel extends JLabel {
        
    private static final ImageIcon ROBOT = new ImageIcon(new ImageIcon("img/Wall-E.png").getImage().getScaledInstance(1024/12, 1024/12, Image.SCALE_DEFAULT));
    private boolean isBlack;
    private final ImageIcon imgMatrix[][];

    public ColouredLabel(ImageIcon imgMatrix[][]) {
        this.imgMatrix = imgMatrix;
    }

    public void setByType(Cell tipo) {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(false);
        if (tipo instanceof Wall) {
            this.setBorder(null);
        }
        this.setIcon(ImgBuilder.getIcon(imgMatrix, tipo));
    }

    public void setBlack(boolean isBlack) {
        this.isBlack = isBlack;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isBlack) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
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
