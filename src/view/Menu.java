package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;

import controller.WhereIAmController;
import model.Map;
import model.MapBuilder;
import model.element.*;

public class Menu{

    private JFrame f;
    private JPanel p;

    public Menu()
    {
        f = new JFrame();
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(400, 600));
        f.add(new JLabel("Select World"),  BorderLayout.NORTH);
        this.p = new JPanel();
        JScrollPane s = new JScrollPane();
        s.getViewport().add(p);
        s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        f.add(s, BorderLayout.CENTER);
    }

    public Set<String> listFilesUsingJavaIO(String dir) {
    return Stream.of(new File(dir).listFiles())
      .filter(file -> !file.isDirectory())
      .map(File::getName)
      .collect(Collectors.toSet());
    }

    public void showPosition() {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};// Esistono queste classi
        final boolean scale[] = {false, false, true, true, true, true, true, true};// valore di scala in base alle classi
        p.removeAll();
        Set<String> mondi = listFilesUsingJavaIO("./saves/");
        p.setLayout(new GridLayout(mondi.size()+1, 1));
        Menu sas = this;
        JButton b;
        String netto = "New WORLDINO";
        b = new JButton(netto);
        b.setPreferredSize(new Dimension(150, 200));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(f);
                JLabel content = new JLabel("Inserisci nome mondo.txt");
                dialog.setLayout(new GridLayout(3, 1));
                dialog.add(content);
                JTextArea text = new JTextArea();
                JButton b = new JButton("Invia");
                dialog.add(text);
                dialog.add(b);
                dialog.setSize(300,100);
                dialog.setLocationRelativeTo(f);
                dialog.setVisible(true);

                b.addActionListener(new ActionListener() {
                    //TODO persentage of random cell
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        String Name = text.getText();
                        Map map;
                        try
                        {
                            map = MapBuilder.generateRandomMap(); // generazione della mappa
                            ImgBuilder imgMatrix = new ImgBuilder(element, scale, map.getISize(), map.getJSize());
                            GUIControlView view = new GUIControlView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
                            TextualView view2 = new TextualView();
                            GUIGameView view3 = new GUIGameView(map, view, imgMatrix);
                            int delay = 5000;
                            Timer timer = new Timer(delay, null);
                            WhereIAmController controller = new WhereIAmController(sas, Name, map, element, timer, view, view2, view3);     
                            f.setVisible(false);
                            controller.start();                                          
                        }
                        catch (Exception ex)
                        {
                            System.err.println(ex.getMessage());
                            ex.printStackTrace();
                        }
                        dialog.dispose();
                    }
                });                
            }
        });
        p.add(b);
        
        for(String m : mondi) 
        {
            b = new JButton(m);
            b.setPreferredSize(new Dimension(150, 200));
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Map map;
                    try
                    {
                        map = MapBuilder.generateFromFile("saves/" + e.getActionCommand(), element); // generazione della mappa
                        ImgBuilder imgMatrix = new ImgBuilder(element, scale, map.getISize(), map.getJSize());
                        GUIControlView view = new GUIControlView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
                        TextualView view2 = new TextualView();
                        GUIGameView view3 = new GUIGameView(map, view, imgMatrix);
                        int delay = 5000;
                        Timer timer = new Timer(delay, null);
                        WhereIAmController controller = new WhereIAmController(sas, e.getActionCommand(), map, element, timer, view, view2, view3);     
                        f.setVisible(false);
                        controller.start();                                          
                    }
                    catch (Exception ex)
                    {
                        System.err.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            });
            p.add(b);
        }
    }

    public void startView() {
        showPosition();
        f.setVisible(true);
    }    
}
