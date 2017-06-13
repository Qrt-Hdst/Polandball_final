package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.BetonString;
import static Polandball_pliki.Others.GetConstans.PolandBallString;

/**
 * Created by Matball on 2017-04-17.
 */

/**
 * klasa przeciwnika typu Beton - niezniszczalnej ściany
 */
public class Beton extends Terrain {
    /**
     * konstruktor obiektu beton
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Beton(int x,int y){
        super();
        x_=x;
        y_=y;
        name_class_object=BetonString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(BetonString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu beton");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
