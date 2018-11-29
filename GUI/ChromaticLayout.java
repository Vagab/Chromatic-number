import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class ChromaticLayout extends JFrame
{
  JRadioButton randomButton;
  JRadioButton customButton;
  JRadioButton fileButton;
  private File inputfile;
  private String aWay;
  JButton createButton = new JButton("Create graph");
  JButton Mode1 = new JButton("Mode 1");
  JButton Mode2 = new JButton("Mode 2");
  JButton Mode3 = new JButton("Mode 3");

  FlowLayout chromLayout = new FlowLayout();

  final String random = "Randomize";
  final String custom = "Custom";
  final String file = "File";

  public ChromaticLayout(String name)
  {
    super(name);
  }
  public ChromaticLayout(String name, File inputfile)
  {
    super(name);
    this.inputfile = inputfile;
  }

  public  String getWay()
  {return aWay;}

  public void addComponentsToPane(final Container pane)
  {
    final JPanel panel = new JPanel();
    panel.setLayout(chromLayout);
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
    //controls.add(createButton);
    //set action listener
    /*createButton.addActionListener(new ActionListener(){
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
            panel.validate();
            panel.repaint();
        }
    });*/

      Mode1.addActionListener(new ActionListener(){
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
              Component f = panel.getTopLevelAncestor();
              ((Window)f).dispose();
              JFrame frame = new JFrame();
              frame.setSize(1600, 1450);
              frame.setTitle("Graph Generator");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              JButton showColors = new JButton("Show Colors");
              JPanel panel = new JPanel();
              panel.setLayout(new BorderLayout());
              JComponent component = new ChartComponent(aWay, inputfile);
              panel.add(component, BorderLayout.CENTER);
              frame.add(panel);
              //panel.setVisible(true);
              frame.setVisible(true);
          }
      });

      panel.add(Mode1);
      panel.add(Mode2);
      panel.add(Mode3);
      pane.add(panel, BorderLayout.CENTER);
      pane.add(controls, BorderLayout.SOUTH);
      //pane.add(label);
  }

  public static void createAndShowGUI() {
      //Create and set up the window.
      File inputfile = new File("/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI", "graph.txt");
      ChromaticLayout frame = new ChromaticLayout("Chromatic Number Game", inputfile);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Set up the content pane.

      frame.addComponentsToPane(frame.getContentPane());
      //Display the window.
      frame.pack();
      frame.setVisible(true);
    }
      public static void main(String[] args) {
          //Schedule a job for the event dispatchi thread:
          //creating and showing this application's GUI.

          javax.swing.SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                  createAndShowGUI();
              }
          });
      }

}
