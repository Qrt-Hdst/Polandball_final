package Polandball_pliki.GameObjects;

/**
 * Created by Matball on 2017-05-09.
 */

/**
 * klasa rodzic dla wszystkich rodzajow bomb
 */
public class Bomb extends StationaryObject {
    /**
     * konstruktor obiektu bomba
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Bomb(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=null;
    }

    /**
     * konstruktor bezparametrowy
     */
    public Bomb(){
        super();
        x_=0;
        y_=0;
        buffImage_=null;
    }
}
