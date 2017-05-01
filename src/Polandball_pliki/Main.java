package Polandball_pliki;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
/**
 * główna funkcja programu
 */

public class Main {

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
     * @return ----->zastanowic sie, co ma zwracac<------
     */
    private static Socket ConnectToServer() {
        try {
            GetSocket();//wywolanie metody Getsocket(), utworzenie gniazda
            OutputStream outputstream = serversocket.getOutputStream();//strumien wyjsciowy
            PrintWriter printwriter = new PrintWriter(outputstream, true);//wypisywanie wiadomosci na strumien
            printwriter.println("LOGIN");//uwtorzenie wiadomosci LOGIN              //wyjsciowy
            InputStream inputstream = serversocket.getInputStream();//odebranie wiadomosci z serwera
            BufferedReader buildreader = new BufferedReader(new InputStreamReader(inputstream));//odczytanie wiadomosci bitowej
            if (buildreader.readLine().contains("LOGGED_IN")) {//?jesli serwer odpowiedzial pomyslnie zwracane jest utworzone gniazdo?
                //return serversocket; ---> do przemyslenia, czy i co ma zwracac tu<------
                return null;
            }
        } catch (Exception e) {
            System.out.println("Nie mozna nawiazac polaczenia");
            System.out.println("Blad: " + e);
        }
        return null;
    }

    /**
     * prywatny obiekt klasy GetConstans, wykorzystanie sparsowanych plików
     */
    // jesli nie dam static to nie moge uzywac w funkcji static
    static private GetConstans getConstans_=new GetConstans();

    /**
     * funkcja główna programu, stworzenie okna menu głównego
     * @param args
     * @throws IOException
     */
    public static void main(String[]args)throws IOException {
        getConstans_=new GetConstans();
        ConnectToServer();//proba nawiazania polaczenia z serwerem
        EventQueue.invokeLater(() -> {
            MainFrame mainframe = new MainFrame();
            mainframe.setVisible(true);

        });
    }
}
