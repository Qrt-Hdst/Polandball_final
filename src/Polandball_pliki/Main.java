package Polandball_pliki;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Polandball_pliki.GetConstans.*;

/**
 * Created by Matball on 2017-03-27.
 */
public class Main {


    static private GetConstans getConstans_=new GetConstans();// jesli nie dam static to nie moge uzywac w funkcji static

    public static void main(String[]args)throws IOException {
        getConstans_=new GetConstans();

        EventQueue.invokeLater(() -> {
            LevelFrame levelFrame_= new LevelFrame();
            levelFrame_.setVisible(true);
        });
    }
}
