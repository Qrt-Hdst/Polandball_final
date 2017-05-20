package Polandball_pliki;


import javax.swing.*;

import static Polandball_pliki.GetConstans.*;

/**
 * Okno gry, zawierające plansze konkretnego poziomu, informacje o stanie rozgrywki
 */

public class LevelFrame extends JFrame {

    /**
     * kontruktor klasy LevelFrame, zawierjący funkcję initLevelFrame
     */

    public LevelFrame() {
        initLevelFrame();
    }

    /**
     * funkcja zawierająca parametry i poszczególne panele gry
     */

    private void initLevelFrame(){
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // zamkniecie okna gry nie powoduje
        this.setSize(Boardwidth,Boardheight);
        this.setLayout(null);

        //panel, który będzie naszą plansza do dodawania elementów poziomu
        PanelBoard panelboard = new PanelBoard();
        add(panelboard);
        panelboard.setVisible(true);

        //panel gorny, w ktorym beda informacje dotyczace gry
        PanelInfoOne panelinfoone = new PanelInfoOne();
        add(panelinfoone);
        panelinfoone.setVisible(true);

        //panel boczny, w ktorym beda pozostale informacje
        PanelInfoTwo panelinfotwo = new PanelInfoTwo();
        add(panelinfotwo);
        panelinfotwo.setVisible(true);


    }

}
