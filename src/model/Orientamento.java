package model;

public class Orientamento {

    public enum Direction{
        Alto,
        Basso,
        Destra,
        Sinistra
    }

    private Direction dir;

    public Orientamento(Direction face)
    {
        this.dir = face;
    }

    public Direction get() {
        return this.dir;
    }

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
