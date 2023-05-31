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

    @Override
    public void startView() {
        new Thread(new Runnable() {
          System.out.println("\n\n\n");
            public void run() {
                while (true) {
                    System.out.println("Type W to move forward, A to move left, D to move right, E to interact and ESC to exit");
                    try {
                        char action = (char) System.in.read();
                        tastiera.nextLine();
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run()
                            {
                                controller.actionPerformed(new ActionEvent(controller, 0, String.valueOf(action)));
                            }
                        });
                    } catch (IOException e) {
                        communicateError("An IO Error occurred");

                    }
                }
            }
        }).start();
    }
}
