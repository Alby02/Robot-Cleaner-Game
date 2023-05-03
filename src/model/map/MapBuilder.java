package model.map;

import model.cell.builder.Cell;
import model.cell.element.Wall;

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
