package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.HeartString;
import static Polandball_pliki.Others.GetConstans.WingsOfHussarString;

/**
 * Dodatkowe życia do zebrania
 */
public class Heart extends Item {

    /**
     * konstruktor obiektu klucz
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Heart(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=HeartString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(HeartString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu serce");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
