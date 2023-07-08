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

    /**
     * Crea una nuova istanza di `CellState` utilizzando il costruttore della classe.
     *
     * @param el la classe della cella di stato
     * @param i l'indice di colonna della cella da creare
     * @param j l'indice di riga della cella da creare
     * @param s lo stato della cella
     * @return la nuova istanza di `CellState` creata
     * @throws ReflectiveOperationException se si verifica un errore di riflessione durante la creazione dell'istanza
     */
    public static CellState createCellState(Class<?> el, int i, int j, int s) throws ReflectiveOperationException
    {
        return (CellState) el.getConstructor(int.class, int.class, int.class).newInstance(i, j, s);
    }

    /**
     * Converte un carattere in un intero utilizzando le convenzioni.
     *
     * @param c il carattere da convertire
     * @return l'intero corrispondente al carattere
     * @throws CharConversionException se il carattere non è ammesso
     */
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
    
    /**
     * Una tabella di associazione tra ID di celle e classi di celle.
     */
    static class IdClassTable
    {
        private Class<?> table[];
        /**
         * Costruisce una nuova istanza di `IdClassTable` utilizzando un array di classi.
         *
         * @param el l'array delle classi delle celle
         * @throws CharConversionException se si verifica un errore durante la conversione del carattere ID
         * @throws IllegalArgumentException se si verifica un errore durante la creazione dell'istanza
         * @throws IllegalAccessException se si verifica un errore durante l'accesso al campo ID
         * @throws NoSuchFieldException se si verifica un errore se il campo ID non viene trovato
         * @throws SecurityException se si verifica un errore di sicurezza durante l'accesso al campo ID
         */
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
        
        /**
         * Restituisce la classe di cella associata all'ID.
         *
         * @param ID l'ID della cella
         * @return la classe di cella associata all'ID, o null se non esiste un'associazione
         * @throws CharConversionException se si verifica un errore durante la conversione del carattere ID
         */
        public Class<?> getClassByID(char ID) throws CharConversionException
        {
            return this.table[charParser(ID)];
        }

    }
}
