package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.element.Oven;
import model.element.Sink;
import model.element.Wall;
import model.element.Washer;
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
                {
                    Cell c = CellBuilder.create(el, Character.getNumericValue((data.charAt(k))), i, j);//forse va meglio
                    //Cell c = CellBuilder.create(el, Integer.parseInt(String.valueOf(data.charAt(k))), i, j);
                    mappa[i][j] = c;
                
                    if(c instanceof CellState)
                    {
                        k++;
                        ((CellState)c).setState(Character.getNumericValue(data.charAt(k))); //forse va meglio
                        //((CellState)c).setState(Integer.parseInt(String.valueOf(data.charAt(k))));
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

    private static void saveMapToFile(Cell[][] mappa, String fileName) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);

            for (Cell[] row : mappa) {
                for (Cell cell : row) {
                    int id = (cell != null) ? cell.getID() : 0; //crea caselle vuote oppure caselle con qualcosa
                    writer.write(id + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map generateRandomMap(int xSize, int ySize) throws MapToSmallException {
        if (xSize < 10 || ySize < 10)
            throw new MapToSmallException("Dimension must greater than 10");
        Cell mappa[][] = new Cell[ySize][xSize];
        Azzera(mappa);
        saveMapToFile(mappa, "mappa.txt");
        return new Map(mappa, new CellState[0]);
    }

    private static void Azzera(Cell[][] mappa) {
        int ovenCount = 0;
        int sinkCount = 0;
        int washerCount = 0;

        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                if (i == 0 || j == 0 || i == mappa.length - 1 || j == mappa[i].length - 1) {
                    mappa[i][j] = new Wall(i, j, 1);
                } else {
                    double randomValue = Math.random();

                    if (ovenCount < 1 && randomValue < 0.05) {
                        mappa[i][j] = new Washer(i, j, 3);
                        ovenCount++;
                    } else if (sinkCount < 1 && randomValue < 0.05) {
                        mappa[i][j] = new Sink(i, j, 4);
                        sinkCount++;
                    } else if(washerCount < 1 && randomValue < 0.05) {
                        mappa[i][j] = new Oven(i, j, 5);
                        washerCount++;
                    }
                }
            }
        }
    }
}
