package model;

public class Map{
    private Cell mappa[][];
    public final Robot robot;

    protected Map(Cell[][] mappa){
        this.mappa = mappa;
        this.robot = new Robot(this);
    }        

    public Cell getCasella(int i, int j)
    {
        return this.mappa[i][j];
    }

    public int getISize(){
        return this.mappa.length;
    }

    public int getJSize(){
        return this.mappa[0].length;
    }

    protected void setNewRobotPosition(int oldI, int oldJ, int newI, int newJ)
    {
        //TODO Need fixing
        this.mappa[oldI][oldJ] = null;//new Vuoto(oldI, oldJ);
    }

    public void enent()
    {}
}