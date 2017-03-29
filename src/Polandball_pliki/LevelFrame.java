package Polandball_pliki;


import javax.swing.*;
import java.awt.*;

import static Polandball_pliki.GetConstans.*;


/**
 * Created by Matball on 2017-03-27.
 */


public class LevelFrame extends JFrame {

    /**
     * pomocnicza zmienna, okreslajaca wysokosc panelboard
     */

    public static int panelboardheight;

    /**
     * pomocnicza zmienna, okeslajaca szerokosc panelboard
     */

    public static int panelboardwidth;

    /**
     * pomocnicza zmienna, okreslajaca wysokosc gornego panelu
     */

    public static int panelinfooneheight;

    /**
     * pomocnicza zmienna, okreslajaca wysokosc bocznego panelu
     */

    public static int panelinfotwoheight;

    /**
     * pomocnicza zmienna, okreslajaca szerokosc gornego panelu
     */

    public static int panelinfotwowidth;



    public LevelFrame() {
        initLevelFrame();
    }

    private void initLevelFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Boardwidth,Boardheight);
        this.setLayout(null);

        MyPanel panelinfoone = new MyPanel();//panel gorny, w ktorym beda informacje dotyczace gry
        panelinfooneheight = (int)(0.2*Boardheight);
        panelinfoone.setSize(Boardwidth,panelinfooneheight);
        panelinfoone.setLocation(0,0);
        panelinfoone.setBackground(Color.WHITE);
        add(panelinfoone);
        panelinfoone.setVisible(true);

        MyPanel panelboard = new MyPanel();//panel, który będzie naszą plansza do dodawania elementów poziomu
        panelboardheight =(int)(0.8*Boardheight);
        panelboardwidth =(int)(0.8*Boardwidth);
        panelboard.setSize(panelboardwidth,panelboardheight);
        panelboard.setLocation(0,panelinfooneheight);
        panelboard.setBackground(Color.BLACK);
        add(panelboard);
        panelboard.setVisible(true);

        MyPanel panelinfotwo = new MyPanel();//panel boczny, w ktorym beda pozostale informacje
        panelinfotwoheight =(int)(0.8*Boardheight);
        panelinfotwowidth = (int)(0.2*Boardwidth);
        panelinfotwo.setSize(panelinfotwowidth,panelinfotwoheight);
        panelinfotwo.setLocation(panelboardwidth,panelinfooneheight);
        panelinfotwo.setBackground(Color.BLUE);
        add(panelinfotwo);
        panelinfotwo.setVisible(true);




    }




}
