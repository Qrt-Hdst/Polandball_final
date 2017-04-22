package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.Monsterspeed;
import static Polandball_pliki.GetConstans.NaziBallString;
import static Polandball_pliki.GetConstans.TurkeyBallString;

/**
 * Created by Matball on 2017-04-16.
 */
/**
 * Klasa przeciwnika typu TurkeyBall
 */
public class Turkeyball extends Enemy {
    /**
     * konstruktor obiektu turkeyballa
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public Turkeyball(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=Monsterspeed;
        velY_=Monsterspeed;
        buffImage_=createBufferedImage();
    }
    /**
     * metoda tworzca bufferedImage
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            File file = new File(TurkeyBallString);
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
