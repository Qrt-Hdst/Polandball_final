package Polandball_pliki.Frame;


import Polandball_pliki.Panel.MainPanel;
import Polandball_pliki.Panel.PanelBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import static Polandball_pliki.Others.GetConstans.*;

/**
 * Okno gry, zawierające plansze konkretnego poziomu, informacje o stanie rozgrywki
 */

public class LevelFrame extends JDialog implements WindowListener, ActionListener {

    /**
     * Zmienna typu mainpanel, gdzie przechowywane sa wszystkie panele gry
     */
    public MainPanel mainPanel;

    /**
     * Zmienna odliczajaca czas odswiezenia
     */
    public static Timer tm;
    /**
     * kontruktor klasy LevelFrame, zawierjący funkcję initLevelFrame
     */

    public LevelFrame() {
        initLevelFrame();
    }

    /**
     * funkcja zawierająca parametry i poszczególne panele gry
     */

    private void initLevelFrame() {


        mainPanel=new MainPanel();
        this.getContentPane().add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);//ustawienie ramki na srodku
        this.setVisible(true);
        this.addWindowListener(this);
        this.setMinimumSize(new Dimension(350, 350));
        this.setResizable(false);
        tm =new Timer(30,mainPanel.panelboard);
        tm.start();

    }

    /**
     * Metoda obslugujaca zdarzenia zakmniecia okna gry, przwracajaca ustawienia domyslne
     * @param e
     */
    public void windowClosing(WindowEvent e) {
        try{
            tm.stop();//zatrzymanie timera
            PanelBoard.MakeDefaultOption(1);//przywrocenie domyslnych parametrow
            this.dispose();//zamkniecie okna
            Amountofpoints=0;
        }catch(Exception error){
            System.out.println(error+"Blad zamkniecia levelframe - klasa levelframe, metoda windowClosing");
        }
    }
   //metody wymagane przez interfejs, przydadza sie pozniej
    public void windowActivated(WindowEvent e) {
    }
    public void windowClosed(WindowEvent e) {
    }
    public void windowDeactivated(WindowEvent e) {
    }
    public void windowDeiconified(WindowEvent e) {
    }
    public void windowIconified(WindowEvent e) {
    }
    public void windowOpened(WindowEvent e) {
    }
    public void actionPerformed(ActionEvent e) {
    }

}
