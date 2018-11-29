import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class MainMenu_V1 extends JFrame {

    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem exit;
    private JPanel mainPanel;
    private JMenuItem back;
    CardLayout cardLayout = new CardLayout();

    public MainMenu_V1() throws IOException{
        setTitle("I HATE THIS SHIT!!!!!!!!");
        setResizable(true);
        setSize(new Dimension(1600,1400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMainMenu());
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(new FirstPanel(mainPanel), "FIRST");
        mainPanel.add(new SecondPanel(mainPanel), "SECOND");

        setContentPane(mainPanel);
    }

    public JMenuBar createMainMenu() {
        menuBar = new JMenuBar();
        file = new JMenu("Menu");
        exit = new JMenuItem("Exit");
        back = new JMenuItem("<-");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.previous(mainPanel);
            }
        });

        file.add(back);
        file.add(exit);
        menuBar.add(file);

        return menuBar;
    }

    public void switchPanel(Container container, String panelName) {
        CardLayout card = (CardLayout) (container.getLayout());
        card.show(container, panelName);
    }

    public static void main(String[] args) throws IOException {
        new MainMenu_V1().setVisible(true);
    }
}