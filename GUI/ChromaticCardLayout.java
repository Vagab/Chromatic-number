import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ChromaticCardLayout implements ItemListener {
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Menu";
    final static String TEXTPANEL = "Game";
    JRadioButton randomButton;
    JRadioButton customButton;
    JRadioButton fileButton;
    private String aWay;
    JButton createButton = new JButton("Create graph");
    JButton Mode1 = new JButton("Mode 1");
    JButton Mode2 = new JButton("Mode 2");
    JButton Mode3 = new JButton("Mode 3");
    File inputfile = new File("/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI", "graph.txt");

    FlowLayout gameLayout = new FlowLayout();

    final String random = "Randomize";
    final String custom = "Custom";
    final String file = "File";


    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        //JLabel label= new JLabel();
        //label.setIcon(new ImageIcon("/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI/ring.jpg"));
        //create radiobuttons
        randomButton = new JRadioButton(random);
        randomButton.setActionCommand(random);
        randomButton.setSelected(true);
        customButton = new JRadioButton(custom);
        customButton.setActionCommand(custom);
        fileButton = new JRadioButton(file);
        fileButton.setActionCommand(file);
        //add controls to create graphs
        final ButtonGroup group = new ButtonGroup();
        group.add(randomButton);
        group.add(customButton);
        group.add(fileButton);
        controls.add(randomButton);
        controls.add(customButton);
        controls.add(fileButton);
        controls.add(createButton);
        //set action listener
        JPanel card1 = new JPanel();
        card1.add(Mode1);
        card1.add(Mode2);
        card1.add(Mode3);
        JPanel card2 = new JPanel();
        createButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String command = group.getSelection().getActionCommand();
                //Check the selection
                if (command.equals("Randomize"))
                {
                    aWay = "Randomize";
                }
                else if (command.equals("Custom"))
                {
                    aWay = "Custom";
                }
                else {
                    aWay = "File";
                }
            }
        });
        card1.add(controls, BorderLayout.SOUTH);
        card2.add(new ChartComponent(aWay, inputfile));

        //Create the "cards".



        //JComponent component = new ChartComponent(aWay, inputfile);

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);

    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ChromaticCardLayout()");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        File inputfile = new File("/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI", "graph.txt");
        //ChromaticLayout chromFrame = new ChromaticLayout("Chromatic Number Game", inputfile);
        ChromaticCardLayout cardFrame = new ChromaticCardLayout();
        cardFrame.addComponentToPane(frame.getContentPane());
        //chromFrame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
