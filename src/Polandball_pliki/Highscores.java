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

    public Highscores(){}

    /**
     * metoda pobieraja z serwera liste najlepszych wynikow i zapisaja ja do swojego pliku highscores.xml
     * @param socket
     */

    public static void GetHighscores (Socket socket) {
        serversocket = socket;//------>do zmiany, perzuczic do jakiegos konstruktora<------
        try {
        if (socket != null) {//sprawdzenie czy gniazdo serwera nie jest nullem(czy zostalo "cos" przypisane)
                socket.getInputStream().skip(socket.getInputStream().available());//pominiecie odpowiedzi serwera
                OutputStream outputstream = socket.getOutputStream();//strumien wyjsciowy
                PrintWriter printwriter = new PrintWriter(outputstream, true);//przywiazanie do strumienia wyjsciowego
                printwriter.println("GET_HIGHSCORES");//utworzenie wiadomosci GET_HIGHSCORES    //wiadomosci ktora bedziemy wysylac
                System.out.println("WYSLANO WIADOMOSC: GET_HIGHSCORES");
                InputStream inputstream = socket.getInputStream();//odebranie wiadomosci od serwera
                BufferedReader buildreader = new BufferedReader(new InputStreamReader(inputstream));
                String answer = buildreader.readLine();//przypisanie do stringa odczytanej wiadomosci
                if (answer != "INVALID_COMMAND") {//sprawdzenie czy serwer zrozumial zadanie
                    System.out.println("OTRZYMANO WIADOMOSC: " +answer);
                    FileWriter fileWriter = new FileWriter("src\\Polandball_pliki\\highscores.xml");
                    BufferedWriter bufferedwriter = new BufferedWriter(fileWriter);
                    String bufor[] = answer.split(" ");//----->PROBLEM Z PODZIELENIEM, NIE WIEM JAKI ZNAK<-----
                    for(int i=0; i<bufor.length;i++) {
                        bufferedwriter.write(bufor[i]);//zapisanie odebranego tekstu do pliku podanego powyzej
                        bufferedwriter.newLine();//znak nowej linni po kazdym zapisanym ciagu znakow
                    }
                    bufferedwriter.close();//zamkniecie pliku
                }
                else if(answer == "INVALID_COMMAND"){
                    System.out.println("OTRZYMANO WIADOMOSC: " +answer);
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
