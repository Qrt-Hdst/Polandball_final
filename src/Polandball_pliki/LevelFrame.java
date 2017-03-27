package Polandball_pliki;
import javax.swing.*;

import static Polandball_pliki.GetConstans.*;
/**
 * Created by Matball on 2017-03-27.			Boardheigh=Integer.parseInt(doc.getElementsByTagName("Boardheigh").item(0).getTextContent());
 Boardwidth=Integer.parseInt(doc.getElementsByTagName("Boardwidth").item(0).getTextContent());
 */
public class LevelFrame extends JFrame {

    public LevelFrame(Boardheigh,Boardwidth){

        this.setSize(Boardheigh,Boardwidth);
        this.setDefaultCloseOperation(LevelFrame.EXIT_ON_CLOSE);

    }

}
