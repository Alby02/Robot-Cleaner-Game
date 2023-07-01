package model;

import java.lang.reflect.InvocationTargetException;

public class CellBuilder {
    /*URLClassLoader child = new URLClassLoader(
            new URL[] {myJar.toURI().toURL()},
            this.getClass().getClassLoader()
    );
    Class classToLoad = Class.forName("com.MyClass", true, child);
    Method method = classToLoad.getDeclaredMethod("myMethod");
    Object instance = classToLoad.newInstance();
    Object result = method.invoke(instance);aaaaaaaaaaaaaaaa */

    public static Cell create(Class<?> el[], int l, int i, int j) {
        Cell c = null;
        Class<?> cellClass = el[l];
        if (cellClass != null) {
            try {
                c = (Cell) cellClass.getConstructor(int.class, int.class, int.class).newInstance(i, j, l);
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
            {
                e.printStackTrace();
                System.exit(-100);
            }
        }
        return c;
    }
}
