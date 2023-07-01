package view;

import javax.swing.ImageIcon;

import model.Cell;
import model.CellState;

public class ImgBuilder {

    private ImageIcon imgMatrix[][];

    public ImgBuilder(Class<?> el[], final boolean nScale[], final int iScale, final int jScale) throws Exception
    {
        int scale = jScale;
        if(jScale > iScale)
        {
            scale = iScale;
        }
        this.imgMatrix = new ImageIcon[el.length][];
        this.imgMatrix[0] = new ImageIcon[1];
        this.imgMatrix[0][0] = null;

        for(int i = 1; i < el.length; i++)
        {
            Class<?> c = el[i];
            if(CellState.class.isAssignableFrom(c))
            {
                String states[] = (String[])c.getDeclaredField("states").get(null);
                this.imgMatrix[i] = new ImageIcon[states.length];
                for(int j = 0; j < states.length; j++)
                {
                    ImageIcon II = new ImageIcon("img/" + el[i].getSimpleName() + "-" + states[j] + ".png");
                    if(nScale[i])
                    {
                        II = new ImageIcon(II.getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));
                    }
                    this.imgMatrix[i][j] = II;
                }
            }
            else
            {
                this.imgMatrix[i] = new ImageIcon[1];
                ImageIcon II = new ImageIcon("img/" + el[i].getSimpleName() + ".png");
                if(nScale[i])
                {
                    II = new ImageIcon(II.getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));
                }
                this.imgMatrix[i][0] = II;
            }
        }
    }

    public ImageIcon getIcon(final Cell tipo)
    {
        int a = 0, b = 0;
        if(tipo != null)
        {
            a = tipo.getID();
            if (tipo instanceof CellState)
            {
                b = ((CellState)tipo).getState();
            }
        }     
        return this.imgMatrix[a][b];
    }   
}
