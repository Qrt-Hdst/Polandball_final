package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.ChestOfGoldString;
import static Polandball_pliki.GetConstans.WingsOfHussarString;

/**
 * skrzynia ze złotem do zebrania
 */

public class ChestOfGold extends Item {

    /**
     * konstruktor obiektu klucz
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public ChestOfGold(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=ChestOfGoldString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(ChestOfGoldString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu skrzynia ze złotem");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
