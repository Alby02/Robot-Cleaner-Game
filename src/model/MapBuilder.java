package model;

import java.awt.Point;
import java.io.CharConversionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import model.exception.FileStructureWrongException;
import model.exception.MapToSmallException;

public class MapBuilder {
    /**
     * Genera una mappa leggendo i dati da un file di testo.
     * Se la cella è un'istanza di Eventable, si aggiunge la cella.
     * Se la cella è un'istanza di CellState, aggiunge lo stato alla cella.
     *
     * @param fileName il nome del file di testo contenente la struttura della mappa
     * @param el l'array delle classi delle celle utilizzate nella mappa
     * @return la mappa generata
     * @throws FileNotFoundException se il file specificato non viene trovato
     * @throws FileStructureWrongException se la struttura del file di testo è errata
     * @throws CharConversionException nel caso in cui c'è un errore di conversione da int a char
     * @throws ReflectiveOperationException
     * @throws IllegalArgumentException
     */
    public static Map generateFromFile(String fileName, Class<?> el[]) throws FileNotFoundException, FileStructureWrongException, CharConversionException, IllegalArgumentException, ReflectiveOperationException
    {
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        int i;
        Cell mappa[][];
        CellBuilder.IdClassTable builder = new CellBuilder.IdClassTable(el);
        Point a = new Point(1, 1);
        try {
            if(!myReader.hasNextLine())
            {
                throw new FileStructureWrongException("Il file risulta vuoto");
            }
            int xMapSize = Integer.parseInt(myReader.nextLine());//colonne
            int yMapSize = Integer.parseInt(myReader.nextLine());//righe
            mappa = new Cell[yMapSize][xMapSize];
            for(i = 0; i < yMapSize && myReader.hasNextLine(); i++)
            {
                String data = myReader.nextLine();
                for(int j = 0, k = 0; j < xMapSize; j++, k+=2)
                {
                    char t = data.charAt(k);
                    Class<?> g = builder.getClassByID(t);
                    
                    Cell c = null;
                    if (g != null){
                        if(CellState.class.isAssignableFrom(g))
                        {
                            k++;
                            c = CellBuilder.createCellState(g, i, j, CellBuilder.charParser(data.charAt(k)));
                        }
                        else 
                        {
                            c = CellBuilder.createCell(g, i, j);
                        }
                    }
                    else if(t == 'R' || t == 'r'){
                        a.x = i;
                        a.y = j;
                    }   
                    mappa[i][j] = c;
                }
            }

            if(i != yMapSize)
            {   
                throw new FileStructureWrongException("La mappa è stata disegnata male");
            }
        } 
        catch (NumberFormatException e)
        {
            throw new FileStructureWrongException(e.getMessage());
        }
        finally
        {
            myReader.close();
        }
        return new Map(mappa, a);
    }

