import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SecondPanel extends JPanel {

    private JButton button;
    private JPanel mainPanel;

    public SecondPanel(JPanel mainPanel) throws FileNotFoundException {
        this.mainPanel = mainPanel;
        setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());
        //setBackground(Color.ORANGE);
        //add(createButton());
        add(new ChartComponent(), BorderLayout.CENTER);
    }

    /*private JButton createButton() {
        button = new JButton("Switch to the first Panel");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu_V1 main = new MainMenu_V1();
                main.switchPanel(mainPanel, "FIRST");
            }
        });
        return button;
    }*/
}