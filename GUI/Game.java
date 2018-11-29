import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;



public class Game
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChromaticLayout.createAndShowGUI();
            }
        });
    }
}