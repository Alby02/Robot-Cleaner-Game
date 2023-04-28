package model.cell.builder;


import java.util.HashMap;

import javax.swing.ImageIcon;

public class CellFactory {

    private HashMap<String,ImageIcon> Icone;

    public CellFactory()
    {
        this.Icone = new HashMap<String,ImageIcon>(); 
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
        }
        else
            NomeClasse = "Floor";
        if(!this.Icone.containsKey(NomeClasse))
            this.Icone.put(NomeClasse, new ImageIcon("src/img/" + NomeClasse + ".jpg")) ;
        return this.Icone.get(NomeClasse);
    }
}