package src.model.element;

public abstract class Interagibile extends Casella{    

    public Interagibile(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    protected boolean status;

    public boolean isStatus() {
        return status;
    }

    public abstract void interagi() throws IllegaInteractnGameException;
}
