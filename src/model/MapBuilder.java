package model;

import java.awt.Point;
import java.io.CharConversionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
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
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Map generateFromFile(String fileName, Class<?> el[]) throws FileNotFoundException, FileStructureWrongException, CharConversionException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, InvocationTargetException, NoSuchMethodException
    {
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        int i;
        Cell mappa[][];
        CellBuilder builder = new CellBuilder(el);
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
     * Genera una mappa casuale di dimensioni predefinite (10x10).
     *
     * @return la mappa casuale generata
     */
    public static Map generateRandomMap()
    {
        Cell mappa[][] = new Cell[10][10];
        Azzera(mappa);
        return new Map(mappa, null);
    }

    /**
     * Genera una mappa casuale delle dimensioni specificate.
     *
     * @param xSize la dimensione in larghezza della mappa
     * @param ySize la dimensione in altezza della mappa
     * @return la mappa casuale generata
     * @throws MapToSmallException se le dimensioni della mappa sono inferiori a 10
     */
    public static Map generateRandomMap(int xSize, int ySize) throws MapToSmallException {
        if (xSize >= 10 || ySize >= 10)
            throw new MapToSmallException("Dimension must greater than 10");
        Cell mappa[][] = new Cell[ySize][xSize];
        Azzera(mappa);
        return new Map(mappa, null);
    }

    private static void Azzera(Cell[][] mappa) {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                //TODO sistema Azzera random map Cell
                /*if (i == 0 || j == 0 || i == mappa.length - 1 || j == mappa[i].length - 1) {
                    mappa[i][j] = new Wall(i, j);
                } else {
                    mappa[i][j] = randCasella(i, j);
                }*/
            }
        }
    }
}
