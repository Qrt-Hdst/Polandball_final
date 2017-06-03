package Polandball_pliki.LevelFrame;


import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import static Polandball_pliki.GetConstans.*;

/**
 * Okno gry, zawierające plansze konkretnego poziomu, informacje o stanie rozgrywki
 */

public class LevelFrame extends JFrame implements WindowListener, ActionListener {

    public PanelBoard panelboard;

    public PanelInfoOne panelinfoone;

    public PanelInfoTwo panelinfotwo;

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
        // zamkniecie okna gry nie powoduje
        this.setSize(Boardwidth, Boardheight);
        this.setLayout(null);
        this.addWindowListener(this);

        //panel, który będzie naszą plansza do dodawania elementów poziomu
        panelboard = new PanelBoard();
        add(panelboard);
        panelboard.setVisible(true);

        //panel gorny, w ktorym beda informacje dotyczace gry
        panelinfoone = new PanelInfoOne();
        add(panelinfoone);
        panelinfoone.setVisible(true);

        //panel boczny, w ktorym beda pozostale informacje
        panelinfotwo = new PanelInfoTwo();
        add(panelinfotwo);
        panelinfotwo.setVisible(true);

        //odpalenie timera na panelboardzie
        tm =new Timer(30,panelboard);
        tm.start();

    }

    /**
     * Metoda obslugujaca zdarzenia zakmniecia okna gry, przwracajaca ustawienia domyslne
     * @param e
     */
    public void windowClosing(WindowEvent e) {
        try{
            tm.stop();//zatrzymanie timera
            PanelBoard.MakeDefaultOption();//przywrocenie domyslnych parametrow
            this.dispose();//zamkniecie okna
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

    /*public void paintComponent(){
        panelinf
    }*/
}
