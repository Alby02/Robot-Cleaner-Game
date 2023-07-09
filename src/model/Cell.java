package model;

public abstract class Cell
{   
    protected int i;
    protected int j;
    public static char ID = 0;

    /**
     * Costruisce un oggetto Cell con le coordinate e l'identificatore specificati.
     *
     * @param i  l'indice di colonna della cella nella griglia(x)
     * @param j  l'indice di riga della cella nella griglia(y)
     */
    public Cell(int i, int j)
    {
        this.i = i;
        this.j = j;
    }

    /**
     * Restituisce l'indice di colonna della cella nella griglia.
     *
     * @return l'indice di colonna della cella
     */
    public int posI()
    {
        return i;
    }

    /**
     * Restituisce l'indice di riga della cella nella griglia.
     *
     * @return l'indice di riga della cella
     */
    public int posJ()
    {
        return j;
    }

    /**
     * Restituisce l'identificatore univoco della cella.
     *
     * @return l'identificatore della cella
     */
    public abstract char getID();
}
