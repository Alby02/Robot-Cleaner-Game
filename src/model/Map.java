package model;

import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;
import model.exception.IllegalPositionGameException;

import java.util.Random;

public class Map{
    private Cell mappa[][];
    private Eventable eventable[];
    public Random nRandom;
    public final Robot robot;

    protected Map(Cell[][] mappa, Eventable eventable[]) {
        this.mappa = mappa;
        this.eventable = eventable;
        this.nRandom = new Random();
        this.robot = new Robot(this);
    }        

    public boolean isCasellaEmpty(int i, int j) 
    {
        return this.mappa[i][j] == null;
    }

    public void BotMove(int oldI, int oldJ, int i, int j) throws IllegalPositionGameException{
        if(this.mappa[i][j] == null || this.mappa[i][j] instanceof Removable)
        {
            this.mappa[oldI][oldJ] = null;
        }
        else
        {
            throw new IllegalPositionGameException("Illegal Position Game Exception");
        }
    }

    public int getIDCasella(int i, int j){
        return this.mappa[i][j].getID();
    }

    public int getStateCasella(int i, int j){
        if(this.mappa[i][j] instanceof CellState)
            return ((CellState)this.mappa[i][j]).getState();
        return 0;
    }

    public int getISize(){
        return this.mappa.length;
    }

    public int getJSize(){
        return this.mappa[0].length;
    }

    protected void setVuotoPosition(int I, int J){
        this.mappa[I][J] = null;//new Vuoto(I, J);
    }

    public void event()
    {
        int number;
        for (int i = 0; i < 5; i++) {
            number = this.nRandom.nextInt(eventable.length);
            try {
                Cell c = this.eventable[number].Event(this);
                System.out.println("new event generated ");
                if (c != null)
                {
                    this.mappa[c.i][c.j] = c;
                }
                break;
            } catch (CantGenerateStateEventException e) {}
        }
    }

    public void interact() throws IllegaInteractnGameException
    {
        int i = this.robot.getCellFacingI(), j = this.robot.getCellFacingJ();
        if (this.mappa[i][j] instanceof Interactable)
        {   
            ((Interactable)this.mappa[i][j]).interact();
        }
        else if (this.mappa[i][j] instanceof Removable)
        {
            this.mappa[i][j] = null;
        }
    }
}
