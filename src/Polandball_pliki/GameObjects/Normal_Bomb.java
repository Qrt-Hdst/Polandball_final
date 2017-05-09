package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.Normal_BombString;

/**
 * Created by Matball on 2017-05-09.
 */
public class Normal_Bomb extends Bomb {

    /**
     * konstruktor obiektu klucz
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Normal_Bomb(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(Normal_BombString);
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
