
package model.map;

import model.cell.builder.Cell;

public class Mappa{
    Cell mappa[][];
    Robot robot;

    public Mappa(Cell[][] mappa){
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

    public int getSize()
    {
        return this.mappa.length;
    }

    public void setNewRobotPosition(int oldI, int oldJ, int newI, int newJ)
    {
        //this.mappa[newI][newJ] = this.robot; TODO Need fixing
        this.mappa[oldI][oldJ] = null;//new Vuoto(oldI, oldJ);
    }
}