
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
import view.GUIGameView;
import view.GUIContolView;
import view.ImgBuilder;
import view.TextualView;
import model.element.*;

/**
 * Main.bruh
 */
public class App {

    private static Timer timer;
    public static void main(String[] args) {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};// TODO Immutable HashTable, parserser 
        final boolean scale[] = {false, false, true, true, true, true, true, true};// TODO Immutable HashTable
        // // TODO Immutable HashTable for persentage of random cell
        Map map;
        try
        {
            map = MapBuilder.generateFromFile("mappa.txt", element); // generazione della mappa // TODO map to file TODO random cell
            ImgBuilder imgMatrix = new ImgBuilder(element, scale, map.getISize(), map.getJSize());
            GUIContolView view = new GUIContolView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
            TextualView view2 = new TextualView();
            GUIGameView view3 = new GUIGameView(map, view, imgMatrix);//TODO la x deve esposrtare la mappa su file
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
