
package model.map;

import model.cell.builder.Cell;

public class Map{
    Cell mappa[][];
    Robot robot;

    protected Map(Cell[][] mappa){
        this.mappa = mappa;
        this.robot = new Robot(this);
    }        

    public Cell[][] getMap()
    {
        return this.mappa;
    }

    public Cell getCasella(int i, int j)
    {
        return this.mappa[i][j];
    }

    public Robot getRobot()
    {
        return this.robot;
    }

    public int getISize(){
        return this.mappa.length;
    }

    public int getJSize(){
        return this.mappa[0].length;
    }

    public void setNewRobotPosition(int oldI, int oldJ, int newI, int newJ)
    {
        //TODO Need fixing
        this.mappa[oldI][oldJ] = null;//new Vuoto(oldI, oldJ);
    }
}