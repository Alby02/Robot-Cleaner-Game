package model.cell.element;

import model.cell.builder.*;
import model.map.Map;

public class Sink extends CellState  {

    public Sink(int i, int j) {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void interact() throws IllegaInteractnGameException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public Cell Event(Map mappa) throws CantGenerateStateEventException{
        Cell c = null;
        if(this.state == "base")
        {
            state = "broken";
        }
        else
        {
            if(i < mappa.getISize() - 1 && mappa.getCasella(this.i + 1, this.j) == null)
            {
                c = new Water(this.i + 1, this.j);
            }
            else if(j < mappa.getJSize() -1 && mappa.getCasella(this.i, this.j + 1) == null)
            {
                c = new Water(this.i, this.j + 1);
            }
            else if(i > 1 && mappa.getCasella(this.i - 1, this.j) == null)
            {
                c = new Water(this.i - 1, this.j);
            }
            else if(j > 1 && mappa.getCasella(this.i + 1, this.j - 1) == null)
            {
                c = new Water(this.i, this.j - 1);
            }
            else
            {
                throw new CantGenerateStateEventException("All surroung cell are full");
            }
        }
        return c;
    }



    
}
