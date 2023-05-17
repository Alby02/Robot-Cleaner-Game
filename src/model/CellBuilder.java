package model;

import model.element.*;;

public class CellBuilder {
/*URLClassLoader child = new URLClassLoader(
        new URL[] {myJar.toURI().toURL()},
        this.getClass().getClassLoader()
);
Class classToLoad = Class.forName("com.MyClass", true, child);
Method method = classToLoad.getDeclaredMethod("myMethod");
Object instance = classToLoad.newInstance();
Object result = method.invoke(instance);aaaaaaaaaaaaaaaa */

    private static String e[] = null;
    public static void setClassElementsNames(String e[])
    {
        CellBuilder.e = e;
    }

    public static Cell createElementFromLetter(char l)
    {
        Cell c = null;
        if(!(l == 'N' || l == 'n'))
        {
            try {
                int sas = (int) Class.forName("model.element.Wall").getField("Scale").get(Cell.class);
                System.out.println(sas);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return c;
    }


    public static void main(String[] args) {
        Cell sas = createElementFromLetter('a');
    }
}
