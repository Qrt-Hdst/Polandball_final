
import java.io.BufferedReader;
import java.io.FileReader;
/*import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;*/

/**
 * klasa obslugujaca zlecenia od klienta otrzymane przez serwer
 */
public class ServerAction {

    /**
     * id kazdego zalogowanego klienta
     */

    private static int clientid =0;

    /**
     * zmienna infomrujaca o dostepnosci serwera, w przypadku false serwer nie przyjmuje klientow
     */

    private static boolean serveron= true;

    /**
     * funkcja oblsugujaca zadania od klienta
     * @param command zadanie klienta
     * @return odpowiedz serwera
     */
    public static String ServerResponse(String command){

        String servercommand=command;//przypisanie otrzymanego zadanie do zmiennej
        String servermessage;//wiadomosc zwracana przez metode
        //obsluga zadan na podstawie otrzymanej wiadomosci od klienta
        switch(servercommand){
            case "LOGIN":
                servermessage=login();//wywolanie metody zwracajacaj potwierdzenie o zalogowaniu
                break;
            case "GET_HIGHSCORES":
                servermessage=GetHighscores();//wywolanie metody GetHighscores, pobranie listy najlepszych wynikow
                break;
            default:
                servermessage="INVALID_COMMAND";//w przypadku nieznanego zadania zostanie wyswietlona informacja o
                break;                          //nieznanej komendzie
        }
        return servermessage;//zwrocenie wiadomosci serwera przez metode ServerResponse
    }

    /**
     * funkcja obslugujaca logowanie klientow
     * @return odpowiedz serwera
     */
    private static String login(){
        String servermessage;//wiadomsoc serwera
        if(serveron) {//sprawdzenie czy serwer moze przyjac klienta
            servermessage="LOGGED_IN "+clientid+"\n";//w przypadku zaakceptoawnia klienta serwer wysyla odpowiedz
            //LOGGED_IN i informacje o przydzielonym id klienta
            clientid++;//zwiekszenie id kolejnych zalogowanych klientow
        }
        else{
            servermessage="CONNECTION_REJECTED";//odrzucenie polaczenia, w przypadku wylaczenia mozliwosci przyjmowania
        }                                       //klientow
        return servermessage;//zwrocenie wiadomosci serwera przez metode login
    }


    /**
     * funkcja wczytujaca liste najlepszych wynikow
     * @return zwraca liste najlepszych wynikow
     */

    private static String GetHighscores(){
        //plik xml zawierajacy liste najlepszych wynikow jest pakowany do stringa, aby mozna bylo go wyslac jako
        //wiadomosc tekstowa
        StringBuilder stringbuilder = new StringBuilder();
        String currentline;
        try (
                //wczytywanie kolejnych linijek pliku i dodawanie ich do zmiennej stringbuilder
                BufferedReader buildreader = new BufferedReader(new FileReader("src\\highscores.xml"))){
                 while ((currentline = buildreader.readLine()) != null) {
                     stringbuilder.append(currentline);
            }
        }
        catch (Exception e){
            System.err.println("Nastapil niespodziewany blad");
        }
        return stringbuilder.toString();//zbudowany ciag konertowany na string i zwracany przez metode
    }


}
