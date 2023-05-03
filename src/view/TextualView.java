package view;

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

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("position")) {
            this.showPosition();
        }
    }

    @Override
    public void showPosition() {
        //da ricontrollare
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

    public void startView() {   //da ricontrollare
        System.out.println("\n\n\nType W to move forward, A to move left, D to move right, E to interact and ESC to exit");
        while (true) {
            try {
                char action = (char) System.in.read();
                tastiera.nextLine();
                switch (action) {
                case 'A':
                    this.controller.doAction(action);
                    System.out.println("Agent has moved left");
                    break;
                case 'W':
                    this.controller.doAction(action);
                    System.out.println("Agent has moved forward");
                    break;
                case 'D':
                    this.controller.doAction(action);
                    System.out.println("Agent has moved right");
                    break;
                case 'E':
                    this.controller.doAction(action);
                    System.out.println("Agent interaced with something");
                    break;
                case 'a':
                    this.controller.doAction(action);
                    System.out.println("Agent has moved left");
                    break;
                case 'w':
                    this.controller.doAction(action);
                    System.out.println("Agent has moved forward");
                    break;
                case 'd':
                    this.controller.doAction(action);
                    System.out.println("Agent has moved right");
                    break;
                case 'e':
                    this.controller.doAction(action);
                    System.out.println("Agent interaced with something");
                    break;
                }
            } catch (IOException e) {
                this.communicateError("An IO Error occurred");
            }
        }
    }
}
