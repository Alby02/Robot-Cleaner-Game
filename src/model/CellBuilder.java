package model;

import java.io.CharConversionException;


public class CellBuilder {
    /*URLClassLoader child = new URLClassLoader(
            new URL[] {myJar.toURI().toURL()},
            this.getClass().getClassLoader()
    );
    Class classToLoad = Class.forName("com.MyClass", true, child);
    Method method = classToLoad.getDeclaredMethod("myMethod");
    Object instance = classToLoad.newInstance();
    Object result = method.invoke(instance); Ma chi me lo fa fare*/

    /**
     * Crea un'istanza di una sottoclasse di Cell specificata dalla classe passata come parametro.
     *
     * @param el un array di classi che rappresentano le possibili sottoclassi di Cell
     * @param l  l'indice corrispondente alla sottoclasse desiderata nell'array el
     * @param i  l'indice di colonna della cella da creare
     * @param j  l'indice di riga della cella da creare
     * @return di una istanza della sottoclasse di Cell corrispondente all'indice l
     * @throws ReflectiveOperationException
     * 
     */
    public static Cell createCell(Class<?> el, int i, int j) throws ReflectiveOperationException
    {
        return (Cell) el.getConstructor(int.class, int.class).newInstance(i, j);
    }

    public static CellState createCellState(Class<?> el, int i, int j, int s) throws ReflectiveOperationException
    {
        return (CellState) el.getConstructor(int.class, int.class, int.class).newInstance(i, j, s);
    }

    public static int charParser (char c) throws CharConversionException
    {
        int x;
        if(c >= 'a' && c <= 'z') //codice da 10 a 35
            x = c - 87;
        else if(c >= 'A' && c <= 'Z') //codice da 10 a 35
            x = c - 55;
        else if(c >= '0' && c <= '9') //codice da 0 a 9
            x = c - 48;
        else
            throw new CharConversionException("Carattere non ammesso " + (int)c);
        return x;
    }

    static class IdClassTable
    {
        private Class<?> table[];

        public IdClassTable(Class<?> el[]) throws CharConversionException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
        {
            this.table = new Class[36];
            java.util.Arrays.fill(this.table, null);
            for(int i = 1; i < el.length; i++)
            {
                int p = charParser(((char)el[i].getDeclaredField("ID").get(null)));
                this.table[p] = el[i];
            }
        }

        public Class<?> getClassByID(char ID) throws CharConversionException
        {
            return this.table[charParser(ID)];
        }

    }
}