    /**
     * Genera una mappa casuale di dimensioni scelte.
     *
     * @param ce l'array delle classi delle celle utilizzate nella mappa
     * @param probability un array di interi che rappresenta la probabilità di selezione delle classi di celle
     * @return la mappa casuale generata
     * @throws ReflectiveOperationException se si verifica un errore di riflessione durante la generazione della mappa
     */
    public static Map generateRandomMap(Class<?>[] ce, int[] probability) throws ReflectiveOperationException
    {
        Map m = null;
        try {
            m =  generateRandomMap(10, ce, probability);
        } catch (MapToSmallException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return m;
    }

    public static Map generateRandomMap(int size, Class<?>[] ce, int[] probability) throws MapToSmallException, ReflectiveOperationException
    {
        return generateRandomMap(size, size, ce, probability);
    }

    /**
     * Genera una mappa casuale delle dimensioni specificate.
     *
     * @param xSize la dimensione in larghezza della mappa
     * @param ySize la dimensione in altezza della mappa
     * @param ce l'array delle classi delle celle utilizzate nella mappa
     * @param probability un array di interi che rappresenta la probabilità di selezione delle classi di celle
     * @return la mappa casuale generata
     * @throws MapToSmallException se le dimensioni della mappa sono inferiori a 10
     * @throws ReflectiveOperationException se si verifica un errore di riflessione durante la generazione della mappa
     */
    public static Map generateRandomMap(int xSize, int ySize, Class<?>[] ce, int[] probability) throws MapToSmallException, ReflectiveOperationException {
        if (xSize < 10 || ySize < 10)
            throw new MapToSmallException("Dimension must greater than 10");
        Cell mappa[][] = new Cell[ySize][xSize];
        Azzera(mappa, ce, probability, inderxOfNegative(probability));
        return new Map(mappa, new Point(1, 1));
    }

    /**
     * Restituisce l'indice del primo valore negativo nell'array specificato.
     *
     * @param pro l'array di interi
     * @return l'indice del primo valore negativo
     * @throws IllegalArgumentException se tutti i valori nell'array sono non negativi
     */
    private static int inderxOfNegative(int[] pro){
        int i;
        for(i = 0; i < pro.length && pro[i] >= 0; i++);
        if(i == pro.length)
            throw new IllegalArgumentException("non hai messo le 'probabilità corrette");
        return i;
    }

    /**
     * Calcola la somma dei valori assoluti dell'array specificato.
     *
     * @param pro l'array di interi
     * @return la somma dei valori assoluti
     */
    private static int sommaPro(int[] pro)
    {
        int soomma, i;
        for(i = 0, soomma = 0 ; i < pro.length; soomma+=Math.abs(pro[i]), i++);
        return soomma;
    }

    /**
     * Restituisce l'indice dell'array in cui la somma dei valori assoluti è superiore al valore specificato.
     *
     * @param pro l'array di interi
     * @param val il valore di confronto
     * @return l'indice dell'array in cui la somma supera il valore specificato
     */
    private static int getIndexProb(int[] pro, int val)
    {
        int so, i;
        for(i = 0, so = 0; i < pro.length && so + Math.abs(pro[i]) < val ; so+=Math.abs(pro[i]), i++);
        return i;
    }

    /**
     * Restituisce il numero di stati della classe specificata.
     *
     * @param c la classe delle celle
     * @return il numero di stati della classe
     * @throws ReflectiveOperationException se si verifica un errore di riflessione durante il recupero degli stati
     */
    private static int getNOfStates(Class<?> c) throws ReflectiveOperationException
    {
        return ((String[])c.getDeclaredMethod("getStates").invoke(null)).length;
    }

    /**
     * Inizializza la mappa con celle casuali utilizzando le classi, le probabilità e il bordo specificati.
     *
     * @param mappa la mappa di celle
     * @param ce l'array delle classi delle celle
     * @param probability un array di interi che rappresenta la probabilità di selezione delle classi di celle
     * @param Border l'indice della classe di cella da utilizzare per il bordo
     * @throws ReflectiveOperationException se si verifica un errore di riflessione durante la creazione delle celle
     */
    private static void Azzera(Cell[][] mappa,Class<?>[] ce, int[] probability, int Border) throws ReflectiveOperationException{

        int ProTot = sommaPro(probability); 
        Random nR = new Random();
        int r;

        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                if (i == 0 || j == 0 || i == mappa.length - 1 || j == mappa[i].length - 1) {
                    mappa[i][j] = CellBuilder.createCell(ce[Border], i, j);
                } else if(!(i== 1 && j==1)){
                    r = getIndexProb(probability, nR.nextInt(ProTot));
                    if(ce[r] != null)
                    {
                        if(CellState.class.isAssignableFrom(ce[r])){
                            mappa[i][j] = CellBuilder.createCellState(ce[r], i, j, nR.nextInt(getNOfStates(ce[r])));
                        }else{
                            mappa[i][j] = CellBuilder.createCell(ce[r], i, j);
                        }
                    }                    
                }
            }
        }
    }

    /**
     * Scrive la mappa specificata su un file di testo.
     *
     * @param fileName il nome del file di testo in cui scrivere la mappa
     * @param m la mappa da scrivere
     * @param el l'array delle classi delle celle utilizzate nella mappa
     * @throws IOException se si verifica un errore durante la scrittura del file
     * @throws ReflectiveOperationException se si verifica un errore di riflessione durante la scrittura del file
     */
    public static void toFile(String FileName, Map m, Class<?> el[]) throws IOException, ReflectiveOperationException
    {
        File dir = new File("saves");
        if(!dir.exists())
            dir.mkdir();
        FileWriter wr = new FileWriter(new File("saves/"+FileName));
        CellBuilder.IdClassTable builder = new CellBuilder.IdClassTable(el);
        wr.write(Integer.toString(m.getISize()) + '\n');
        wr.write(Integer.toString(m.getJSize()) + '\n');
        for(int i = 0 ; i < m.getISize(); i++)
        {
            String s = "";
            for(int j = 0; j < m.getJSize(); j++)
            {
                char ID = m.getIDCasella(i, j);
                if(ID == '0' && m.robot.getI() == i && m.robot.getJ() == j)
                {
                    ID = 'R';
                }
                    
                if(builder.getClassByID(ID) != null && m.getStateCasella(i, j)!=-1)
                {
                    s += ID + Integer.toString(m.getStateCasella(i, j)) + " ";
                }
                else
                {
                    s += ID + " ";
                }
            }
            wr.write(s + '\n');
        } 
        wr.close();
    }
}
