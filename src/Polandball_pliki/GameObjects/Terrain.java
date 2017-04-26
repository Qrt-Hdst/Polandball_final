package Polandball_pliki.GameObjects;

/**
 * Klasa przodek dla obiektow teronowych ( skrzynki, betony itd )
 */
public class Terrain extends StationaryObject {

    /**
     * konstruktor obiektu terenu
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public Terrain(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=null;
    }

    /**
     * konstruktor bezparametrowy
     */
    public Terrain(){
        super();
        x_=0;
        y_=0;
        buffImage_=null;
    }


}
