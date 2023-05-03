import controller.WhereIAmController;
import model.map.MapBuilder;
import model.map.Mappa;
import view.GUIView;
import view.TextualView;

/**
 * Main.bruh
 */
public class App {

    public static void main(String[] args) {
        Mappa map = new Mappa(new MapBuilder().getMap());
        GUIView view = new GUIView(map);
        TextualView view2 = new TextualView();
        WhereIAmController contro = new WhereIAmController(map, view, view2);

        //TODO add thred for random event (passare mappa)

    
    }
}