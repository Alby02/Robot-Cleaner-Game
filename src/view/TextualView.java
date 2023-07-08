package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Scanner;

import controller.WhereIAmController;
import model.Map;

public class TextualView implements WhereIAmView {
    static Scanner tastiera = new Scanner(System.in);
    private WhereIAmController controller;
    private boolean conti;
    private Thread terminal;
    private Map m;

    public TextualView(Map m) {
        this.m = m;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("position")) {
            this.showPosition();
        }
    }

    @Override
    public void showPosition() {
        for(int i = 0; i < m.getISize(); i++)
        {
            for(int j = 0; j < m.getJSize(); j++)
            {
                String s = m.getIDCasella(i, j) + "";
                if(m.getStateCasella(i, j) != -1)
                    s += m.getStateCasella(i, j);
                System.out.print(s + "\t");
            }
            System.out.println("");
        }

        System.out.println("Type W to move forward, A to move left, D to move right, E to interact and ESC to exit");
    }

    @Override
    public void communicateError(String message) {
        System.out.println(message);
    }

    @Override
    public void addController(WhereIAmController controller) {
        this.controller = controller;
    }

    @Override
    public void startView() {
        System.out.println("\n\n\n");
        showPosition();
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
                        if(conti)
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
        m = null;
    }
}
