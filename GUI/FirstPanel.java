import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class FirstPanel extends JPanel {

    private JButton button;
    private JPanel mainPanel;
    JRadioButton randomButton;
    JRadioButton customButton;
    JRadioButton fileButton;
    private File inputfile;
    private String aWay;
    JButton createButton = new JButton("Create graph");
    JButton Mode1 = new JButton("Mode 1");
    JButton Mode2 = new JButton("Mode 2");
    JButton Mode3 = new JButton("Mode 3");
    final String random = "Randomize";
    final String custom = "Custom";
    final String file = "File";
    FlowLayout chromLayout = new FlowLayout();

    public FirstPanel(JPanel mainPanel) {
        //this.aWay = aWay;
        this.mainPanel = mainPanel;
        //setPreferredSize(new Dimension(400, 200));
        //setBackground(Color.GRAY);
        add(switchButton());
        final JPanel panel = new JPanel();
        panel.setLayout(chromLayout);
        JPanel controls = new JPanel();
        setLayout(new BorderLayout());
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
        //set action listener
        Mode1.addActionListener(new ActionListener() {
            ReadAdjMatrix r = new ReadAdjMatrix();
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();
                //Check the selection
                r.setGraph(command);
                MainMenu_V1 main = new MainMenu_V1();
                main.switchPanel(mainPanel, "SECOND");
            }
        });
        panel.add(Mode1);
        panel.add(Mode2);
        panel.add(Mode3);
        add(panel, BorderLayout.NORTH);
        add(controls, BorderLayout.SOUTH);
    }

    private JButton switchButton() {
        button = new JButton("Play");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu_V1 main = new MainMenu_V1();
                main.switchPanel(mainPanel, "SECOND");
            }
        });
        return button;
    }
}