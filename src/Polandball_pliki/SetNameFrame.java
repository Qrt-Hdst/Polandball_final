package Polandball_pliki;

import static Polandball_pliki.GetConstans.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Okno wyboru nazwy gracza
 */
public class SetNameFrame extends JFrame implements ActionListener {

    /**
     * label zawierjący prośbę o podanie swojej nazwy
     */

    private JLabel Info;

    /**
     * Pole tekstowe, gdzie należy podać swoją nazwę
     */

    private JTextField TextField;

    /**
     * Przycisk zatwierdzający wprowadzoną nazwę
     */

    private JButton Okey;

    /**
     * zmienna, do której będzie wczytywana nazwa gracza z textfielda
     */

    public static String nickname;

    /**
     * Konstruktor okna, zawierający funkcję initSetNameFrame
     */

    public SetNameFrame() {
        initSetNameFrame();
    }

    /**
     * funkcja zawierająca parametry i komponenty okna wyboru nazwy podczas gry
     */

    private void initSetNameFrame(){

        this.setBackground(Color.WHITE);
        this.setSize(400,300);
        this.setLayout(null);

        //pole, gdzie wspisujemy nazwę gracza, parametry tego okna nie są zawarte w pliku konfiguracyjnym
        TextField = new JTextField();
        TextField.setBounds(100,100,150,30);
        add(TextField);

        //przycisk zatwierdzający wpisany tekst
        Okey = new JButton("OK");
        Okey.setBounds(260,100,70,30);
        add(Okey);
        Okey.addActionListener(this);

    }

    /**
     * funkcja obsługująca zdarzenia okna - wczytywanie nazwy gracza
     * @param event
     */

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        //wczytanie nazwy gracza, zamknięcie okna, przejście do poziomu 1
        if(source==Okey){
            nickname = TextField.getText();
            LevelFrame levelframe = new LevelFrame();
            levelframe.setVisible(true);
            this.setVisible(false);
        }

    }

    /**
     * funkcja zwracająca tekst wpisany w textfielda
     */

    public String GetName(){
        return nickname;
    }

}
