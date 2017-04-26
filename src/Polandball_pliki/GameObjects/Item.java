package Polandball_pliki.GameObjects;

/**
 * klasa rodzic dla wszystkich przedmiotow do zebrania/interakcji
 */
public class Item extends StationaryObject {
    /**
     * konstruktor obiektu przedmiot
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Item(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=null;
    }

    /**
     * konstruktor bezparametrowy
     */
    public Item(){
        super();
        x_=0;
        y_=0;
        buffImage_=null;
    }
}
