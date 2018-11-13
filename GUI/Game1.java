import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Game1
{
 public static void main(String[] args)
 {
 JFrame frame = new ButtonFrame2();

 frame.setTitle("A frame with two components");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



 frame.setVisible(true);
 }
}

//----
class ButtonFrame2 extends JFrame
{
 private JButton button1;
 private JButton button2;
 private JButton button3;

 private JLabel label;

 private static int menuCountF = 0;

 private static final int FRAME_WIDTH = 300;
 private static final int FRAME_HEIGTH = 300;

 public ButtonFrame2()
 {
  createComponents();
  setSize(FRAME_WIDTH, FRAME_HEIGTH);

 }

 class ClickListener1 implements ActionListener
 {

  public void actionPerformed(ActionEvent event)
  {

   label.setText("Menu 1 choosen");
  }
 }
 class ClickListener2 implements ActionListener
 {

  public void actionPerformed(ActionEvent event)
  {

   label.setText("Menu 2 choosen");
  }
 }
 class ClickListener3 implements ActionListener
 {

  public void actionPerformed(ActionEvent event)
  {

   label.setText("Menu 3 choosen");
  }
 }


 private void createComponents()
 {


  button1 = new JButton("Menu 1");
  button2 = new JButton("Menu 2");
  button3 = new JButton("Menu 3");


  ActionListener listener1 = new ClickListener1();
  ActionListener listener2 = new ClickListener2();
  ActionListener listener3 = new ClickListener3();
  button1.addActionListener(listener1);
  button2.addActionListener(listener2);
  button3.addActionListener(listener3);

  label = new JLabel("Hello World!");

  JPanel panel = new JPanel();


  JLabel background=new JLabel(new ImageIcon("/Users/Desktop/Java/Project/GUI/ring.jpg"));

  add(background);

  background.setLayout(new FlowLayout());

  background.add(button1);
  background.add(button2);
  background.add(button3);
  background.add(label);

  }
  }
