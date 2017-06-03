package Polandball_pliki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;

import static Polandball_pliki.GetConstans.MainFrameheight;
import static Polandball_pliki.GetConstans.MainFramewidth;

/**
 * Created by Matball on 2017-06-03.
 */

/**
 * Panel zawierający się w MainFrame, zawiera tło i guziki okna startowego (skalowalne)
 */
public class StartPanel extends JPanel implements ActionListener {

    /**
     * Zdjęcie, które ustawimy na tło
     */

    private Image img_;
    /**
     * Przycisk rozpoczynający nową grę
     */

    public JButton NewGame;

    /**
     * Przycisk wyświetlający listę najlepszych wyników
     */

    public JButton Highscores;

    /**
     * Przycisk wyjścia z programu
     */

    public JButton Ending;

    /**
     * Gniazdo serwera
     */

    public static Socket serversocket;

    /**
     * Adres IP serwera
     */

    private static String IPAddress;

    /**
     * Port serwera
     */

    private static int Port;

    public StartPanel(String img) {
        this(new ImageIcon(img).getImage()); // wywoluje konstruktor ImageContener(Image img)
    }



    public StartPanel(Image img){
        img_ = img;


        Dimension size = new Dimension(MainFramewidth, MainFrameheight);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);

        //przycisk nowej gry
        NewGame = new JButton("Nowa Gra");
        NewGame.setFont(new Font("Serif", Font.PLAIN, 20));
        add(NewGame);
        NewGame.addActionListener(this);


        //przycisk, ktory
        Highscores = new JButton("Najlepsze wyniki");
        Highscores.setFont(new Font("Serif", Font.PLAIN, 20));
        add(Highscores);
        Highscores.addActionListener(this);

        //przycisk wyjścia z gry
        Ending = new JButton("Wyjście");
        Ending.setFont(new Font("Serif", Font.PLAIN, 20));
        add(Ending);
        Ending.addActionListener(this);


        //setLayout(null);
    }

    public void paintComponent(Graphics g) {

        g.drawImage(img_, 0, 0, getWidth(), getHeight(), null);
        NewGame.setBounds(getWidth()/3,getHeight()/7,getWidth()/3,50);
        Highscores.setBounds(getWidth()/3,(3*getHeight())/7,getWidth()/3,50);
        Ending.setBounds(getWidth()/3,(5*getHeight())/7,getWidth()/3,50);

    }


    /**
     * Metoda zwracajaca utworzone gniazdo
     * @return gniazdo klienta
     */
    public static Socket GetSocket(){
        return serversocket;
    }

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
        //else if(source==ConnectToServer){
        // ConnectToServer();//wywolanie metody laczacej z serwerem
        // }
        //przejscie do listy najlepszych wyników
        else if(source==Highscores){
            if(serversocket !=null) {
                Highscores highscores = new Highscores();
                highscores.GetHighscores(MakeSocket());//pobranie danych z serwera, przypisanie do bufora
                highscores.ShowHighscores();//wyswietlenie listy najlepszych wynikow
            }else{
                System.out.println("Nie mozna wyswietlic listy najlepszych wyników");
            }
        }
        //zamknięcie okna głownego, wyjście z gry
        else if(source==Ending)
            System.exit(0);

    }

    /**
     * metoda wczytujaca z pliku nr portu i adres ip serwera, tworzoca gniazdo
     * @return gniazdo serwera
     */
    public static Socket MakeSocket() {
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
            MakeSocket();//wywolanie metody Getsocket(), utworzenie gniazda
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
}
