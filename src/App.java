
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
import view.GUIPartialView;
import view.GUIView;
import view.ImgBuilder;
import view.TextualView;
import model.element.*;

/**
 * Main.bruh
 */
public class App {

    private static Timer timer;
    public static void main(String[] args) {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};// TODO Immutable list
        final boolean scale[] = {false, false, true, true, true, true, true, true};// TODO Immutable list
        // TODO Immutable list for persentage of random cell
        Map map;
        try
        {
            map = MapBuilder.generateFromFile("mappa.txt", element); // generazione della mappa // TODO map to file //TODO random cell
            ImgBuilder imgMatrix = new ImgBuilder(element, scale, map.getISize(), map.getJSize());
            GUIView view = new GUIView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello, TODO la x deve esposrtare la mappa su file
            TextualView view2 = new TextualView();
            GUIPartialView view3 = new GUIPartialView(map, view, imgMatrix);// TODO rimozione dei pulsanti utilizza la x solo per far sparire la gui non per  chidere
            WhereIAmController controller = new WhereIAmController(map, view, view3, view2);
            startTimer(5000, controller);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }    

    }

    public static void startTimer(int delay, ActionListener actionListener){
        timer = new Timer(delay, actionListener);
        timer.setActionCommand(".");
        timer.setInitialDelay(delay);
        timer.start();
    }
}
