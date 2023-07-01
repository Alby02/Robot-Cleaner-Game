package model;

import model.Orientamento.Direction;
import model.exception.IllegalPositionGameException;

public class Robot {
    private int i;
    private int j;
    final public Orientamento dir;
    private final Map scacchi;

    protected Robot(Map mappa)
    {
        this.i = 1;
        this.j = 1;
        this.dir = new Orientamento(Direction.Destra);
        this.scacchi = mappa;
    }

    public void forward() throws IllegalPositionGameException
    {
        int newI = this.i, newJ = this.j;
        switch(this.dir.get())
        {
            case Destra:
                newI++;
                break;
            case Alto:
                newJ++;
                break;
            case Sinistra:
                newI--;
                break;
            case Basso:
                newJ--;   
                break;
        }

        this.scacchi.BotMove(this.i, this.j,newI, newJ);
        this.i = newI;
        this.j = newJ;
    }

    public int getCellFacingI()
    {
        int x = this.i;

        switch(this.dir.get())
        {
            case Destra:
                x++;            
                break;   
            case Sinistra:        
                x--;
                break;
            default: 
                break;    
        }

        return x;
    }

    public int getCellFacingJ()
    {
        int y = this.j;

        switch(this.dir.get())
        {
            case Alto:
                y++;
                break;   
            case Basso:        
                y--;
                break;
            default:
                break; 
        }

        return y;
    }

    public int getI()
    {return i;}
    public int getJ()
    {return j;}
}
