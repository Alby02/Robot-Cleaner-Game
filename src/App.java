
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
import view.GUIGameView;
import view.GUIControlView;
import view.ImgBuilder;
import view.TextualView;
import model.element.*;

/**
 * Main.bruh
 */
public class App {

    private static Timer timer;
    public static void main(String[] args) {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};// Esistono queste classi
        final boolean scale[] = {false, false, true, true, true, true, true, true};// valore di scala in base alle classi
        // TODO persentage of random cell
        Map map;
        try
        {
            map = MapBuilder.generateFromFile("mappa.txt", element); // generazione della mappa // TODO map to file TODO random cell
            ImgBuilder imgMatrix = new ImgBuilder(element, scale, map.getISize(), map.getJSize());
            GUIControlView view = new GUIControlView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
            TextualView view2 = new TextualView();
            GUIGameView view3 = new GUIGameView(map, view, imgMatrix);//TODO la x deve esposrtare la mappa su file
            WhereIAmController controller = new WhereIAmController(map, element, view, view3, view2);
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
