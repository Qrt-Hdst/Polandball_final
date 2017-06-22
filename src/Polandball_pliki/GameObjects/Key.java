package Polandball_pliki.GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.KeyString;

public class Key extends Item {

    /**
     * Konstruktor klasy Key
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Key(int x,int y){
        super();
        x_=x;
        y_=y;
        name_class_object=KeyString;
        buffImage_= GetConstans.KeyBuff;
    }

}
