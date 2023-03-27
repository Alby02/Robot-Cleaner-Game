package src.view;

import java.beans.PropertyChangeListener;

import src.controller.WhereIAmController;

public interface WhereIAmView extends PropertyChangeListener {

    public void showPosition();

    public  void communicateError(String message);

    public  void addController(WhereIAmController whereIAmController);

    public  void startView();
}
