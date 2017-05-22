package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.*;

/**
 * Klasa gracza typu Polandball
 */
public class Polandball extends LivingObject {
    /**
     * konstruktor obiektu gracza
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Polandball(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=0;
        velY_=0;
        name_class_object=PolandBallString;
        buffImage_=createBufferedImage();
        distance_from_elevation_walls=3/16;
        distance_from_azimuth_walls=3/16;
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(PolandBallString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
