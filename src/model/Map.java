package model;

import java.util.Timer;
import java.util.TimerTask;

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

<<<<<<< HEAD
    public void event(Map mappa){
        
    }

    public void startTimer(long delay, long period){
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                event(Map.this);
            }
        }, delay, period);
    }

    public void stopTimer(){
        timer.cancel();
    }
}
=======
    public void event(Map Mappa){

    }
}
>>>>>>> 7fa1971ce99bdd7c6a2a2a7b0316745a5fc430aa
