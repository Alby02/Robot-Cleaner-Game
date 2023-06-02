package view;

import javax.swing.ImageIcon;

import model.Cell;
import model.CellState;

public class ImgBuilder {

    public static final ImageIcon[][] createImgMatrix(final String el[], final boolean nScale[], final int iScale, final int jScale) throws Exception
    {
        int scale = jScale;
        if(jScale > iScale)
        {
            scale = iScale;
        }
        ImageIcon imgMatrix[][] = new ImageIcon[el.length][];
        imgMatrix[0] = new ImageIcon[1];
        imgMatrix[0][0] = null;

        for(int i = 1; i < el.length; i++)
        {
            Class<?> c = Class.forName("model.element."+el[i]);
            if(CellState.class.isAssignableFrom(c))
            {
                String states[] = (String[])c.getDeclaredField("states").get(null);
                imgMatrix[i] = new ImageIcon[states.length];
                for(int j = 0; j < states.length; j++)
                {
                    ImageIcon II = new ImageIcon("img/" + el[i] + "-" + states[j] + ".png");
                    if(nScale[i])
                    {
                        II = new ImageIcon(II.getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));
                    }
                    imgMatrix[i][j] = II;
                }
            }
            else
            {
                imgMatrix[i] = new ImageIcon[1];
                ImageIcon II = new ImageIcon("img/" + el[i] + ".png");
                if(nScale[i])
                {
                    II = new ImageIcon(II.getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));
                }
                imgMatrix[i][0] = II;
            }
        }
        return imgMatrix;
    }

    public static ImageIcon getIcon(final ImageIcon imgMatrix[][], final Cell tipo)
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
        return imgMatrix[a][b];
    }   
}
