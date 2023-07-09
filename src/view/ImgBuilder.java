package view;

import java.io.CharConversionException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;

import model.CellBuilder;
import model.CellState;
import model.Map;

public class ImgBuilder {

    private ImageIcon imgMatrix[][];
    public final ImageIcon ROBOT;

    public ImgBuilder(Class<?> el[], boolean nScale[], final int iScale, final int jScale) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalArgumentException, NoSuchFieldException, CharConversionException
    {
        int scale = jScale;
        if(jScale > iScale)
        {
            scale = iScale;
        }

        ROBOT = new ImageIcon(new ImageIcon("img/Wall-E.png").getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));

        this.imgMatrix = new ImageIcon[36][];
        this.imgMatrix[0] = new ImageIcon[1];
        this.imgMatrix[0][0] = null;

        for(int i = 1; i < el.length; i++)
        {
            Class<?> c = el[i];
            int p = CellBuilder.charParser(((char)c.getDeclaredField("ID").get(null)));
            if(CellState.class.isAssignableFrom(c))
            {
                String states[] = (String[])c.getDeclaredMethod("getStates").invoke(null);
                this.imgMatrix[p] = new ImageIcon[states.length];
                for(int j = 0; j < states.length; j++)
                {
                    ImageIcon II = new ImageIcon("img/" + el[i].getSimpleName() + "-" + states[j] + ".png");
                    if(nScale[i])
                    {
                        II = new ImageIcon(II.getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));
                    }
                    this.imgMatrix[p][j] = II;
                }
            }
            else
            {
                this.imgMatrix[p] = new ImageIcon[1];
                ImageIcon II = new ImageIcon("img/" + el[i].getSimpleName() + ".png");
                if(nScale[i])
                {
                    II = new ImageIcon(II.getImage().getScaledInstance(1024/scale, 1024/scale, java.awt.Image.SCALE_DEFAULT));
                }
                this.imgMatrix[p][0] = II;
            }
        }
    }

    public ImageIcon getIcon(Map mappa, int i, int j) 
    {
        int a = 0, b = 0;
        if(!mappa.isCasellaEmpty(i, j))
        {
            try {
                a = CellBuilder.charParser(mappa.getIDCasella(i, j));
            } catch (CharConversionException e) {
                e.printStackTrace();
            }
            b = mappa.getStateCasella(i, j);
            if(b == -1)
                b = 0;
        }     
        return this.imgMatrix[a][b];
    }   
}
