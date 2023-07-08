
import model.element.Cat;
import model.element.Fire;
import model.element.Oven;
import model.element.Sink;
import model.element.Wall;
import model.element.Washer;
import model.element.Water;
import view.Menu;

/**
 * Main.bruh
 */
public class App {

    public static void main(String[] args) {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};// Esistono queste classi
        final boolean scale[] = {false, false, true, true, true, true, true, true};// valore che indica se l'mmagine va scalata in base alle classi
        final int[] probability = {100, -25, 10, 5, 5, 5, 25, 5}; //probabilità che spawni un elemento 
        Menu m = new Menu(element, scale, probability);
        m.startView();
    }
}
