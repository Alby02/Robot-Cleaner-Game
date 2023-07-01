package model;

import model.Orientamento.Direction;
import model.exception.IllegalPositionGameException;

public class Robot {
    private int i;
    private int j;
    final public Orientamento dir;
    private Map scacchi;

    protected Robot(Map mappa)
    {
        this.i = 1;
        this.j = 1;
        this.dir = new Orientamento(Direction.Destra);
        this.scacchi = mappa;
    }

    public void forward() throws IllegalPositionGameException
    {
        switch(this.dir.get())
        {
            case Destra:
                this.scacchi.BotMove(this.i, this.j,this.i+1, this.j);
                this.i++;
                break;
            case Alto:
                this.scacchi.BotMove(this.i, this.j,this.i,this.j + 1);
                this.j++;
                break;
            case Sinistra:
                this.scacchi.BotMove(this.i, this.j,this.i-1, this.j);
                this.i--;
                break;
            case Basso:
                this.scacchi.BotMove(this.i, this.j,this.i, this.j-1);
                this.j--;   
                break;
        }
    }

    public int getCellFacingI()
    {
        int x = this.i;

        switch(this.dir.get())
        {
            case Destra:
                x++;            
                break;
            case Alto:
                break;   
            case Sinistra:        
                x--;
                break;    
            case Basso:        
                break;
        }

        return x;
    }

    public int getCellFacingJ()
    {
        int y = this.j;

        switch(this.dir.get())
        {
            case Destra:            
                break;
            case Alto:
                y++;
                break;   
            case Sinistra:        
                break;    
            case Basso:        
                y--;
                break;
        }

        return y;
    }

    public int getI()
    {return i;}
    public int getJ()
    {return j;}
}
