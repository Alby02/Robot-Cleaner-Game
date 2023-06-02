package controller;

import java.util.Collection;
import java.util.HashSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeSupport;

import model.Map;
import model.exception.IllegaInteractnGameException;
import model.exception.IllegalPositionGameException;
import view.WhereIAmView;

public class WhereIAmController implements ActionListener, KeyListener {
    private Map model;
    private Collection<WhereIAmView> views;
    private PropertyChangeSupport property; 

    public WhereIAmController(Map model, WhereIAmView... views) {
        this.model = model;
        this.views = new HashSet<>();
        this.property = new PropertyChangeSupport(this);
        for(WhereIAmView v : views) {
            this.views.add(v);
            this.property.addPropertyChangeListener(v);
            v.addController(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try
        {
            switch(event.getActionCommand()){
                case "A":
                case "a":
                    this.model.robot.dir.rotateSX();
                    break;
                case "W":
                case "w":
                    this.model.robot.forward();
                    break;
                case "D":
                case "d":
                    this.model.robot.dir.rotateDX();
                    break;
                case "E":
                case "e":
                    this.model.interact();
                    break;
            }
            this.property.firePropertyChange("position", null, null);
        }
        catch (IllegalPositionGameException e) {
            for (WhereIAmView v : this.views) {
                v.communicateError(e.getMessage());
            }
        }
        catch (IllegaInteractnGameException e) {
            for (WhereIAmView v : this.views) {
                v.communicateError(e.getMessage());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        actionPerformed(new ActionEvent(this, 0, String.valueOf(e.getKeyChar())));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothething
    }            
}
