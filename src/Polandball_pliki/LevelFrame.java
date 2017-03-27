package Polandball_pliki;


import javax.swing.*;
import java.awt.*;

import static Polandball_pliki.GetConstans.Boardheight;
import static Polandball_pliki.GetConstans.Boardwidth;


/**
 * Created by Matball on 2017-03-27.
 */
public class LevelFrame extends JFrame {

    public LevelFrame() {
        this.setSize(Boardheight,Boardwidth);               //ustawia rozmiar z pliku konfiguracyjnego, zmienne int zainicjowane w GetConstans
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //mozemy wylaczac aplikacje iksem :o
        this.setVisible(true);                              //czyni widocznym
        DrawingPanel dpn=new DrawingPanel();
        this.add(dpn);
    }



    public static void main(String[] args) {        //tej funkcji jeszcze nie do konca ogarniam - ogolnie podejrzewam że to macza w jakiś
                                                    //w wątku tzn. -odmalowuje nam co chwila levelFrame wraz z ramkami

        EventQueue.invokeLater(() -> {
            LevelFrame levelFrame = new LevelFrame();
        });
    }
}
