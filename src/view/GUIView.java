package src.view;

import javax.swing.*;

import src.controller.WhereIAmController;
import src.model.element.*;
import src.model.map.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
/*

    

*/
public class GUIView extends JFrame implements WhereIAmView {

    private class ColouredLabel extends JLabel {
        //private final Color Vuoto = Color.WHITE;
        private final ImageIcon WALL = new ImageIcon("src/img/Muro.jpg");
        private final ImageIcon ROBOT = new ImageIcon(new ImageIcon("src/img/Wall-E.jpg").getImage().getScaledInstance(1024/10, 1024/10, Image.SCALE_DEFAULT));
        private final ImageIcon LAMINATO = new ImageIcon("src/img/Laminato.jpg");
        
        public ColouredLabel(Class tipo) {
            this.setOpaque(true);
            this.setByType(tipo);
            
            
        }

        public void setByType(Class tipo) {
            
            if(tipo == Muro.class)
            {
                this.setIcon(WALL);
                this.setBorder(null);
            }
            else if(tipo == src.model.map.Robot.class)
            {
                this.setBorder(null);
                this.setIcon(ROBOT);
            }
            else{
                this.setIcon(LAMINATO);
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                /*
                this.setBackground(Vuoto);
                */
            }   
        }

        public void setSelected()
        {
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }
        

    } 

    private final JPanel main;
    private final JButton buttons[];
    /*private final JButton left;
    private final JButton right;*/

    private ColouredLabel[][] labels;
    private Mappa scacco;

    public GUIView(Mappa modello) throws HeadlessException {
        super("Where I am?");
        this.scacco = modello;
        Class mappa[][] = this.scacco.getMap();
        this.setSize(1000, 1050);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        main = new JPanel();
        main.setLayout(new GridLayout(10,10));
        main.setPreferredSize(new Dimension(1000, 1000));
        this.labels= new ColouredLabel[mappa.length][mappa[0].length];
        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                this.labels[i][j] = new ColouredLabel(mappa[i][j]);
                main.add(labels[i][j]);
            }
        }
        
        this.labels[this.scacco.getRobot().getCellFacingI()][this.scacco.getRobot().getCellFacingJ()].setSelected();

        this.add(main, BorderLayout.CENTER);
        
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(1, 4));
        button.setPreferredSize(new Dimension(1000, 50));

        this.buttons = new JButton[4];
        this.buttons[0] = new JButton("LEFT");
        this.buttons[1] = new JButton("FORWARD");
        this.buttons[2] = new JButton("INTERACT");
        this.buttons[3] = new JButton("RIGHT");

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

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("position")) {
            this.showPosition();
        }
    }

    @Override
    public void showPosition() {
        Class mappa[][] = this.scacco.getMap();
        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                this.labels[i][j].setByType(mappa[i][j]);
            }
        }
        this.labels[this.scacco.getRobot().getCellFacingI()][this.scacco.getRobot().getCellFacingJ()].setSelected();
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
