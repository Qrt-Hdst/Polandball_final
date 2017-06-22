package GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.Normal_BombString;

/**
 * Klasa reprezentujaca zwykla bombe
 */

public class Normal_Bomb extends Bomb {

    /**
     * Konstruktor klasy Normal_bomb
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Normal_Bomb(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=null;
        image_= GetConstans.Normal_Bomb;
        name_class_object=Normal_BombString;
        explosionflag_=false;
    }


}
