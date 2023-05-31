
import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
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
            map = MapBuilder.generateFromFile("mappa.txt");
            GUIView view = new GUIView(map);
            TextualView view2 = new TextualView();
            GUIPartialView view3 = new GUIPartialView(map, view);
            WhereIAmController contro = new WhereIAmController(map, view, view3, view2);
            //TODO add thread for random event (passare mappa)
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
