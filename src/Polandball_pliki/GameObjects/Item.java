package Polandball_pliki.GameObjects;

/**
 * Created by Matball on 2017-04-17.
 */
public class Item extends StationaryObject {
    public Item(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=null;
    }
    public Item(){
        super();
        x_=0;
        y_=0;
        buffImage_=null;
    }
}
