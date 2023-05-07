package model.cell.element;

import java.util.HashMap;
import javax.swing.ImageIcon;

import model.cell.builder.*;
import model.map.Map;

public class Washer extends CellState
{
    private static HashMap<String,ImageIcon> Icon;

    static
    {
        Icon = new HashMap<String,ImageIcon>();
        Icon.put("base", new ImageIcon(new ImageIcon("src/img/Washer-base.png").getImage().getScaledInstance(1024/iScale, 1024/jScale, java.awt.Image.SCALE_DEFAULT)));
        Icon.put("broken", new ImageIcon(new ImageIcon("src/img/Washer-broken.png").getImage().getScaledInstance(1024/iScale, 1024/jScale, java.awt.Image.SCALE_DEFAULT)));
    }

    public Washer(int i, int j)
    {
        super(i, j);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void interact() throws IllegaInteractnGameException 
    {
        if(this.state == "broken")
        {
            this.state = "base";
        }
        else
        {
            throw new IllegaInteractnGameException("Invalid interaction Washer is not broken");
        }
    }

    @Override
    public Cell Event(Map mappa) throws CantGenerateStateEventException
    {
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

    @Override
    public ImageIcon getIcon() {
        return Icon.get(state);
    }    
}
