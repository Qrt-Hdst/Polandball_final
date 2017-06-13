package Polandball_pliki.Others;

import Polandball_pliki.Frame.LevelFrame;
import Polandball_pliki.Panel.PanelBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;


import static Polandball_pliki.Frame.MainFrame.startPanel;
import static Polandball_pliki.Frame.SetNameFrame.levelframe;
import static Polandball_pliki.Others.GetConstans.*;
import static Polandball_pliki.Panel.PanelBoard.ChangeInfoStatus;
import static Polandball_pliki.Panel.PanelInfoOne.PlayerName;


/**
 * Klasa odpowiedzialna za wyswietlenie okna konca gry
 */
public class GameOver extends JFrame implements ActionListener, WindowListener{

    /**
     * Label zawierajacy informacje o koncu gry
     */

    private JLabel GameOverLabel1;
    /**
     * Label zawierajacy infomracje o wyniku gry
     */

    private JLabel GameOverLabel2;

    /**
     * Przycisk, po wcisnieciu ktorego zostaje wyslana (jesli to mozliwe) wiadomsoc do serwera z nazwa gracza i wynikiem
     */

    private JButton Okey;
    /**
     * Konstruktor okna, zawierający funkcję initGameOver
     */

    public GameOver() {
        initGameOver();
    }

    /**
     * Metoda tworzaca okno konca gry
     */
    private void initGameOver(){

        this.setBackground(Color.WHITE);
        this.setSize(300,300);
        this.setLayout(null);
        this.addWindowListener(this);

        //dodanie do punktow premii za czas
        if(LevelTime>0){
            Amountofpoints = ChangeInfoStatus(Amountofpoints,(LevelTime+1)*PointsForSecond);
        }
        //i jeszcze wiecej punktow za zachowane itemy
        Amountofpoints = ChangeInfoStatus(Amountofpoints, (Amountoflifes + Amountofordinarybombs +
        Amountofremotebombs + Amountofhusarswings + Amountofkeys + Amountoflasers)*5);

        //label, zawierajacy informacje o koncu gry
        GameOverLabel1 = new JLabel("KONIEC GRY! ");
        GameOverLabel1.setBounds(50,50,300,50);
        GameOverLabel1.setFont(new Font("Serif", Font.PLAIN, 25));
        add(GameOverLabel1);

        //label, zawierajacy informacje o uzyskanym wyniku
        GameOverLabel2 = new JLabel("Twój wynik: " +Integer.toString(Amountofpoints));
        GameOverLabel2.setBounds(50,100,300,50);
        GameOverLabel2.setFont(new Font("Serif", Font.PLAIN, 25));
        add(GameOverLabel2);

        //Przycisk zatwierdzajacy wyslanie do serwera wyniku gracza, po tym nastepuje powrot do menu
        Okey = new JButton("OK");
        Okey.setBounds(100,150,100,30);
        add(Okey);
        Okey.addActionListener(this);

    }

    /**
     * Metoda oblusgujaca zdarzenia klasy GameOver
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source==Okey){
            try{
                SendScoreandName(startPanel.MakeSocket());//wywolanie nizej zdefiniowanej metody, wyslanie wyniku do serwera
                this.dispose();//setVisible(false);
                levelframe.dispose();//zamkniecie okna gry
                LevelFrame.tm.stop();//zatrzymanie timera
                PanelBoard.MakeDefaultOption(1);//przywrocenie domyslnych parametrow
                Amountofpoints=0;
            } catch(Exception e){
                System.out.println(e+"Blad przycisku OK - klasa GameOver");
            }
        }
    }

    /**
     * Metoda wykonuyjaca te same czynnosci po zamknieciu okna, co po wcisnieciu przecisku OK
     * @param e
     */
    public void windowClosing(WindowEvent e) {
        try{
            SendScoreandName(startPanel.MakeSocket());//wywolanie nizej zdefiniowanej metody, wyslanie wyniku do serwera
            this.dispose();//setVisible(false);
            levelframe.dispose();//zamkniecie okna gry
            LevelFrame.tm.stop();//zatrzymanie timera
            PanelBoard.MakeDefaultOption(1);//przywrocenie domyslnych parametrow
        }catch(Exception error){
            System.out.println(error+"Blad zamkniecia gameover - klasa GameOver, metoda windowClosing");
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

    /**
     * Metoda wysylajaca koncowy wynik danej rozgrywki wraz z nazwa gracza do serwera
     * @param socket
     */
    public void SendScoreandName(Socket socket){
        try {
            if (socket != null) {//sprawdzenie czy gniazdo serwera nie jest nullem(czy zostalo "cos" przypisane)
                OutputStream outputstream = socket.getOutputStream();//strumien wyjsciowy
                PrintWriter printwriter = new PrintWriter(outputstream, true);//przywiazanie do strumienia wyjsciowego
                //wiadomosc, ktora bedziemy wysylac - nick gracza + wynik gracza
                printwriter.println("PUT_SCORE "+ PlayerName + " " + Integer.toString(Amountofpoints)+ "\n");//utworzenie wiadomosci
                System.out.println("WYSLANO WIADOMOSC: PUT_SCORE " + PlayerName + " " + Integer.toString(Amountofpoints)+ "\n");
                InputStream inputstream = socket.getInputStream();//odebranie wiadomosci od serwera
                BufferedReader buildreader = new BufferedReader(new InputStreamReader(inputstream));
                String answer = buildreader.readLine();//przypisanie do stringa odczytanej wiadomosci
                System.out.println("OTRZYMANO WIADOMOSC: " +answer);//wyswietlenie otrzymanej wiadomosci
            }
            else{
                System.out.println("Blad socketa");//w przypadku, gdy serversocket null
            }
        }catch (IOException e) {
            System.out.println("Dane nie mogly zostac wyslane do serwera");
            System.out.println(e);
        }
    }
}
