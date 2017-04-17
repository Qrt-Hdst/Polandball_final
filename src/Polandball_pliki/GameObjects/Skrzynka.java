package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.SkrzynkaString;

/**
 * Created by Matball on 2017-04-17.
 */
public class Skrzynka extends StationaryObject {
    /**
     * konstruktor obiektu gracza
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Skrzynka(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda tworzca bufferedImage
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
            System.out.println("Blad wczytywania obiektu");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
