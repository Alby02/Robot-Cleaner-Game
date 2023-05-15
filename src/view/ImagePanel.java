package view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

        private Image img;
      
        public ImagePanel() {
            super();
            this.img = new ImageIcon("src/img/Floor.png").getImage();
        }
      
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
        }
}

