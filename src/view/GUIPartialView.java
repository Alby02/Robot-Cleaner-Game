package view;

import java.beans.PropertyChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.WhereIAmController;
import model.Cell;
import model.Map;
import model.element.Wall;

public class GUIPartialView extends JFrame implements WhereIAmView {
    private class ColouredLabel extends JLabel {
        
        private final ImageIcon ROBOT = new ImageIcon(new ImageIcon("src/img/Wall-E.png").getImage().getScaledInstance(1024/12, 1024/12, Image.SCALE_DEFAULT));

        public ColouredLabel() {}

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

    private JPanel main;
    private final JButton buttons[];

    private ColouredLabel[][] labels;
    final private Map scacco;

    public GUIPartialView(Map modello, GUIView g) throws HeadlessException {
        super("Robot Cleaner 9000") ;
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
                this.labels[i][j] = new ColouredLabel ();
                main.add(labels[i][j]);
            }
        }

        this.showPosition();

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
        JButton GUIable = new JButton("CONTROL GUI");

        for (JButton but : this.buttons) {
            button.add(but);
        }
        button.add(GUIable);

        GUIable.addActionListener(new ActionListener() {
            private boolean gui = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                gui = !gui;
                g.setVisible(gui);
                requestFocus();
            }
        });

        this.add(button, BorderLayout.SOUTH);

        this.setFocusable(true);
        this.requestFocus();

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
        int startRow = Math.max(scacco.robot.getI() -1, 0);
        int endRow = Math.min(scacco.robot.getI() +1, scacco.getISize() -1);
        int startCol = Math.max(scacco.robot.getJ() -1, 0);
        int endCol = Math.min(scacco.robot.getJ() +1, scacco.getJSize() -1);

        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                if(i >= startRow && i <= endRow && j >= startCol && j <= endCol){
                    this.labels[i][j].setVisible(true);
                    this.labels[i][j].setByType(this.scacco.getCasella(i, j));
                }
                else{
                    this.labels[i][j].setVisible(false);
                }
            }
        }
        this.labels[this.scacco.robot.getI()][this.scacco.robot.getJ()].setRobot();
        this.labels[this.scacco.robot.getCellFacingI()][this.scacco.robot.getCellFacingJ()].setSelected();
        this.requestFocus();
    }

    @Override
    public void communicateError(String message) {
        JDialog dialog = new JDialog(this);
        JLabel content = new JLabel(message);
        dialog.add(content);
        dialog.setSize(300,100);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    @Override
    public void addController(WhereIAmController controller) {
        for (JButton but : this.buttons) {
            but.addActionListener(controller);
        }
        this.addKeyListener(controller);
    }

    @Override
    public void startView() {
        this.setVisible(true);
    }
}