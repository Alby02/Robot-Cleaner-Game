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

public class Menu{

    private JFrame f;
    private JPanel p;
    private Class<?>[] elements;
    private boolean[] scale;
    private int[] probability;
    private int num;

    public Menu(Class<?>[] ce, boolean[] sca, int[] pro)
    {
        this.elements = ce;
        this.scale = sca;
        this.probability = pro;
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
        p.removeAll();
        Set<String> mondi = listFilesUsingJavaIO("./saves/");
        p.setLayout(new GridLayout(mondi.size()+1, 1));
        Menu sas = this;
        JButton b;
        String netto = "New WORLDINO";
        b = new JButton(netto);
        b.setPreferredSize(new Dimension(150, 150));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(f);
                dialog.setLayout(new BorderLayout());
                JPanel pannellino = new JPanel(new BorderLayout());
                JPanel pannellinoLabel = new JPanel(new GridLayout(2, 1));
                pannellino.add(pannellinoLabel, BorderLayout.WEST);
                JPanel pannellinoText = new JPanel(new GridLayout(2, 1));
                pannellino.add(pannellinoText, BorderLayout.EAST);
                dialog.add(pannellino, BorderLayout.CENTER);

                JLabel content = new JLabel("Inserisci nome mondo.txt");
                pannellinoLabel.add(content);
                JTextArea text = new JTextArea();
                text.setPreferredSize(new Dimension(125, 50));
                pannellinoText.add(text);

                JLabel contentDimensione = new JLabel("Inserisci dimensione del mondo di righe(maggiore di 10)");
                pannellinoLabel.add(contentDimensione);
                JTextArea dimensione = new JTextArea();
                dimensione.setPreferredSize(new Dimension(125, 50));
                pannellinoText.add(dimensione);

                JButton b = new JButton("Invia");
                dialog.add(b, BorderLayout.SOUTH);
                dialog.setSize(500,100);
                dialog.setLocationRelativeTo(f);
                dialog.setVisible(true);

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        String Name = text.getText();
                        num = Integer.parseInt(dimensione.getText());
                        Map map;
                        try
                        {
                            map = MapBuilder.generateRandomMap(num, elements, probability); // generazione della mappa
                            ImgBuilder imgMatrix = new ImgBuilder(elements, scale, map.getISize(), map.getJSize());
                            GUIControlView view = new GUIControlView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
                            TextualView view2 = new TextualView(map);
                            GUIGameView view3 = new GUIGameView(map, view, imgMatrix);
                            int delay = 5000;
                            Timer timer = new Timer(delay, null);
                            WhereIAmController controller = new WhereIAmController(sas, Name, map, elements, timer, view, view2, view3);     
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
            b.setPreferredSize(new Dimension(150, 150));
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Map map;
                    try
                    {
                        map = MapBuilder.generateFromFile("saves/" + e.getActionCommand(), elements); // generazione della mappa
                        ImgBuilder imgMatrix = new ImgBuilder(elements, scale, map.getISize(), map.getJSize());
                        GUIControlView view = new GUIControlView(map, imgMatrix); // generazione della Gui grafica in base alla struttura del modello
                        TextualView view2 = new TextualView(map);
                        GUIGameView view3 = new GUIGameView(map, view, imgMatrix);
                        int delay = 5000;
                        Timer timer = new Timer(delay, null);
                        WhereIAmController controller = new WhereIAmController(sas, e.getActionCommand(), map, elements, timer, view, view2, view3);     
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
