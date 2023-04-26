package model.map;

import model.Elemento;
import model.cell.builder.Ostacolo;

public class Robot implements Elemento {
    private int x; //i
    private int y; //j
    private Orientamento dir;
    private Mappa scacchi;

    public Robot(Mappa mappa)
    {
        this.x = 1;
        this.y = 1;
        this.dir = new Orientamento();
        this.scacchi = mappa;
    }

    public void muovi(Move M) throws IllegalPositionGameException
    {
        if(M == Move.FOR)
        {
            int i = this.x, j = this.y;
            switch(this.dir.get())
            {
                case Destra:
                    if(!(this.scacchi.getCasella(this.x+1, y) instanceof Ostacolo))
                        this.x++;
                    else 
                        throw new IllegalPositionGameException();
                    break;
                case Alto:
                    if(!(this.scacchi.getCasella(x, this.y+1) instanceof Ostacolo))
                        this.y++;
                    else
                        throw new IllegalPositionGameException();
                    break;
                case Sinistra:
                    if(!(this.scacchi.getCasella(this.x-1, y) instanceof Ostacolo))
                        this.x--;
                    else 
                        throw new IllegalPositionGameException();
                    break;
                case Basso:
                    if(!(this.scacchi.getCasella(x, this.y-1) instanceof Ostacolo))
                        this.y--;
                    else 
                        throw new IllegalPositionGameException();
                    break;
            }
            this.scacchi.setNewRobotPosition(i, j, this.x, this.y);
        }
        else
            this.dir.aggiorna(M);
    }


    public int getCellFacingI()
    {
        int x = this.x;

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
        int y = this.y;

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


    

    
}
