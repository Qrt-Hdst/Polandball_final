package Polandball_pliki.GameObjects;

/**
 * Created by Matball on 2017-05-09.
 */

import java.awt.*;

/**
 * klasa rodzic dla wszystkich rodzajow bomb
 */
public class Bomb extends StationaryObject {

    /**
     * Flaga informuja, czy bomba ma wybuchnac
     */
    boolean explosionflag_=false;

    /**
     * Metoda zwracajaca obecny stan explosionflag_
     * @return
     */

    public boolean getExplosionflag(){
        return explosionflag_;
    }

    /**
     * Metoda ustawiajaca explosionflag_
     * @param explosionflag
     */
    public void setExplosionflag(boolean explosionflag){
        explosionflag_=explosionflag;
    }
    /**
     * Zmienna, przechowujaca gif
     */

    Image image_;

    /**
     * konstruktor obiektu bomba
     *
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Bomb(int x, int y) {
        super();
        x_ = x;
        y_ = y;
        buffImage_ = null;
        image_ = null;
        explosionflag_ =false;
    }

    /**
     * konstruktor bezparametrowy
     */
    public Bomb() {
        super();
        x_ = 0;
        y_ = 0;
        buffImage_ = null;
        image_ = null;
        explosionflag_=false;
    }

    /**
     * Wraca Image bedacy gifem
     *
     * @return
     */
    public Image getGIF() {
        return image_;
    }


}