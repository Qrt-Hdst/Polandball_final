package Polandball_pliki;

import static Polandball_pliki.GetConstans.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
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
     * Przycisk inicjalizujacy polaczenie z serwerem
     */

    private JButton ConnectToServer;
    /**
     * Adres IP serwera
     */

    private static String IPAddress;

    /**
     * Port serwera
     */

    private static int Port;

    /**
     * Gniazdo serwera
     */

    public static Socket serversocket;

    /**
     * Konstruktor okna głównego, zawierający funkcję initMainFrame
     */

    public MainFrame() {
        initMainFrame();
    }

    /**
     * metoda wczytujaca z pliku nr portu i adres ip serwera, tworzoca gniazdo
     * @return gniazdo serwera
     */
    public static Socket GetSocket() {
        try {//wczytanie pliku ipconfig
            BufferedReader buildreader = new BufferedReader(new FileReader("src\\Polandball_pliki\\ipconfig.txt"));
            IPAddress = buildreader.readLine();//przypisanie adresu ip serwera z pliku
            Port = Integer.parseInt(buildreader.readLine());//przypisanie nr portu serwera z pliku
            serversocket = new Socket(IPAddress, Port);//utworzenie gniazda
        } catch (Exception e) {
            System.out.println("Nie mozna odczytac pliku");
            System.out.println("Blad: " + e);

        }
        return serversocket;
    }
    /**
     * metoda odpowiadaja za polaczenie z serwerem
     */
    public static void ConnectToServer() {
        try {
            GetSocket();//wywolanie metody Getsocket(), utworzenie gniazda
            /*OutputStream outputstream = serversocket.getOutputStream();//strumien wyjsciowy
            PrintWriter printwriter = new PrintWriter(outputstream, true);//wypisywanie wiadomosci na strumien
            printwriter.println("LOGIN");//uwtorzenie wiadomosci LOGIN              //wyjsciowy
            System.out.println("WYSLANO WIADOMOSC: LOGIN");//wypisanie wiadomosci w konsoli
            InputStream inputstream = serversocket.getInputStream();//odebranie wiadomosci z serwera
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));//odczytanie wiadomosci bitowej
            String answer =  bufferedreader.readLine();//przypisanie odpowiedzi serwera do stringa
            if (answer.contains("LOGGED_IN")) {//jesli serwer odpowiedzial pomyslnie zwraca jest informacja o nawiazaniu polaczenia
                System.out.println("OTRZYMANO WIADOMOSC: " + answer);//wypisanie wiadomosci w konsoli
            }
            else {
                System.out.println("Nie mozna nawiazac polaczenia");
            }*/
        } catch (Exception e) {
            System.out.println("Nie mozna nawiazac polaczenia");
            System.out.println("Blad: " + e);
        }

    }


    /**
     * funkcja zawierająca parametry i komponenty okna głównego
     */

    private void initMainFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        NewGame.setBounds(MainFramewidth/3,MainFrameheight/9,MainFramewidth/3,50);
        add(NewGame);
        NewGame.addActionListener(this);

        //przycisk do polaczenia z serwerem
        ConnectToServer = new JButton("Połącz z serwerem");
        ConnectToServer.setFont(new Font("Serif", Font.PLAIN, 20));
        ConnectToServer.setBounds(MainFramewidth/3,(3*MainFrameheight)/9,MainFramewidth/3,50);
        add(ConnectToServer);
        ConnectToServer.addActionListener(this);

        //przycisk do listy najlepszych wyników
        Highscores = new JButton("Najlepsze wyniki");
        Highscores.setFont(new Font("Serif", Font.PLAIN, 20));
        Highscores.setBounds(MainFramewidth/3,(5*MainFrameheight)/9,MainFramewidth/3,50);
        add(Highscores);
        Highscores.addActionListener(this);

        //przycisk wyjścia z gry
        Ending = new JButton("Wyjście");
        Ending.setFont(new Font("Serif", Font.PLAIN, 20));
        Ending.setBounds(MainFramewidth/3,(7*MainFrameheight)/9,MainFramewidth/3,50);
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
        //polaczenie z serwerem
        else if(source==ConnectToServer){
            ConnectToServer();//wywolanie metody laczacej z serwerem
        }
        //przejscie do listy najlepszych wyników
        else if(source==Highscores){
                if(serversocket !=null) {
                    Highscores highscores = new Highscores();
                    highscores.GetHighscores(GetSocket());//pobranie danych z serwera, przypisanie do bufora
                    highscores.ShowHighscores();//wyswietlenie listy najlepszych wynikow
                }else{
                    System.out.println("Nie mozna wyswietlic listy najlepszych wyników");
                }
        }
        //zamknięcie okna głownego, wyjście z gry
        else if(source==Ending)
            System.exit(0);

    }
}