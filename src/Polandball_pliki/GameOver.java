package Polandball_pliki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.PanelInfoOne.PlayerName;

/**
 * Klasa odpowiedzialna za wyswietlenie okna konca gry
 */
public class GameOver extends JFrame implements ActionListener{

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
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source==Okey){
            try{
                SendScoreandName(MainFrame.MakeSocket());//wywolanie nizej zdefiniowanej metody, wyslanie wyniku do serwera
                this.dispose();//setVisible(false);
            } catch(Exception e){
                System.out.println(e+"Blad przycisku OK - klasa GameOver");
            }
        }
    }
    public void SendScoreandName(Socket socket){
        try {
            if (socket != null) {//sprawdzenie czy gniazdo serwera nie jest nullem(czy zostalo "cos" przypisane)
                OutputStream outputstream = socket.getOutputStream();//strumien wyjsciowy
                PrintWriter printwriter = new PrintWriter(outputstream, true);//przywiazanie do strumienia wyjsciowego
                //wiadomosci ktora bedziemy wysylac
                printwriter.println("PUT_SCORE "+ PlayerName + " " + Integer.toString(Amountofpoints)+ "\n");//utworzenie wiadomosci
                System.out.println("WYSLANO WIADOMOSC: PUT_SCORE ");
               InputStream inputstream = socket.getInputStream();//odebranie wiadomosci od serwera
                BufferedReader buildreader = new BufferedReader(new InputStreamReader(inputstream));
                String answer = buildreader.readLine();//przypisanie do stringa odczytanej wiadomosci
                if (answer != "INVALID_COMMAND") {//sprawdzenie czy serwer zrozumial zadanie
                    System.out.println("OTRZYMANO WIADOMOSC: " +answer);//wyswietlenie otrzymanej wiadomosci

                }
                else if(answer == "INVALID_COMMAND"){
                    System.out.println("OTRZYMANO WIADOMOSC: " +answer);
                }
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
