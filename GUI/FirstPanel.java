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
    JLabel imageLabel = new JLabel();

    public FirstPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        final JPanel panel = new JPanel();
        panel.setLayout(chromLayout);
        JPanel controls = new JPanel();
        setLayout(new BorderLayout());
        controls.setLayout(new FlowLayout());
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
        try {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("gif2.gif"));
            imageLabel.setIcon(ii);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //set action listener


        Mode1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();
                ReadAdjMatrix r = new ReadAdjMatrix();
                try { r.setGraph(command);
                    MainMenu_V1 main = new MainMenu_V1();
                    main.switchPanel(mainPanel, "SECOND");
                } catch (IOException ex) { ex.printStackTrace(); }
                //Check the selection

            }
        });
        panel.add(Mode1);
        panel.add(Mode2);
        panel.add(Mode3);
        add(panel, BorderLayout.NORTH);
        add(controls, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.CENTER);
    }
}