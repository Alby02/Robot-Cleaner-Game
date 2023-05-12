package model.element;

import java.util.HashMap;

import javax.swing.ImageIcon;

import model.Cell;
import model.CellState;
import model.Map;
import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

public class Oven extends CellState
{
    private static HashMap<String,ImageIcon> Icon;

    static
    {
        Icon = new HashMap<String,ImageIcon>();
        Icon.put("base", new ImageIcon(new ImageIcon("src/img/Oven-base.png").getImage().getScaledInstance(1024/Scale, 1024/Scale, java.awt.Image.SCALE_DEFAULT)));
        Icon.put("broken", new ImageIcon(new ImageIcon("src/img/Oven-broken.png").getImage().getScaledInstance(1024/Scale, 1024/Scale, java.awt.Image.SCALE_DEFAULT)));
    }

    public Oven(int i, int j)
    {
        super(i, j, "base");
    }

    @Override
    public void interact() throws IllegaInteractnGameException 
    {
        if (this.state == "broken") {
            this.state = "base";
        }
        else
        {
            throw new IllegaInteractnGameException("Invalid interaction Oven is not broken");
        }
    }

    @Override
    public Cell Event(Map mappa) throws CantGenerateStateEventException
    {
        Cell c = null;

        if (this.state == "base") {
            this.state = "broken";
        }
        else
        {
            if(i < mappa.getISize() - 1 && mappa.getCasella(this.i + 1, this.j) == null)
            {
                c = new Fire(this.i + 1, this.j);
            }
            else if(j < mappa.getJSize() -1 && mappa.getCasella(this.i, this.j + 1) == null)
            {
                c = new Fire(this.i, this.j + 1);
            }
            else if(i > 1 && mappa.getCasella(this.i - 1, this.j) == null)
            {
                c = new Fire(this.i - 1, this.j);
            }
            else if(j > 1 && mappa.getCasella(this.i + 1, this.j - 1) == null)
            {
                c = new Fire(this.i, this.j - 1);
            }
                else
            {
                throw new CantGenerateStateEventException("All surrounding cell are full");
            }
        }
        return c;
    }

    @Override
    public ImageIcon getIcon() {
        return Icon.get(state);
    }
}