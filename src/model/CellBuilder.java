package model;

import java.lang.reflect.InvocationTargetException;

public class CellBuilder {

    public static Cell create(String[] el, int l, int i, int j) {
        Cell c = null;
        if (!(l == 0 || l == 0)) {
            try {
                Class<?> cellClass = Class.forName("model.element." + el[l]);
                c = (Cell) cellClass.getConstructor(int.class, int.class, int.class).newInstance(i, j, l);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(-100);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-100);
            }
        }
        return c;
    }
}
