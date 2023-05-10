package view;

import javax.swing.*;

import controller.WhereIAmController;
import model.cell.builder.Cell;
import model.cell.element.Wall;
import model.map.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
public class GUIView extends JFrame implements WhereIAmView {

    private class ColouredLabel extends JLabel {
        //private final Color Vuoto = Color.WHITE;
        
        private final ImageIcon ROBOT = new ImageIcon(new ImageIcon("src/img/Wall-E.png").getImage().getScaledInstance(1024/12, 1024/12, Image.SCALE_DEFAULT));

        public ColouredLabel(Cell tipo) {
            this.setOpaque(false);
            this.setByType(tipo);            
        }

        public void setByType(Cell tipo) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            if (tipo instanceof Wall) {
                this.setBorder(null);
            }
            if(tipo != null)
                this.setIcon(tipo.getIcon());
            else
                this.setIcon(null);
                /*
                this.setBackground(Vuoto);
                */   
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

    class ImagePanel extends JPanel {

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

    private final JPanel main;
    private final JButton buttons[];
    /*private final JButton left;
    private final JButton right;*/

    private ColouredLabel[][] labels;
    final private Map scacco;

    public GUIView(Map modello) throws HeadlessException {
        super("Gioco");
        this.scacco = modello;
        this.setSize(1000, 1050);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        main = new ImagePanel();
        main.setLayout(new GridLayout(modello.getISize(),modello.getJSize()));
        main.setPreferredSize(new Dimension(1000, 1000));
        this.labels= new ColouredLabel[modello.getISize()][modello.getJSize()];
        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                this.labels[i][j] = new ColouredLabel (modello.getCasella(i, j));
                main.add(labels[i][j]);
            }
        }
        
        this.labels[modello.robot.getCellFacingI()][modello.robot.getCellFacingJ()].setSelected();
        this.labels[modello.robot.getI()][modello.robot.getJ()].setRobot();
        this.add(main, BorderLayout.CENTER);
        
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(1, 4));
        button.setPreferredSize(new Dimension(1000, 50));

        this.buttons = new JButton[4];
        this.buttons[0] = new JButton("LEFT(A)");
        this.buttons[0].setActionCommand("A");
        this.buttons[1] = new JButton("FORWARD(W)");
        this.buttons[1].setActionCommand("W");
        this.buttons[2] = new JButton("RIGHT(D)");
        this.buttons[2].setActionCommand("D");
        this.buttons[3] = new JButton("INTERACT(E)");
        this.buttons[3].setActionCommand("E");

        for (JButton but : this.buttons) {
            button.add(but);
        }

        this.add(button, BorderLayout.SOUTH);
         
        /*left = new JButton("LEFT");
        this.add(left,BorderLayout.WEST);
        right = new JButton("RIGHT");
        this.add(right,BorderLayout.EAST);*/

        /*SwingUtilities.updateComponentTreeUI(this);
        this.pack();*/

        this.startView();

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("position")) {
            this.showPosition();
        }
    }

    @Override
    public void showPosition() {
        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                this.labels[i][j].setByType(this.scacco.getCasella(i, j));
            }
        }
        this.labels[this.scacco.robot.getI()][this.scacco.robot.getJ()].setRobot();
        this.labels[this.scacco.robot.getCellFacingI()][this.scacco.robot.getCellFacingJ()].setSelected();
    }

    @Override
    public void communicateError(String message) {
        JDialog dialog = new JDialog(this);
        JLabel content = new JLabel(message);
        dialog.add(content);
        dialog.setSize(300,100);
        dialog.setVisible(true);
    }
    
    @Override
    public void addController(WhereIAmController controller) {
        for (JButton but : this.buttons) {
            but.addActionListener(controller);
        }
    }

    @Override
    public void startView() {
        this.setVisible(true);
    }
}
