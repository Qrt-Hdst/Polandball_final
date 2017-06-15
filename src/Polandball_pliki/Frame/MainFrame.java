package Polandball_pliki.Frame;

import Polandball_pliki.Panel.StartPanel;

import static Polandball_pliki.Others.GetConstans.*;

import javax.swing.*;


/**
 * Okno główne, wyświetlane podczas uruchomienia gry
 */
public class MainFrame extends JFrame  {

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startPanel =new StartPanel(new ImageIcon(BackgroundString).getImage());
        this.getContentPane().add(startPanel);
        this.pack();
        this.setLocationRelativeTo(null);//ustawienie ramki na srodku
        this.setVisible(true);
    }
}