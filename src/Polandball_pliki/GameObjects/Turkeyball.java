package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.TurkeyBallString;

/**
 * Created by Matball on 2017-04-16.
 */
public class Turkeyball extends Enemy {
    public Turkeyball(int x,int y){
        x_=x;
        y_=y;
        try {
            File file = new File(TurkeyBallString);
            buffImage_ = ImageIO.read(file);
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
