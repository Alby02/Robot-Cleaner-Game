package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Scanner;

import controller.WhereIAmController;

public class TextualView implements WhereIAmView { // TODO deve essere come la contol view
    static Scanner tastiera = new Scanner(System.in);
    private WhereIAmController controller;
    private boolean conti;
    Thread terminal;

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
        System.out.println("Type W to move forward, A to move left, D to move right, E to interact and ESC to exit");
    }

    @Override
    public void communicateError(String message) {
        System.err.println("An error occurred: "+message);
    }

    @Override
    public void addController(WhereIAmController controller) {
        this.controller = controller;
    }

    @Override
    public void startView() {
        System.out.println("\n\n\n");
        System.out.println("Type W to move forward, A to move left, D to move right, E to interact and ESC to exit");
        conti = true;
        this.terminal = new Thread(new Runnable() {

            public void run() {
                while (conti) {
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
        });
        terminal.start();
    }

    @Override
    public void disposami() {
        conti = false;
        terminal.interrupt();
        tastiera.close();
        controller = null;
    }
}
