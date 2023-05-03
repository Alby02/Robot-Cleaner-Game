
package model.map;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.cell.builder.Cell;
import model.cell.element.*;


public class Mappa{
    Cell mappa[][];
    Robot robot;
    public PropertyChangeSupport support;

    public Mappa() {
        this.mappa = new Cell[10][10];
        this.support = new PropertyChangeSupport(this);
        Azzera();
    }

    public Mappa(int x) throws MapToSmallException {
        if (x < 10)
            throw new MapToSmallException("Dimensioni non consone");
        this.mappa = new Cell[x][x];
        this.support = new PropertyChangeSupport(this);
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
        this.robot = new Robot(this);
        //this.mappa[1][1] = this.robot; //TODO need fixing
    }

    public Cell[][] getMap()
    {
        return this.mappa;
    }

    public Cell getCasella(int i, int j)
    {
        return this.mappa[i][j];
    }

    private Cell randCasella(int i, int j) {
        return null;//new Vuoto(i, j);
    }

    public Robot getRobot()
    {
        return this.robot;
    }

    public void addObserver(PropertyChangeListener observer) {
        this.support.addPropertyChangeListener(observer);
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