package model;

import java.awt.Point;

import model.Orientamento.Direction;
import model.exception.IllegalPositionGameException;

public class Robot {
    private int i;
    private int j;
    final public Orientamento dir;
    private final Map scacchi;

    /**
     * Costruttore della classe Robot.
     *
     * @param mappa la mappa di gioco a cui il robot appartiene
     */
    protected Robot(Map mappa, Point p)
    {
        this.i = p.x;
        this.j = p.y;
        this.dir = new Orientamento(Direction.Destra);
        this.scacchi = mappa;
    }

    /**
     * Muove il robot in avanti nella direzione corrente.
     *
     * @throws IllegalPositionGameException se la posizione di destinazione non è valida
     */
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

    /**
     * Restituisce l'indice i della cella adiacente nella direzione corrente,
     * che si trova nella cella subito davanti al robot
     *
     * @return l'indice i della cella adiacente
     */
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

    /**
     * Restituisce l'indice j della cella adiacente nella direzione corrente,
     * che si trova nella cella subito davanti al robot
     *
     * @return l'indice j della cella adiacente
     */
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

    /**
     * Restituisce l'indice i corrente del robot.
     *
     * @return l'indice i corrente
     */
    public int getI() {
        return i;
    }

    /**
     * Restituisce l'indice j corrente del robot.
     *
     * @return l'indice j corrente
     */
    public int getJ() {
        return j;
    }
}
