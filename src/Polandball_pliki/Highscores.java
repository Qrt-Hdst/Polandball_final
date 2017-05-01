package Polandball_pliki;

import java.io.*;
import java.net.Socket;
/**
 * Klasa odpowiadaja za za pobranie z serwera najlepszych wynikow, sparsowanie pobranego pliku i wyswieltenie go
 */
public class Highscores {

    /**
     * zmienna serversocket, do ktorej przypisane jest gniazdo serwera
     */

    public static Socket serversocket;

    /**
     * konstruktor klasy Highscores
     */

    public Highscores(){

    }

    /**
     * metoda pobieraja z serwera liste najlepszych wynikow i zapisaja ja do swojego pliku highscores.xml
     * @param socket
     */

    public static void GetHighscores (Socket socket) {
        serversocket = socket;//------>do zmiany, przuczic do jakiegos konstruktora<------
        try {
        if (socket != null) {//sprawdzenie czy gniazdo serwera nie jest nullem(czy zostalo "cos" przypisane)
                socket.getInputStream().skip(socket.getInputStream().available());//pominiecie odpowiedzi serwera
                OutputStream outputstream = socket.getOutputStream();//strumien wyjsciowy
                PrintWriter printwriter = new PrintWriter(outputstream, true);
                printwriter.println("GET_HIGHSCORES");//utworzenie wiadomosci GET_HIGHSCORES
                InputStream inputstream = socket.getInputStream();//odebranie wiadomosci od serwera
                BufferedReader buildreader = new BufferedReader(new InputStreamReader(inputstream));
                String answer = buildreader.readLine();
                if (answer != "INVALID_COMMAND") {//sprawdzenie czy serwer zrozumial zadanie
                    PrintWriter out = new PrintWriter("src\\Polandball_pliki\\highscores.xml");
                    out.println(answer);//zapisanie odebranego tekstu do pliku podanego powyzej
                    out.close();
                }
            }
        else{
            System.out.println("Blad socketa");//w przypadku, gdy serversocket null
        }

        }catch (IOException e) {
            System.out.println("PLik nie mógł zostać pobrany z serwera");
            System.out.println(e);
        }


    }
}
