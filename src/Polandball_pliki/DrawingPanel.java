package Polandball_pliki;

import javax.swing.*;
import java.awt.*;

import static Polandball_pliki.GetConstans.Boardheight;
import static Polandball_pliki.GetConstans.Boardwidth;

/**
 * Created by Matball on 2017-03-27.
 */
public class DrawingPanel extends JPanel{

    /**
     * Zmienna przechowujaca grafike
     */
    private ImageIcon icon;

    /**
     * Zmienna przechowujaca grafike
     */
    private ImageIcon icon2;


    /**
     * dlugosc naszego panela na osi x
     */

    private int x_;

    /**
     * szerokosc naszego panela na osi y
     */
    private int y_;

    //ogolnie rzecz biorac by sie przydało stworzyc jakiś plik xmla w którym byśmy przechowywali ścieszki do grafik
    public DrawingPanel(){

        this.setBackground(Color.BLACK);               //zapewnia tło w nasyzm kolorze
        loadImage("src\\GameGraphics\\Polandball.png");
        initPanel();
    }

    /**
     * funkcja ładujaca obrazek ze scieszki path
     * @param path_to_graphic
     */


    private void loadImage(String path_to_graphic){
        icon= new ImageIcon(path_to_graphic);       //definiuje instancje grafiki
    }

    /**
     *  tworzenie panela na ktorym bedzie sie znajował nasza grafika --funkcja jeszcze do przemyslenia
     */
    private void initPanel(){
        //int x_=Boardheight;
        //int y_=Boardwidth;
        int x_ =icon.getIconWidth();
        int y_ =icon.getIconHeight();
        setPreferredSize(new Dimension(x_,y_));     //definiuje prawdopodobnie rozmiar panelu- na razie testowo
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        icon.paintIcon(this, g,x_,y_ );
    }


}
