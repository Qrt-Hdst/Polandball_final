package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.SkrzynkaString;

/**
 * klasa skrzynka - obiekt który bedzie można zniszczyć za pomocą np. bomb
 */
public class Skrzynka extends Terrain {
    /**
     * konstruktor obiektu skrzynka
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Skrzynka(int x,int y){
        super();
        x_=x;
        y_=y;
        name_class_object=SkrzynkaString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(SkrzynkaString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu skrzynka");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
