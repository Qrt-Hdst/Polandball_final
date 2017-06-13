package Polandball_pliki;

import Polandball_pliki.Frame.SetConnection;
import java.io.IOException;


/**
 * główna funkcja programu
 */

public class Main {

    /**
     * Obiekt klasy Setconnection, tworzenie okna wyboru trybu rozgrywki
     */
    public static SetConnection setconnection;
    /**
     * funkcja główna programu, stworzenie okna menu głównego
     * @param args
     * @throws IOException
     */
    public static void main(String[]args)throws IOException {
        //utworzenie okna nawiazywania polaczenia w serwerem
        setconnection = new SetConnection();
        setconnection.setVisible(true);
    }
}
