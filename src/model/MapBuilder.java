package model;

import java.awt.Point;
import java.io.CharConversionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
     * Genera una mappa casuale di dimensioni predefinite (10x10).
     *
     * @return la mappa casuale generata
     */
    public static Map generateRandomMap()
    {
        Map m = null;
        try {
            m =  generateRandomMap(10);
        } catch (MapToSmallException e) {
            System.err.println("Sei un cretino");
            e.printStackTrace();
            System.exit(-1);
        }
        return m;
    }

    public static Map generateRandomMap(int size) throws MapToSmallException
    {
        return generateRandomMap(size, size);
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
        if (xSize < 10 || ySize < 10)
            throw new MapToSmallException("Dimension must greater than 10");
        Cell mappa[][] = new Cell[ySize][xSize];
        Azzera(mappa);
        return new Map(mappa, new Point(1, 1));
    }

    private static void Azzera(Cell[][] mappa) {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                if (i == 0 || j == 0 || i == mappa.length - 1 || j == mappa[i].length - 1) {
                    mappa[i][j] = new model.element.Wall(i, j);// brutto
                } else if(!(i== 1 && j==1)){
                    mappa[i][j] = null;
                    //TODO sistema Azzera random map Cell
                }
            }
        }
    }

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
