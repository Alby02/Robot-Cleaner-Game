package model;

import model.exception.CantGenerateEventException;
import model.exception.IllegaInteractnGameException;
import model.exception.IllegalPositionGameException;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class Map{
    private Cell mappa[][]; //Matrice delle celle della mappa
    private LinkedList<Eventable> eventable; // Array degli oggetti che possono generare eventi
    private final Random nRandom; // Generatore di numeri casuali
    public final Robot robot; // Il robot presente nella mappa

    /**
     * Costruisce un oggetto Map con la mappa e gli oggetti eventabili specificati.
     *
     * @param mappa la matrice delle celle che compongono la mappa
     * @param p coordinate di ROBOT
     */
    public Map(Cell[][] mappa, Point p) {
        this.mappa = mappa;
        this.nRandom = new Random();
        this.robot = new Robot(this, p);
        generateSupportArray();
    }        

    private void generateSupportArray()
    {
        this.eventable = new LinkedList<Eventable>();
        for(int i = 0; i < this.mappa.length; i++)
        {
            for(int j = 0; j < this.mappa[i].length; j++)
            {
                if(this.mappa[i][j] instanceof Eventable)
                {
                    this.eventable.add((Eventable)this.mappa[i][j]);
                }
            }
        }
    }

    /**
     * Verifica se la cella nella posizione specificata è vuota.
     *
     * @param i l'indice di colonna della cella
     * @param j l'indice di riga della cella
     * @return della cella vuota
     */
    public boolean isCasellaEmpty(int i, int j) 
    {
        return this.mappa[i][j] == null && !(this.robot.getI() == i && this.robot.getJ() == j);
    }

    /**
     * Sposta il robot dalla posizione precedente alla posizione specificata.
     * Se la cella nella nuova posizione è vuota o può essere rimossa, il robot si sposta.
     * Altrimenti, viene sollevata un'eccezione IllegalPositionGameException.
     * 
     * @param oldI l'indice di colonna della posizione precedente del robot
     * @param oldJ l'indice di riga della posizione precedente del robot
     * @param i    l'indice di colonna della posizione del robot
     * @param j    l'indice di riga della posizione del robot
     * @throws IllegalPositionGameException se la nuova posizione non è valida
     */
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

    /**
     * Restituisce l'ID della cella nella posizione specificata.
     *
     * @param i l'indice di colonna della cella
     * @param j l'indice di riga della cella
     * @return l'ID della cella
     */
    public char getIDCasella(int i, int j)
    {
        return (this.mappa[i][j] != null) ? this.mappa[i][j].getID() : '0';
    }

    /**
     * Restituisce lo stato della cella nella posizione specificata.
     * Se la cella è un'istanza di CellState, restituisce il suo stato.
     * Altrimenti, restituisce 0.
     *
     * @param i l'indice di colonna della cella
     * @param j l'indice di riga della cella
     * @return lo stato della cella
     */
    public int getStateCasella(int i, int j)
    {
        if(this.mappa[i][j] instanceof CellState)
            return ((CellState)this.mappa[i][j]).getState();
        return -1;
    }

    /**
     * Restituisce la dimensione della mappa considerando le colonne.
     *
     * @return la dimensione della mappa nelle colonne
     */
    public int getISize(){
        return this.mappa.length;
    }

    /**
     * Restituisce la dimensione della mappa considerando le righe.
     *
     * @return la dimensione della mappa nelle righe
     */
    public int getJSize(){
        return this.mappa[0].length;
    }

    /**
     * Genera un evento casuale nella mappa.
     * Se l'evento(Ev) è un'istanza di CellState, la cella risultante dell'evento viene inserita nella mappa.
     * Se l'evento(Ev) è un'istanza di Movable, la cella risultante dell'evento viene inserita nella mappa
     * e la cella precedente viene cancellata.
     * Se non è possibile generare l'evento, viene continuato il ciclo per un massimo di 5 tentativi.
     * @throws CantGenerateEventException se la cella non ha le condizioni per generare l'evento.
     */
    public void event() // TODO Migliorare
    {
        int number;
        if(eventable.size()>0){
            for (int i = 0; i < 5; i++) {
                number = this.nRandom.nextInt(eventable.size());
                try {
                    Eventable Ev = this.eventable.get(number);
                    if(Ev instanceof CellState){
                        Cell c = Ev.Event(this);
                        if (c != null)
                        {
                            this.mappa[c.i][c.j] = c;
                        }
                    }
                    else if(Ev instanceof Movable)
                    {
                        int oldI = ((Cell)Ev).i, oldJ = ((Cell)Ev).j;
                        Cell c = Ev.Event(this);
                        this.mappa[c.i][c.j] = c;
                        this.mappa[oldI][oldJ] = null;
                    }
                    break;
                } catch (CantGenerateEventException e) {}
            }
        }
    }

    /**
     * Esegue un'interazione con l'oggetto nella cella di fronte al robot.
     * Se la cella è un'istanza di Interactable, viene eseguita l'interazione.
     * Se la cella è un'istanza di Removable, la cella viene rimossa.
     *
     * @throws IllegaInteractnGameException se l'interazione non è consentita nel contesto del gioco
     */
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
