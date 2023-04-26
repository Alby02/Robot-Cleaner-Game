package model.cell.element.other;

import model.cell.builder.Interagibile;

public class Lavatrice extends Interagibile implements Ostacolo {

    public Lavatrice(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void interagi() throws IllegaInteractnGameException {
        // TODO Auto-generated method stub
        this.status = !this.status;
    }


    
}
