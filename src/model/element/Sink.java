package model.element;

import java.util.HashMap;

import javax.swing.ImageIcon;

import model.Cell;
import model.CellState;
import model.Map;
import model.exception.CantGenerateStateEventException;
import model.exception.IllegaInteractnGameException;

public class Sink extends CellState
{
    private static HashMap<String,ImageIcon> Icon;

    static
    {
        Icon = new HashMap<String,ImageIcon>();
        Icon.put("base", new ImageIcon(new ImageIcon("src/img/Sink-base.png").getImage().getScaledInstance(1024/iScale, 1024/jScale, java.awt.Image.SCALE_DEFAULT)));
    }

    public Sink(int i, int j)
    {
        super(i, j, "base");
    }

    @Override
    public void interact() throws IllegaInteractnGameException 
    {
        if (this.state == "broken") {
            this.state = "base";
            updateIcon();
        }
        else
        {
            throw new IllegaInteractnGameException("Invalid interaction Sink is not broken");
        }
    }

    @Override
    public Cell Event(Map mappa) throws CantGenerateStateEventException 
    {
        Cell c = null;

        if (this.state == "base") {
            this.state = "broken";
            updateIcon();
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
                throw new CantGenerateStateEventException("All surrounding cell are full");
            }
        }

        return c;
    }

    @Override
    public ImageIcon getIcon() {
        return Icon.get(state);
    }

    private void updateIcon() {
        if(this.state == "broken") {
            Icon.put("broken", new ImageIcon(new ImageIcon("src/img/Sink-broken.png").getImage().getScaledInstance(1024/iScale, 1024/jScale, java.awt.Image.SCALE_DEFAULT)));
        }
        else {
            Icon.put("base", new ImageIcon(new ImageIcon("src/img/Sink-base.png").getImage().getScaledInstance(1024/iScale, 1024/jScale, java.awt.Image.SCALE_DEFAULT)));
        }

    }
}
