package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.*;
/**
 * Klasa gracza typu Polandball
 */
public class Polandball extends LivingObject {
    /**
     *
     */
    BufferedImage leftHussar;
    /**
     *
     */
    BufferedImage rightHussar;
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
        leftHussar=createLeftHussar();
        rightHussar=createRightHussar();
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
                BufferedImage bufferedImage = ImageIO.read(file);
                return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu Polandball");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage createLeftHussar() {
        try {
            File file = new File(HussarBall_leftString);
            BufferedImage image = ImageIO.read(file);
            return image;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu Polandball");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public BufferedImage createRightHussar() {
        try {
            File file = new File(HussarBall_rightString);
            BufferedImage image  = ImageIO.read(file);
            return image;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu Polandball");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getLeftHussar() {
        return leftHussar;
    }

    public BufferedImage getRightHussar() {
        return rightHussar;
    }

}
