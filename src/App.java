
import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
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
            WhereIAmController contro = new WhereIAmController(map, view, view2);
            //TODO add thred for random event (passare mappa)
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }    
    }
}