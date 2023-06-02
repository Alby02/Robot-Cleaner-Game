package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.exception.FileStructureWrongException;
import model.exception.MapToSmallException;

public class MapBuilder {

    public static Map generateFromFile(String fileName, String[] el) throws FileNotFoundException, FileStructureWrongException{
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        int i;
        Cell mappa[][];
        ArrayList<CellState> cells = new ArrayList<CellState>();
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
                {// TODO better char to integer for faster execution and more cell options
                    Cell c = CellBuilder.create(el, Integer.parseInt(String.valueOf(data.charAt(k))), i, j);
                    mappa[i][j] = c;
                
                    if(c instanceof CellState)
                    {
                        k++;
                        ((CellState)c).setState(Integer.parseInt(String.valueOf(data.charAt(k)))); //TODO better constructor for CellState
                        cells.add((CellState)c);
                    }
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
        return new Map(mappa, cells.toArray(new CellState[0]));
    }

    public static Map generateRandomMap()
    {
        Cell mappa[][] = new Cell[10][10];
        Azzera(mappa);
        return new Map(mappa, new CellState[0]);
    }

    public static Map generateRandomMap(int xSize, int ySize) throws MapToSmallException {
        if (xSize >= 10 || ySize >= 10)
            throw new MapToSmallException("Dimension must greater than 10");
        Cell mappa[][] = new Cell[ySize][xSize];
        Azzera(mappa);
        return new Map(mappa, new CellState[0]);
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
