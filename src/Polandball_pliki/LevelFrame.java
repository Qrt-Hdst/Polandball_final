package Polandball_pliki;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Matball on 2017-03-27.
 */
public class LevelFrame extends JFrame {

    public LevelFrame(int Boardheight,int Boardwidth){
        this.setSize(Boardheight,Boardwidth);               //ustawia rozmiar z pliku konfiguracyjnego
        getContentPane().setBackground(Color.BLACK);        //zapewnia tło
        this.setVisible(true);                              //czyni widocznym
    }
}
