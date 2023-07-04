package controller;

import java.util.Collection;
import java.util.HashSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Map;
import model.MapBuilder;
import model.exception.IllegaInteractnGameException;
import model.exception.IllegalPositionGameException;
import view.WhereIAmView;

public class WhereIAmController implements ActionListener, KeyListener, WindowListener {
    private Map model;
    private Collection<WhereIAmView> views;
    private PropertyChangeSupport property; 
    private Class<?> el[];

    public WhereIAmController(Map model, Class<?> el[], WhereIAmView... views) {
        this.model = model;
        this.el = el;
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
            String actionCommand = event.getActionCommand().toLowerCase();

            switch(actionCommand){
                case "a":
                    this.model.robot.dir.rotateSX();
                    break;
                case "w":
                    this.model.robot.forward();
                    break;
                case "d":
                    this.model.robot.dir.rotateDX();
                    break;
                case "e":
                    this.model.interact();
                    break;
                case ".":
                    this.model.event();
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

    @Override
    public void windowOpened(WindowEvent e) {
        //do non
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            MapBuilder.toFile(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now()) + ".txt", model, el);
            System.exit(0);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ReflectiveOperationException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //do non
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //do non
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //do non
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //do non
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //do non
    }            
}
