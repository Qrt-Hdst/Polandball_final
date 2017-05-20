package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.Monsterspeed;
import static Polandball_pliki.GetConstans.NaziBallString;
import static Polandball_pliki.GetConstans.SovietBallString;

/**
 * Klasa przeciwnika typu SovietBall
 */
public class Sovietball extends Enemy {
    /**
     * konstruktor obiektu Sovietball
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public Sovietball(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=0;//Monsterspeed;
        velY_=0;//Monsterspeed;
        name_class_object=SovietBallString;
        buffImage_=createBufferedImage();
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(SovietBallString);
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
