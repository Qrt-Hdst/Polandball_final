package Polandball_pliki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import static Polandball_pliki.GetConstans.*;

/**
 * Klasa, tworzaca okno wyboru, czy chcemy korzystac z danych serwera czy nie
 */
public class SetConnection extends JFrame implements ActionListener {

    /**
     * prywatny obiekt klasy GetConstans, wykorzystanie sparsowanych plików
     */

    static public GetConstans getConstans_;//=new GetConstans();//<-----tego tu byc nie moze,

    /**
     * Label, zawierajacy pytanie o to, czy uzytkownik chce grac w trybie on czy off
     */

    private JLabel Question;

    /**
     * Przycisk zatwierdzajacy polaczenie z serwerm
     */

    private JButton Yes;

    /**
     * Przycisk anulujacy polaczenie z serwerem
     */

    private JButton No;

    /**
     *Bufor, do ktorego beda zapisane dane konfiguracyjne podstawowe
     * Jego pojemnosc jest dokladnie okreslona, zalezna od ilosci zmiennych konfiguracyjnych
     */

    public static String[] configbufor = new String[7];
    /**
     * Konstruktor klasy SetConnection, zawierajacy metode createFrame()
     */

    public SetConnection(){ createFrame();}

    /**
     * Metoda tworzaca okno wyboru trybu rozgrywki
     */

    private void createFrame(){
        //okno wyboru, czy chcemy korzystac z uslug serwera czy nie + odpowiednie parametry dla okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setSize(570,300);
        this.setLayout(null);

        //label z pytaniem, zeby bylo wiadomo, o co chodzi
        Question = new JLabel("Czy chcesz rozpocząć rozgrywkę w trybie online?");
        Question.setBounds(20,100,560,50);
        Question.setFont(new Font("Serif", Font.PLAIN, 25));
        add(Question);

        //przycisk do zatwierdzenia
        Yes = new JButton("Yes");
        Yes.setFont(new Font("Serif", Font.PLAIN, 20));
        Yes.setBounds(160,150,100,50);
        add(Yes);
        Yes.addActionListener(this );

        //przycisk do anulowania
        No = new JButton("No");
        No.setFont(new Font("Serif", Font.PLAIN, 20));
        No.setBounds(310,150,100,50);
        add(No);
        No.addActionListener(this );


    }

    /**
     * Funkcja obsługująca naciśniecia przycisków
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source==Yes) {
           try {
               //----------->NIE MA JESZCZE WSZYSTKICH DANYCH Z SERWERA<----------------
               MainFrame.ConnectToServer();//polaczenie z serwerem
               GetBasicConfig(MainFrame.MakeSocket());//pobranie danych konfiguracyjnych
               GetConstans.read_path_to_graphics();//wczytywanie grafik z folderu gry
               MakeBoardObstacleTable();//utworzenie tablicy statycznej do wykrywania kolzji
               EventQueue.invokeLater(() -> { //utworzenie okna menu
                   MainFrame mainframe = new MainFrame();
                   mainframe.setVisible(true);
               });
               this.setVisible(false);
           }catch (Exception e){
               System.out.println(e);
           }
        }
        if(source==No) {
            GetConstans getConstans_=new GetConstans();//
            //utworzenie okna menu
            EventQueue.invokeLater(() -> {
                MainFrame mainframe = new MainFrame();
                mainframe.setVisible(true);
            });
            this.setVisible(false);
        }
    }

    /**
     * Metoda pobierajaca dane konfiguracyje podstawowe aplikacji
     * @param socket
     */
    public void GetBasicConfig (Socket socket) {
        try {
            if (socket != null) {//sprawdzenie czy gniazdo serwera nie jest nullem(czy zostalo "cos" przypisane)
                socket.getInputStream().skip(socket.getInputStream().available());//pominiecie odpowiedzi serwera
                OutputStream outputstream = socket.getOutputStream();//strumien wyjsciowy
                PrintWriter printwriter = new PrintWriter(outputstream, true);//przywiazanie do strumienia wyjsciowego
                printwriter.println("GET_CONFIGFILE");//utworzenie wiadomosci GET_HIGHSCORES    //wiadomosci ktora bedziemy wysylac
                System.out.println("WYSLANO WIADOMOSC: GET_CONFIGFILE");
                InputStream inputstream = socket.getInputStream();//odebranie wiadomosci od serwera
                BufferedReader buildreader = new BufferedReader(new InputStreamReader(inputstream));
                String answer = buildreader.readLine();//przypisanie do stringa odczytanej wiadomosci
                if (answer != "INVALID_COMMAND") {//sprawdzenie czy serwer zrozumial zadanie
                    System.out.println("OTRZYMANO WIADOMOSC: " +answer);//wyswietlenie otrzymanej wiadomosci
                    configbufor = answer.split(" ");//podzial ciagu tekstu i zaladowanie do bufora
                    //wyswietlenie bufora, zeby sprawdzic czy dobre    wywalic
                    for(int i=0;i<7;i++){
                    System.out.println(configbufor[i]);}
                    SetBasicConfic();//wywolanie nizje zdefiniowanej metody
                }
                else if(answer == "INVALID_COMMAND"){
                    System.out.println("OTRZYMANO WIADOMOSC: " +answer);
                }
            }
            else{
                System.out.println("Blad socketa");//w przypadku, gdy serversocket null
            }
        }catch (IOException e) {
            System.out.println("Dane nie mogły zostać pobrane z serwera zostać pobrane z serwera lub wystapil inny blad");
            System.out.println(e);
        }
    }

    /**
     * Metoda przypisujaca pobrane podstawowe dane konfiguracyjne do zmiennych aplikacji
     */
    private void SetBasicConfic()
    {
        //przypisanie wartosci z bufora odpowiednim zmiennym
        Boardheight = Integer.valueOf(configbufor[0]);
        Boardwidth = Integer.valueOf(configbufor[1]);
        MainFrameheight = Integer.valueOf(configbufor[2]);
        MainFramewidth = Integer.valueOf(configbufor[3]);
        HighscoresFrameSize = Integer.valueOf(configbufor[4]);
        SpeedPlayer = Integer.valueOf(configbufor[5]);
        TimeToExplosion = Integer.valueOf(configbufor[6]);
    }
}
