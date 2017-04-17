package Polandball_pliki;

import static Polandball_pliki.GetConstans.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Okno główne, wyświetlane podczas uruchomienia gry
 */
public class MainFrame extends JFrame implements ActionListener {

    /**
     * Przycisk rozpoczynający nową grę
     */

    private JButton NewGame;

    /**
     * Przycisk wyświetlający listę najlepszych wyników
     */

    private JButton Highscores;

    /**
     * Przycisk wyjścia z programu
     */

    private JButton Ending;

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setBackground(Color.WHITE);//---------->dodac grafie<----------
        this.setSize(MainFramewidth,MainFrameheight);
        this.setLayout(null);
        setContentPane(new JLabel(new ImageIcon(BackgroundString)));
        /*
        JLabel Information = new JLabel("Informacje",JLabel.CENTER);
        Information.setBounds(0,0,30,10);
        add(Information);
        Information.setVisible(true);
        */

        //przycisk nowej gry
        NewGame = new JButton("Nowa Gra");
        NewGame.setFont(new Font("Serif", Font.PLAIN, 20));
        NewGame.setBounds(MainFramewidth/3,MainFrameheight/7,MainFramewidth/3,50);
        add(NewGame);
        NewGame.addActionListener(this);

        //przycisk do listy najlepszych wyników
        Highscores = new JButton("Najlepsze wyniki");
        Highscores.setFont(new Font("Serif", Font.PLAIN, 20));
        Highscores.setBounds(MainFramewidth/3,(3*MainFrameheight)/7,MainFramewidth/3,50);
        add(Highscores);

        //przycisk wyjścia z gry
        Ending = new JButton("Wyjście");
        Ending.setFont(new Font("Serif", Font.PLAIN, 20));
        Ending.setBounds(MainFramewidth/3,(5*MainFrameheight)/7,MainFramewidth/3,50);
        add(Ending);
        Ending.addActionListener(this);

    }


    /**
     * funkcja obsługująca naciśniecia przycisków
     * @param event
     */

    @Override
    public void actionPerformed(ActionEvent event) {
        //bierzemy buttony, które nasłuchują na zdarzenie
        Object source = event.getSource();
        //przejście do okna wyboru nicku postaci
        if(source==NewGame) {
            SetNameFrame setnameframe = new SetNameFrame();
            setnameframe.setVisible(true);
        }
        //przejscie do listy najlepszych wyników
        else if(source==Highscores){

        }
        //zamknięcie okna głownego, wyjście z gry
        else if(source==Ending)
            System.exit(0);

    }
}