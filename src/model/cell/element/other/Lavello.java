package model.cell.element.other;

import model.cell.builder.Interagibile;

public class Lavello extends Interagibile implements Ostacolo {

    public Lavello(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void interagi() throws IllegaInteractnGameException {
        // TODO Auto-generated method stub
        this.status = !this.status;
    }


    
}
