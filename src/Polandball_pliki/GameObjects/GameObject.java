package Polandball_pliki.GameObjects;

import java.awt.image.BufferedImage;

/**
 * Created by Matball on 2017-04-16.
 */

/**
 * Klasa-rodzic ruchomych obiektow
 */
public class GameObject  {
    int x_;
    int y_;

    BufferedImage buffImage_;

    /**
     * konstruktor obiektu
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public GameObject(int x,int y){
        x_=x;
        y_=y;
        buffImage_=null;
    }

    /**
     * konstruktor bezparametrowy
     */
    public GameObject(){
        x_=0;
        y_=0;
        buffImage_=null;
    }

    /**
     * funkcja zwracająca wartość na x
     * @return x_ - polozenie na osi
     */
    public int getX(){
        return x_;
    }
    /**
     * funkcja zwracająca wartość na y
     * @return y_ - polozenie na osi
     */
    public int getY(){
        return y_;
    }
    /**
     * funkcja zwracająca grafike przechowywaną przez obiekt
     * @return buffImage_ - grafika obiektu
     */
    public BufferedImage getBuffImage(){
        return buffImage_;
    }
    /**
     * zmienia pozycje obiektu na osi x
     * @param x nowe pozycja na osi x
     */

    public void changeX(int x){
        x_=x;
    }

    /**
     * Zmienia pozycje obiektu na osi Y
     * @param y nowa wartość pozycji
     */
    public void changeY(int y){
        y_=y;
    }


}
