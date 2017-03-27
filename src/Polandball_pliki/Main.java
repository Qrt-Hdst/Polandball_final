package Polandball_pliki;

import java.io.IOException;

import static Polandball_pliki.GetConstans.*;

/**
 * Created by Matball on 2017-03-27.
 */
public class Main {

    //

    static private GetConstans getConstans_=new GetConstans();;// jesli nie dam static to nie moge uzywac w funkcji static

    public static void main(String[]args)throws IOException {
        getConstans_=new GetConstans();

        LevelFrame levelFrame_= new LevelFrame(Boardheight,Boardwidth);
    }
}
