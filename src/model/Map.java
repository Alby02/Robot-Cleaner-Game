package model;

import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

import java.util.Random;

public class Map{
    private Cell mappa[][];
    private CellState eventable[];
    private Random nRandom;
    public final Robot robot;

    protected Map(Cell[][] mappa, CellState eventable[]) {
        this.mappa = mappa;
        this.eventable = eventable;
        this.nRandom = new Random();
        this.robot = new Robot(this);
    }        

    public Cell getCasella(int i, int j){ // block access to casella 
        return this.mappa[i][j];
    }

    public int getISize(){
        return this.mappa.length;
    }

    public int getJSize(){
        return this.mappa[0].length;
    }

    protected void setNewRobotPosition(int oldI, int oldJ, int newI, int newJ){
        //TODO Need fixing Robot position
        this.mappa[oldI][oldJ] = null;//new Vuoto(oldI, oldJ);
    }


    public void event()
    {
        int number;
        for (int i = 0; i < 5; i++) {
            number = this.nRandom.nextInt(eventable.length);
            try {
                Cell c = this.eventable[number].Event(this);
                System.out.println("new event generated ");
                if (c != null) {
                    System.out.println("Generated Water " + c.i + " " + c.j);
                    this.mappa[c.i][c.j] = c;
                }
                break;
            } catch (CantGenerateStateEventException e) {}
        }
    }

    public void interact() throws IllegaInteractnGameException
    {
        int i = this.robot.getCellFacingI(), j = this.robot.getCellFacingJ();
        if (this.mappa[i][j] instanceof CellState)
        {   
            ((CellState)this.mappa[i][j]).interact();
        }
    }
}
