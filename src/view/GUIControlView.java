package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import controller.WhereIAmController;
import model.Map;

public class GUIControlView extends JFrame implements WhereIAmView {

    private JPanel main;

    private ColouredLabel[][] labels;
    private Map scacco;

    public GUIControlView(Map modello, ImgBuilder imgMatrix) throws HeadlessException {
        super("Robot Cleaner 9000");
        this.scacco = modello;
        this.setSize(1000, 1000);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        main = new ImagePanel();
        main.setLayout(new GridLayout(modello.getISize(),modello.getJSize()));
        main.setPreferredSize(new Dimension(1000, 1000));
        this.labels= new ColouredLabel[modello.getISize()][modello.getJSize()];
        for(int i=0;i<this.labels.length;i++) {
            for(int j=0; j<this.labels[i].length; j++){
                this.labels[i][j] = new ColouredLabel (scacco, imgMatrix);
                main.add(labels[i][j]);
            }
        }
        
        this.labels[modello.robot.getCellFacingI()][modello.robot.getCellFacingJ()].setSelected();
        this.labels[modello.robot.getI()][modello.robot.getJ()].setRobot();
        this.add(main, BorderLayout.CENTER);
        

        this.setFocusable(true);
        showPosition();

        /*left = new JButton("LEFT");
        this.add(left,BorderLayout.WEST);
        right = new JButton("RIGHT");
        this.add(right,BorderLayout.EAST);*/

        /*SwingUtilities.updateComponentTreeUI(this);
        this.pack();*/
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
                this.labels[i][j].setByType(i, j);
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
        this.addKeyListener(controller);
    }

    @Override
    public void startView() {
        //do nothing
    }

    @Override
    public void disposami() {
        scacco = null;
        this.dispose();
    }
}
