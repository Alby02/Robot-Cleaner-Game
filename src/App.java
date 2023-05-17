
import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
import model.element.Sink;
import model.timer.EventTimer;
import model.timer.SinkTimer;
import view.GUIPartialView;
import view.GUIView;
import view.TextualView;

/**
 * Main.bruh
 */
public class App {

    public static void main(String[] args) {
        
        Map map;
        try {
            map = MapBuilder.generateFromFile("mappa.txt"); // generazione della mappa
            GUIView view = new GUIView(map); // generazione della Gui grafica in base alla struttura del modello
            TextualView view2 = new TextualView();
            GUIPartialView view3 = new GUIPartialView(map, view);
            WhereIAmController contro = new WhereIAmController(map, view, view3, view2);
            Sink sink = new Sink (0, 0);
            SinkTimer sinkTimer = new SinkTimer(0, 10000);
            sinkTimer.startTimer();
            //TODO add thred for random event (passare mappa)
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        sinkTimer.stopTimer();
    }
}