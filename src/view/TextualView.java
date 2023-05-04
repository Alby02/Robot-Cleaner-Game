package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Scanner;

import controller.WhereIAmController;

public class TextualView implements WhereIAmView {
    static Scanner tastiera = new Scanner(System.in);
    private WhereIAmController controller;

    public TextualView() {
        //doNothing
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("position")) {
            this.showPosition();
        }
    }

    @Override
    public void showPosition() {
        //doNothing
    }

    @Override
    public void communicateError(String message) {
        System.err.println("An error occurred: "+message);
    }

    @Override
    public void addController(WhereIAmController controller) {
        this.controller = controller;
        this.startView();
    }

    public void startView() {
        System.out.println("\n\n\nType W to move forward, A to move left, D to move right, E to interact and ESC to exit");
        while (true) {
            try {
                char action = (char) System.in.read();
                tastiera.nextLine();
                this.controller.actionPerformed(new ActionEvent(controller, 0, String.valueOf(action)));
            } catch (IOException e) {
                this.communicateError("An IO Error occurred");
            }
        }
    }
}
