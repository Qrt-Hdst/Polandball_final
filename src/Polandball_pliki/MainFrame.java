package Polandball_pliki;

import static Polandball_pliki.GetConstans.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.*;



/**
 * Okno główne, wyświetlane podczas uruchomienia gry
 */
public class MainFrame extends JFrame {

    /**
     * Tło  okna ( skalowalne )
     */

    public static StartPanel startPanel;

    /**
     * Konstruktor okna głównego, zawierający funkcję initMainFrame
     */

    public MainFrame() {
        initMainFrame();
    }

    /**
     * funkcja zawierająca parametry i komponenty okna głównego
     */

    private void initMainFrame() {

        startPanel =new StartPanel(new ImageIcon(BackgroundString).getImage());
        this.getContentPane().add(startPanel);
        this.pack();
        this.setVisible(true);
    }
}