package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.*;
/**
 * Klasa reprezentujaca gracza
 */
public class Polandball extends LivingObject {
    /**
     * Zmienna przechowujaca grafike lewych skrzydel husarskich
     */
    public BufferedImage leftHussar;
    /**
     * Zmienna przechowujaca grafike prawych skrzydel husarskich
     */
    public BufferedImage rightHussar;
    /**
     * Konstruktor klasy Polandball
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
        buffImage_=PolandBallBuff;
        leftHussar=LeftWingHussarballBuff;
        rightHussar=RightWingHussarballBuff;
        distance_from_elevation_walls=3/16;
        distance_from_azimuth_walls=3/16;
    }

    /**
     * Metoda zwracajaca grafike lewych skrzydel husarskich podczas trybu husarza
     * @return
     */
    public BufferedImage getLeftHussar() {
        return leftHussar;
    }
    /**
     * Metoda zwracajaca grafike prawych skrzydel husarskich podczas trybu husarza
     * @return
     */
    public BufferedImage getRightHussar() {
        return rightHussar;
    }
}
