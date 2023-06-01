package model;

import javax.swing.Timer;

import java.awt.event.*;

public class Map{
    private Cell mappa[][];
    public final Robot robot;
    private Timer timer;

    protected Map(Cell[][] mappa){
        this.mappa = mappa;
        this.robot = new Robot(this);
    }        

    public Cell getCasella(int i, int j){
        return this.mappa[i][j];
    }

    public int getISize(){
        return this.mappa.length;
    }

    public int getJSize(){
        return this.mappa[0].length;
    }

    protected void setNewRobotPosition(int oldI, int oldJ, int newI, int newJ){
        //TODO Need fixing
        this.mappa[oldI][oldJ] = null;//new Vuoto(oldI, oldJ);
    }


    public void event(){
        
    }

    public void startTimer(int delay){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event){
                event();
            }
        };
        this.timer = new Timer(delay, actionListener);
        this.timer.setInitialDelay(delay);
        this.timer.start();
    }

    public void stopTimer(){
        this.timer.stop();
    }
}
