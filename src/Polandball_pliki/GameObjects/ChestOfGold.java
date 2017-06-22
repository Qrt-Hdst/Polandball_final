package GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.ChestOfGoldString;
import static Polandball_pliki.Others.GetConstans.WingsOfHussarString;

/**
 * Klasa reprezentujaca skrzynke ze zlotem
 */

public class ChestOfGold extends Item {

    /**
     * Konstruktor klasy ChestOfGold
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public ChestOfGold(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=ChestOfGoldString;
        buffImage_= GetConstans.ChestOfGoldBuff;
    }

}
