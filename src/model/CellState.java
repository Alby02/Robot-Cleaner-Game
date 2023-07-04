package model;

public abstract class CellState extends Cell
{
    protected int state;
    private static String[] states = {"Base"};

    /**
     * Costruisce un oggetto Cell con le coordinate e l'identificatore specificati.
     *
     * @param i  l'indice di colonna della cella nella griglia(x)
     * @param j  l'indice di riga della cella nella griglia(y)
     * @param state lo stato della cella
     */
    public CellState(int i, int j, int state)
    {
        super(i, j);
        this.state = state;
    }

    /**
     * Restituisce lo stato della cella.
     *
     * @return lo stato della cella
     */
    public int getState()
    {
        return this.state;
    }

    /**
     * Restituisce un array di stati possibili per la cella.
     *
     * @return un array di stati possibili
     */
    public static String[] getStates()
    {
        return states.clone();
    }
}
