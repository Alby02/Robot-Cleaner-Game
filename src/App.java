import model.element.*;
import view.Menu;

/**
 * Main.bruh
 */
public class App {

    public static void main(String[] args) {
        Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};// Esistono queste classi
        boolean scale[] = {false, false, true, true, true, true, true, true};// valore che indica se l'mmagine va scalata in base alle classi
        int[] probability = {100, -25, 10, 5, 5, 5, 25, 5}; //probabilità che spawni un elemento 
        Menu m = new Menu(element, scale, probability);
        m.startView();
    }
}
