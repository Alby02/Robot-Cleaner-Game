import src.controller.WhereIAmController;
import src.model.map.Mappa;
import src.view.GUIView;
import src.view.TextualView;

/**
 * Main.bruh
 */
public class Main {

    public static void main(String[] args) {
        Mappa map = new Mappa();
        GUIView view = new GUIView(map);
        TextualView view2 = new TextualView();
        WhereIAmController contro = new WhereIAmController(map, view, view2);


        //Test susu
    }
}