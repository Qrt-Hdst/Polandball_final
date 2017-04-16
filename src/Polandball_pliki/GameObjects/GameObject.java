package Polandball_pliki.GameObjects;

import java.awt.image.BufferedImage;

/**
 * Created by Matball on 2017-04-16.
 */
public class GameObject  {
    int x_;
    int y_;
    BufferedImage buffImage_;
    public GameObject(int x,int y){
        x_=x;
        y_=y;
        buffImage_=null;
    }
    public GameObject(){
        x_=0;
        y_=0;
        buffImage_=null;
    }
}
