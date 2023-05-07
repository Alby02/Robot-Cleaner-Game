package model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.cell.builder.Cell;
import model.cell.element.*;

public class MapBuilder {

    public static Map generateFromFile(String fileName) throws FileNotFoundException, FileStructureWrongException{
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
            int MapSize = Integer.parseInt(myReader.nextLine());
            Cell.iScale = MapSize;
            Cell.jScale = MapSize;
            mappa = new Cell[MapSize][MapSize];
            for(i = 0; i < MapSize && myReader.hasNextLine(); i++)
            {
                //per ora va bene, ma non ci sono tutti gli elementi 
                String data = myReader.nextLine();
                for(int j = 0; j < data.length(); j+=2)
                {
                    switch (data.charAt(j)) {
                        case 'W'://wall
                            mappa[i][j/2] = new Wall(i, j/2);
                            break;
                        case 'S'://sink
                            mappa[i][j/2] = new Sink(i, j/2);
                            break;
                        case 'w'://water
                            mappa[i][j/2] = new Water(i, j/2);
                            break;
                        default:
                            mappa[i][j/2] = null;
                            break;
                    }
                }
            }

            if(i != MapSize)
            {   myReader.close();
                throw new FileStructureWrongException("La mappa è stata disegnata male");
            }
        } catch (NumberFormatException e) {
            myReader.close();

            throw new FileStructureWrongException(e.getMessage());
        }
        myReader.close();
        return new Map(mappa);
    }

    public static Map generateRandomMap()
    {
        Cell mappa[][] = new Cell[10][10];
        Azzera(mappa);
        return new Map(mappa);
    }

    public static Map generateRandomMap(int size) throws MapToSmallException {
        return generateRandomMap(size, size);
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
                if (i == 0 || j == 0 || i == mappa.length - 1 || j == mappa[i].length - 1) {
                    mappa[i][j] = new Wall(i, j);
                } else {
                    mappa[i][j] = randCasella(i, j);
                }
            }
        }
    }

    private static Cell randCasella(int i, int j) {
        return null;//new Vuoto(i, j); //TODO generate cosidering percentage
    }
}
