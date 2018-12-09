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
    private SecondPanel sec;

    CardLayout cardLayout = new CardLayout();

    public MainMenu_V1() {
        setTitle("My Layout");
        setResizable(true);
        // setSize(new Dimension(500,500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMainMenu());
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
        sec = new SecondPanel(mainPanel);
        mainPanel.add(new FirstPanel(mainPanel), "FIRST");
        mainPanel.add(sec, "SECOND");

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
                mainPanel.add(new FirstPanel(mainPanel), "FIRST");
                mainPanel.add(new SecondPanel(mainPanel), "SECOND");
                cardLayout.first(mainPanel);
            }
        });

        file.add(back);
        file.add(exit);
        menuBar.add(file);

        return menuBar;
    }

    public void switchPanel(Container container, String panelName) {
        mainPanel.add(sec, "SECOND");
        CardLayout card = (CardLayout) (container.getLayout());
        card.show(container, panelName);
    }

    public static void main(String[] args) throws IOException {
        new MainMenu_V1().setVisible(true);
    }
}