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
    int velX_;
    int velY_;
    BufferedImage buffImage_;

    /**
     * konstruktor obiektu
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public GameObject(int x,int y){
        x_=x;
        y_=y;
        velX_=0;
        velY_=0;
        buffImage_=null;
    }



    /**
     * konstruktor bezparametrowy
     */
    public GameObject(){
        x_=0;
        y_=0;
        velX_=0;
        velY_=0;
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
     * funkcja zwracająca wartość na x
     * @return x_ - polozenie na osi
     */
    public int getY(){
        return y_;
    }
    /**
     * funkcja zwracająca wartość na y
     * @return y_ - polozenie na osi
     */
    public BufferedImage getBuffImage(){
        return buffImage_;
    }

    /**
     * zwraca dotychczasowa predkosc na osi X
     * @return predkosc obiektu na osi x
     */
    public int get_velX(){return velX_;}
    /**
     * zwraca dotychczasowa predkosc na osi Y
     * @return predkosc obiektu na osi y
     */
    public int get_velY(){return velY_;}
    /**
     * zmienia pozycje obiektu na osi
     * @param
     */
    public void changeX(int x){
        x_=x;
    }

    /**
     *
     * @param y
     */
    public void changeY(int y){
        y_=y;
    }
    public void change_velX(int velX){
        velX_=velX;
    }
    public void change_velY(int velY){
        velY_=velY;
    }
}
