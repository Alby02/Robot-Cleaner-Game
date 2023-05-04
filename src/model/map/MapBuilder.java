package model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.cell.builder.Cell;
import model.cell.element.Sink;
import model.cell.element.Wall;
import model.cell.element.Water;

public class MapBuilder {

    private Cell mappa[][];

    public MapBuilder() {
        this.mappa = new Cell[10][10];
        Azzera();
    }

    public MapBuilder(int x) throws MapToSmallException {
        if (x < 10)
            throw new MapToSmallException("Dimensioni non consone");
        this.mappa = new Cell[x][x];
        Azzera();
    }

    public MapBuilder(String nome) throws FileNotFoundException, FileStructureWrongException{
        File file = new File(nome);
        Scanner myReader = new Scanner(file);
        int i;
        if(!myReader.hasNextLine())
        {
            myReader.close();
            throw new FileStructureWrongException("Il file risulta vuoto");
        }
        try {
            int MapSize = Integer.parseInt(myReader.nextLine());
            this.mappa = new Cell[MapSize][MapSize];
            for(i = 0; i < MapSize && myReader.hasNextLine(); i++)
            {
                //per ora va bene, ma non ci sono tutti gli elementi 
                String data = myReader.nextLine();
                for(int j = 0; j < data.length(); j+=2)
                {
                    switch (data.charAt(j)) {
                        case 'W'://wall
                            this.mappa[i][j/2] = new Wall(i, j/2);
                            break;
                        case 'S'://sink
                            this.mappa[i][j/2] = new Sink(i, j/2);
                            break;
                        case 'w'://water
                            this.mappa[i][j/2] = new Water(i, j/2);
                            break;
                        default:
                            this.mappa[i][j/2] = null;
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
    }

    public void Azzera() {
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

    private Cell randCasella(int i, int j) {
        return null;//new Vuoto(i, j);
    }

    public Cell[][] getMap(){
        return this.mappa;
    }
}
