package Polandball_pliki.GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.HeartString;

/**
 * Klasa reprezentujaca pojedyncze zycie w grze
 */
public class Heart extends Item {
    /**
     * Konstruktor klasy Heart
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Heart(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=HeartString;
        buffImage_= GetConstans.HeartBuff;
    }
}
