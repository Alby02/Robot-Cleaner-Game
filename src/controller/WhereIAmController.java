package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;

import model.map.IllegalPositionGameException;
import model.map.Map;
import view.WhereIAmView;

public class WhereIAmController implements ActionListener {
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
                    this.model.robot.foreward();
                    break;
                case "D":
                case "d":
                    this.model.robot.dir.rotateDX();
                    break;
                case "E":
                case "e":
                    this.model.enent();
                    break;
            }
            this.property.firePropertyChange("position", null, null);
        }
        catch (IllegalPositionGameException e) {
            for (WhereIAmView v : this.views) {
                v.communicateError(e.getMessage());
            }
        }
    }            
}
