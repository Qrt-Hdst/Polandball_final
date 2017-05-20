package Polandball_pliki;

import java.io.IOException;

/**
 * główna funkcja programu
 */

public class Main {

    /**
     * funkcja główna programu, stworzenie okna menu głównego
     * @param args
     * @throws IOException
     */
    public static void main(String[]args)throws IOException {
        //utworzenie okna nawiazywania polaczenia w serwerem
        SetConnection setconnection = new SetConnection();
        setconnection.setVisible(true);
    }
}
