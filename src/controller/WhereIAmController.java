package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;

import model.map.Mappa;
import model.map.Move;
import view.WhereIAmView;

public class WhereIAmController implements ActionListener {
    private Mappa model;
    private Collection<WhereIAmView> views;
    private PropertyChangeSupport proprerty; 

    public WhereIAmController(Mappa model, WhereIAmView... views) {
        this.model = model;
        this.views = new HashSet<>();
        this.proprerty = new PropertyChangeSupport(this);
        for(WhereIAmView v : views) {
            this.views.add(v);
            this.proprerty.addPropertyChangeListener(v);
            v.addController(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Move M = null;
        switch(e.getActionCommand()){
            case "A":
            case "a":
                M = Move.SX;
                break;
            case "W":
            case "w":
                M = Move.FOR;
                break;
            case "D":
            case "d":
                M = Move.DX;
                break;
            case "E":
            case "e":
                M = Move.ACT;
                break;
        }
        this.move(M);
    }


    private void move(Move M) {
        try {
            model.getRobot().muovi(M);
            this.proprerty.firePropertyChange("position", null, null);
        }
        catch (Exception e) {
            for (WhereIAmView v : this.views) {
                v.communicateError(e.getMessage());
            }
        }
    }
}
