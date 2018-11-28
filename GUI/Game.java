import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Game extends JFrame
{
  JRadioButton randomButton;
  JRadioButton customButton;
  JRadioButton fileButton;
  private File inputfile;
  private int[][] m;
  private String aWay;
 // private GraphGenerating g;
  JButton createButton = new JButton("Create graph");
  JButton Mode1 = new JButton("Mode 1");
  JButton Mode2 = new JButton("Mode 2");
  JButton Mode3 = new JButton("Mode 3");

  FlowLayout gameLayout = new FlowLayout();

  final String random = "Randomize";
  final String custom = "Custom";
  final String file = "File";

  public Game(String name)
  {
    super(name);
    //this.m = m;
  }
  public Game(String name, File inputfile)
  {
    super(name);
    this.inputfile = inputfile;
  }

  public  String getWay()
  {return aWay;}

  public void addComponentsToPane(final Container pane)
  {
    //this.m = m;
    final JPanel panel = new JPanel();
    panel.setLayout(gameLayout);
    JPanel controls = new JPanel();
    controls.setLayout(new FlowLayout());
    JLabel label= new JLabel();
    label.setIcon(new ImageIcon("/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI/ring.jpg"));
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
    createButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String command = group.getSelection().getActionCommand();
            //Check the selection
            if (command.equals("Randomize"))
            {
              aWay = "Randomize";
              //this.m = new GraphGenerating();
              /*g.showAdjacencyMatrix();
              System.out.println("-----------");*/
            }
            else if (command.equals("Custom"))
            {
                aWay = "Custom";
               /* Scanner in = new Scanner(System.in);
              System.out.println("Enter the number of vertices: ");
              int vertices = in.nextInt();
              System.out.println("Enter the number of edges(note, that number of edges has to be greater" +
                      " then half of the number of vertices): ");
              int edges = in.nextInt();*/
              //this.m = new GraphGenerating(edges, vertices);
              /*g.showAdjacencyMatrix();
              System.out.println("-----------");*/
            }
            else {
                aWay = "File";
                //ReadGraph graph = new ReadGraph(inputfile);
                //this.m = graph.readMatrix();
              /*for (int i = 0; i < m.length; i++)
              { for (int j = 0; j < m.length; j++)
                { System.out.print(m[i][j] + " ");
                }
                System.out.println("");
              }
              System.out.println("-----------");
            }*/
            }
            //update the experiment layout
            panel.validate();
            panel.repaint();
        }
    });

      Mode1.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              Component f = panel.getTopLevelAncestor();
              ((Window)f).dispose();
              JFrame frame = new JFrame();
              frame.setSize(1600, 1450);
              frame.setTitle("Graph Generator");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              JButton showColors = new JButton("Show Colors");
              JPanel panel = new JPanel();
              //panel.setLayout(new FlowLayout());
              JComponent component = new ChartComponent(aWay, inputfile);
              //panel.add(showColors);
              //panel.add(component);
              frame.add(component);
              frame.setVisible(true);
          }
      });

      panel.add(Mode1);
      panel.add(Mode2);
      panel.add(Mode3);
      pane.add(panel, BorderLayout.NORTH);
      pane.add(controls, BorderLayout.SOUTH);
      pane.add(label);
  }

  private static void createAndShowGUI() {
      //Create and set up the window.
      File inputfile = new File("/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI", "graph.txt");
      Game frame = new Game("Chromatic Number Game", inputfile);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Set up the content pane.

      frame.addComponentsToPane(frame.getContentPane());
      //Display the window.
      frame.pack();
      frame.setVisible(true);
    }
      public static void main(String[] args) {
          /* Use an appropriate Look and Feel
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
          /* Turn off metal's use of bold fonts
          UIManager.put("swing.boldMetal", Boolean.FALSE);
          //Schedule a job for the event dispatchi thread:
          //creating and showing this application's GUI.
          */
          javax.swing.SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                  createAndShowGUI();
              }
          });
      }

}
