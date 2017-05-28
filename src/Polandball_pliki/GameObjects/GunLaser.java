package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.GunLaserString;
import static Polandball_pliki.GetConstans.WingsOfHussarString;

/**
 * Item z bronią laserową do zebrania
 */
public class GunLaser extends Item {

    /**
     * konstruktor obiektu klucz
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public GunLaser(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=GunLaserString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(GunLaserString);
            BufferedImage bufferedImage= ImageIO.read(file);
            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu laser");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
