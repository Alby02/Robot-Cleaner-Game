package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.exception.FileStructureWrongException;
import model.exception.MapToSmallException;

public class MapBuilder {

    public static Map generateFromFile(String fileName, String[] el) throws FileNotFoundException, FileStructureWrongException{
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        int i;
        Cell mappa[][];
        if(!myReader.hasNextLine())
        {
            myReader.close();
            throw new FileStructureWrongException("Il file risulta vuoto");
        }
        try {
            int xMapSize = myReader.nextInt(), yMapSize = Integer.parseInt(myReader.nextLine());
            mappa = new Cell[yMapSize][xMapSize];
            for(i = 0; i < yMapSize && myReader.hasNextLine(); i++)
            {
                String data = myReader.nextLine();
                for(int j = 0, k = 0; j < xMapSize; j++, k+=2)
                {
                    Cell c = CellBuilder.create(el, data.charAt(k), i, j);
                    mappa[i][j] = c;
                
                    if(c instanceof CellState)
                    {
                        k++;
                        ((CellState)c).setState(data.charAt(k));
                    }
                }
            }

            if(i != yMapSize)
            {   myReader.close();
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
        
        return new Map(mappa);
    }

    public static Map generateRandomMap()
    {
        Cell mappa[][] = new Cell[10][10];
        Azzera(mappa);
        return new Map(mappa);
    }

    public static Map generateRandomMap(int xSize, int ySize) throws MapToSmallException {
        if (xSize >= 10 || ySize >= 10)
            throw new MapToSmallException("Dimension must greater than 10");
        Cell mappa[][] = new Cell[ySize][xSize];
        Azzera(mappa);
        return new Map(mappa);
    }

    private static void Azzera(Cell[][] mappa) {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                //TODO sistema
                /*if (i == 0 || j == 0 || i == mappa.length - 1 || j == mappa[i].length - 1) {
                    mappa[i][j] = new Wall(i, j);
                } else {
                    mappa[i][j] = randCasella(i, j);
                }*/
            }
        }
    }

    
}
