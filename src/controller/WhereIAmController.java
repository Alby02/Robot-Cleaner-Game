package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;

import model.map.IllegalPositionGameException;
import model.map.Mappa;
import model.map.Move;
import view.WhereIAmView;

public class WhereIAmController implements ActionListener {
    private Mappa model;
    private Collection<WhereIAmView> views;

    public WhereIAmController(Mappa model, WhereIAmView... views) {
        this.model = model;
        this.views = new HashSet<>();
        for(WhereIAmView v : views) {
            System.out.println("Passo");
            this.views.add(v);
            model.addObserver(v);
            v.addController(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Move M = null;
        switch(e.getActionCommand()){
            case "LEFT":
                M = Move.SX;
                break;
            case "FORWARD":
                M = Move.FOR;
                break;
            case "RIGHT":
                M = Move.DX;
                break;
            case "INTERACT":
                M = Move.ACT;
                break;
        }
        this.move(M);
    }

    public void doAction (char action) {
        Move M = null;
        switch (action) {
            case 'L':
                M = Move.SX;
                break;
            case 'F':
                M = Move.FOR;
                break;
            case 'D':
                M = Move.DX;
                break;
            case 'I':
                M = Move.ACT;
                break;
        }
        this.move(M);
    }

    private void move(Move M) {
        try {
            model.getRobot().muovi(M);
            model.support.firePropertyChange("position", null, null);
        }
        catch (Exception e) {
            for (WhereIAmView v : this.views) {
                v.communicateError(e.getMessage());
            }
        }
    }
}
