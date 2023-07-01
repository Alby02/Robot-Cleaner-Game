package view;

import java.beans.PropertyChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.WhereIAmController;
import model.Map;

public class GUIPartialView extends JFrame implements WhereIAmView {

    private JPanel main;
    private final JButton buttons[];

    private ColouredLabel[][] labels;
    final private Map scacco;

    public GUIPartialView(Map modello, GUIView g, ImgBuilder imgMatrix) throws HeadlessException {

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
                this.labels[i][j] = new ColouredLabel (imgMatrix);
                main.add(labels[i][j]);
            }
        }

        this.showPosition();

        this.add(main, BorderLayout.CENTER);
        
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(1, 4));
        button.setPreferredSize(new Dimension(1000, 50));
        buttons = new JButton[4];
        buttons[0] = createButton("LEFT(A)", "A"); //this.buttons[0] = new JButton("LEFT(A)"); this.buttons[0].setActionCommand("A");
        buttons[1] = createButton("FORWARD(W)", "W");
        buttons[2] = createButton("RIGHT(D)", "D");
        buttons[3] = createButton("INTERACT(E)", "E");

        for (JButton but : this.buttons) {
            button.add(but);
        }

        JButton guiButton = createButton("CONTROL GUI", null);
        guiButton.addActionListener(new ActionListener() {
            private boolean guiVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                guiVisible = !guiVisible;
                g.setVisible(guiVisible);
                requestFocus();
            }
        });
        
        button.add(guiButton);
        this.add(button, BorderLayout.SOUTH);

        this.setFocusable(true);
        this.requestFocus();

        this.startView();
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
        int startRow = Math.max(scacco.robot.getI() -1, 0);
        int endRow = Math.min(scacco.robot.getI() +1, scacco.getISize() -1);
        int startCol = Math.max(scacco.robot.getJ() -1, 0);
        int endCol = Math.min(scacco.robot.getJ() +1, scacco.getJSize() -1);

        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                if(i >= startRow && i <= endRow && j >= startCol && j <= endCol){
                    this.labels[i][j].setByType(this.scacco.getCasella(i, j));
                }
                else{
                    this.labels[i][j].setBlack();
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
        JLabel content = new JLabel("You can't move in that cell");
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