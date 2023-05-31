
import javax.swing.ImageIcon;

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

    public static void main(String[] args) {
        String element[] = {"Vuoto", "Wall", "Fire", "Oven", "Sink", "Washer", "Water"};
        boolean scale[] = {false, false, true, true, true, true, true};
        Map map;
        try {
            map = MapBuilder.generateFromFile("mappa.txt", element); // generazione della mappa
            final ImageIcon imgMatrix[][] = ImgBuilder.createImgMatrix(element, scale, map.getISize(), map.getJSize());
            GUIView view = new GUIView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
            TextualView view2 = new TextualView();
            GUIPartialView view3 = new GUIPartialView(map, imgMatrix);
            WhereIAmController contro = new WhereIAmController(map, view, view3, view2);
            //TODO add thred for random event (passare mappa)
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }    
    }
}