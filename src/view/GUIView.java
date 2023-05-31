package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import controller.WhereIAmController;
import model.Map;

public class GUIView extends JFrame implements WhereIAmView {

    private JPanel main;
    private final JButton buttons[];
    /*private final JButton left;
    private final JButton right;*/

    private ColouredLabel[][] labels;
    final private Map scacco;

    public GUIView(Map modello) throws HeadlessException {
        super("Robot Cleaner 9000");
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
        buttons[0] = createButton("LEFT(A)", "A"); //this.buttons[0] = new JButton("LEFT(A)"); this.buttons[0].setActionCommand("A");
        buttons[1] = createButton("FORWARD(W)", "W");
        buttons[2] = createButton("RIGHT(D)", "D");
        buttons[3] = createButton("INTERACT(E)", "E");

        for (JButton but : this.buttons) {
            button.add(but);
        }

        this.add(button, BorderLayout.SOUTH);

        this.setFocusable(true);
        this.requestFocus();

        /*left = new JButton("LEFT");
        this.add(left,BorderLayout.WEST);
        right = new JButton("RIGHT");
        this.add(right,BorderLayout.EAST);*/

        /*SwingUtilities.updateComponentTreeUI(this);
        this.pack();*/
    }

    private JButton createButton(String label, String actionCommand) {
        JButton button = new JButton(label);
        button.setActionCommand(actionCommand);
        return button;
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
        this.requestFocus();
    }

    @Override
    public void communicateError(String message) {
        //do nothing
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
        //do nothing
    }
}
