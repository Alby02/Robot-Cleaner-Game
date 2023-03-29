package view;

import java.beans.PropertyChangeListener;

import controller.WhereIAmController;

public interface WhereIAmView extends PropertyChangeListener {

    public void showPosition();

    public  void communicateError(String message);

    public  void addController(WhereIAmController whereIAmController);

    public  void startView();
}
