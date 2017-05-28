package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.KeyString;
import static Polandball_pliki.GetConstans.WingsOfHussarString;

/**
 * Item Skrzydła hussarskie do zebrania
 */
public class WingsOfHussar extends Item {

    /**
     * konstruktor obiektu klucz
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public WingsOfHussar(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=WingsOfHussarString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(WingsOfHussarString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu skrzydła hussarksie");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
