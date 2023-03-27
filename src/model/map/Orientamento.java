package src.model.map;

public class Orientamento {

    private Direction dir;

    public Orientamento()
    {
        this.dir = Direction.Destra;
    }

    public Direction get() {
        return this.dir;
    }

    public void aggiorna(Move M)
    {
        if(M == Move.SX)
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
        else if (M == Move.DX)
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

}
