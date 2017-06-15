package Polandball_pliki.Frame;


import Polandball_pliki.Panel.SetNameFramePanel;

import javax.swing.*;
import java.awt.*;

import static Polandball_pliki.Others.GetConstans.*;


/**
 * Klasa, tworzaca okno wyboru, czy chcemy korzystac z danych serwera czy nie
 */
public class SetNameFrame extends JFrame {

    /**
     * Obiekt panelu SetNameFramePanel
     */
    public static SetNameFramePanel setNameFramePanel;
    /**
     * Konstruktor klasy SetConnection, zawierajacy metode createFrame()
     */
    public SetNameFrame(){ createFrame();}
    /**
     * Metoda tworzaca okno wyboru nazwy gracza
     */

    private void createFrame(){
        //okno wyboru, czy chcemy korzystac z uslug serwera czy nie + odpowiednie parametry dla okna
        setNameFramePanel=new SetNameFramePanel();
        this.getContentPane().add(setNameFramePanel);
        this.pack();
        this.setLocationRelativeTo(null);//ustawienie ramki na srodku
        this.setVisible(true);
    }

}


