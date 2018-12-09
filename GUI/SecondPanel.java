import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondPanel extends JPanel {

    private JButton button;
    private JPanel mainPanel;
	
	
    public SecondPanel(JPanel mainPanel) throws FileNotFoundException {
        this.mainPanel = mainPanel;
        //setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());
        add(new ChartComponent(), BorderLayout.CENTER);
    }
}