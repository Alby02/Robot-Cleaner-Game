package model;

public class Orientamento {

    /**
     * Enumerazione delle direzioni possibili.
     */
    public enum Direction{
        Alto,
        Basso,
        Destra,
        Sinistra
    }

    private Direction dir;

    /**
     * Costruttore della classe Orientamento.
     *
     * @param face la direzione iniziale
     */
    public Orientamento(Direction face)
    {
        this.dir = face;
    }

    /**
     * Restituisce la direzione corrente.
     *
     * @return la direzione corrente
     */
    public Direction get() {
        return this.dir;
    }

    /**
     * Ruota l'orientamento verso sinistra.
     */
    public void rotateSX()
    {
        switch (this.dir) {
            case Alto:
                this.dir = Direction.Sinistra;
                break;
            case Sinistra:
                this.dir = Direction.Basso;
                break;
            case Basso:
                this.dir = Direction.Destra;
                break;
            case Destra:
                this.dir = Direction.Alto;
                break;
        }
    }

    /**
     * Ruota l'orientamento verso destra.
     */
    public void rotateDX()
    {
        switch (this.dir) {
            case Alto:
                this.dir = Direction.Destra;
                break;
            case Destra:
                this.dir = Direction.Basso;
                break;
            case Basso:
                this.dir = Direction.Sinistra;
                break;
            case Sinistra:
                this.dir = Direction.Alto;
                break;
        }
    }
}
