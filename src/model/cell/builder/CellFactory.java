package model.cell.builder;


import java.util.HashMap;

import javax.swing.ImageIcon;
import java.awt.Image;

public class CellFactory {

    private HashMap<String,ImageIcon> Icone;
    private int scale;

    public CellFactory(int scale)
    {
        this.Icone = new HashMap<String,ImageIcon>(); 
        this.scale = scale;
    }

    public ImageIcon getIcon(Cell cella){
        String NomeClasse;
        if(cella != null)
        {
            NomeClasse = cella.getClass().getSimpleName();
            if(cella instanceof CellState)
            {
                NomeClasse += "-" + ((CellState)cella).getState();
            }
            if(!this.Icone.containsKey(NomeClasse))
                this.Icone.put(NomeClasse, new ImageIcon(new ImageIcon("src/img/" + NomeClasse + ".png").getImage().getScaledInstance(1024/scale, 1024/scale, Image.SCALE_DEFAULT)));
            return this.Icone.get(NomeClasse);
        }
        else
            return null;
    }
}