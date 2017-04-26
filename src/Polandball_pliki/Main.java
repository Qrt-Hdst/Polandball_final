package Polandball_pliki;

import java.awt.*;
import java.io.IOException;

/**
 * główna funkcja programu
 */

public class Main  {


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

        EventQueue.invokeLater(() -> {
            MainFrame mainframe = new MainFrame();
            mainframe.setVisible(true);

        });
    }
}
