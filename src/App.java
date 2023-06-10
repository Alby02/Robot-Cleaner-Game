
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
import view.GUIPartialView;
import view.GUIView;
import view.ImgBuilder;
import view.TextualView;

/**
 * Main.bruh
 */
public class App {

    private static Timer timer;
    public static void main(String[] args) {
        final String element[] = {"Vuoto", "Wall", "Fire", "Oven", "Sink", "Washer", "Water", "Cat"};// TODO Aggiungere eo sostituire con Immutable list of class 
        final boolean scale[] = {false, false, true, true, true, true, true, true};// TODO Immutable list
        // TODO Immutable list for persentage of random cell
        Map map;
        try {
            map = MapBuilder.generateFromFile("mappa.txt", element); // generazione della mappa // TODO Class immutable list e mapp to file random cell
            final ImageIcon imgMatrix[][] = ImgBuilder.createImgMatrix(element, scale, map.getISize(), map.getJSize()); //TODO non è un oggetto immutabile solo il riferim ento lo è
            GUIView view = new GUIView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello, TODO la x deve esposrtare la mappa su file
            TextualView view2 = new TextualView();
            GUIPartialView view3 = new GUIPartialView(map, view, imgMatrix);// TODO rimozione dei pulsanti utilizza la x solo per far sparire la gui non per  chidere
            WhereIAmController controller = new WhereIAmController(map, view, view3, view2);
            //TODO add thread for random event (passare mappa)
            startTimer(2500, controller);
        } catch (Exception e) {
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

    public static void stopTimer(){
        timer.stop();
    }
    /*public static class TestTemporanei{

        public static class Test
        {
            public int x;
        }
        
        static void questo (final Test t[])
        {
            Test y = new Test();
            y.x = 1;
            t[0] = y;
        }

        static Test[] quello ()
        {
            final Test t[] = {new Test()};
            return t;
        }

        public static void main(String[] args) {
            
            final Test t[] = quello();
            t[0].x = 10;
            questo(t);
            System.out.println(t[0].x);
        }
    }*/
}
