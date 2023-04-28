package model.map;

import model.cell.builder.Removable;

public class Robot {
    private int i; //i
    private int j; //j
    private Orientamento dir;
    private Mappa scacchi;

    public Robot(Mappa mappa)
    {
        this.i = 1;
        this.j = 1;
        this.dir = new Orientamento();
        this.scacchi = mappa;
    }

    public void muovi(Move M) throws IllegalPositionGameException
    {
        if(M == Move.FOR)
        {
            int i = this.i, j = this.j;
            switch(this.dir.get())
            {
                case Destra:
                    if(this.scacchi.getCasella(this.i+1, this.j) == null || this.scacchi.getCasella(this.i+1, this.j) instanceof Removable)
                        this.i++;
                    else 
                        throw new IllegalPositionGameException();
                    break;
                case Alto:
                    if(this.scacchi.getCasella(this.i,this.j + 1) == null || this.scacchi.getCasella(this.i, this.j+1)instanceof Removable)
                        this.j++;
                    else
                        throw new IllegalPositionGameException();
                    break;
                case Sinistra:
                    if(this.scacchi.getCasella(this.i-1, this.j) == null || this.scacchi.getCasella(this.i-1, this.j) instanceof Removable)
                        this.i--;
                    else 
                        throw new IllegalPositionGameException();
                    break;
                case Basso:
                    if(this.scacchi.getCasella(this.i, this.j-1) == null || this.scacchi.getCasella(this.i, this.j-1) instanceof Removable)
                        this.j--;
                    else 
                        throw new IllegalPositionGameException();
                    break;
            }
            this.scacchi.setNewRobotPosition(i, j, this.i, this.j);
        }
        else
            this.dir.aggiorna(M);
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
