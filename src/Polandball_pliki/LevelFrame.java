package Polandball_pliki;


import javax.swing.*;
import java.awt.*;

import static Polandball_pliki.GetConstans.*;


/**
 * Created by Matball on 2017-03-27.
 */


public class LevelFrame extends JFrame {




    public LevelFrame() {
        initLevelFrame();
    }

    private void initLevelFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Boardwidth,Boardheight);
        this.setLayout(null);


        PanelBoard panelboard = new PanelBoard();//panel, który będzie naszą plansza do dodawania elementów poziomu
        add(panelboard);
        panelboard.setVisible(true);

        PanelInfoOne panelinfoone = new PanelInfoOne();//panel gorny, w ktorym beda informacje dotyczace gry
        add(panelinfoone);
        panelinfoone.setVisible(true);

        PanelInfoTwo panelinfotwo = new PanelInfoTwo();//panel boczny, w ktorym beda pozostale informacje
        add(panelinfotwo);
        panelinfotwo.setVisible(true);


    }




}
