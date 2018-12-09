import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;

public class JColorDialog extends JFrame{
    private JPanel contentPane;
	private Color stored;		//This is the color we chosed
/*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrameColorDialog frame = new JFrameColorDialog();
                    frame.setVisible(true);
                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }*/
    public JColorDialog() {
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,475,370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        add(contentPane);
        contentPane.setLayout(null);

        final JPanel panelColor = new JPanel();
        panelColor.setBorder(new LineBorder(new Color(0,0,0)));
        panelColor.setBounds(10,11,439,257);
        contentPane.add(panelColor);

        /*final JButton btnSelectColor = new JButton("Select Color");
        btnSelectColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JColorChooser jcc = new JColorChooser();
                jcc.setPreviewPanel(new JPanel());
                Color c = jcc.showDialog(null, "please select a color", Color.gray);
                //jcc.setPreviewPanel(new JPanel());
                panelColor.setBackground(c);
				System.out.println("1" + jcc.getColor());
            }
        });
        btnSelectColor.setBounds(10,279,150,23);
        contentPane.add(btnSelectColor);*/

        final JButton btnPreview = new JButton("cuurent-stage");
        btnPreview.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent arg0) {
                Window parentWindow = SwingUtilities.windowForComponent(btnPreview);
                final JColorChooser jcc = new JColorChooser();
                jcc.getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent arg0) {
                        panelColor.setBackground(jcc.getColor());
						System.out.println("2" + jcc.getColor());	//the color is here
						stored = jcc.getColor();
						System.out.println("stored1 " + stored);
                    }
                });
                JDialog dialog = new JDialog(parentWindow);
                jcc.setPreviewPanel(new JPanel());
                dialog.add(jcc);
                dialog.pack();
                dialog.setVisible(true);
				System.out.println("3" + jcc.getColor());	
            }
        });
        btnPreview.setBounds(180,279,150,23);
        contentPane.add(btnPreview);
    }
	public Color getColorDialog()
	{
		return stored;
	}

}