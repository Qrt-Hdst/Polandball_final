package Polandball_pliki;

/**
 * Panel gorny okna gry
 */

import javax.swing.*;
import java.awt.*;
import static Polandball_pliki.GetConstans.*;

public class PanelInfoOne extends JPanel{

    public PanelInfoOne(){ PanelInfoOne();}

    private void PanelInfoOne(){

        this.setSize(Boardwidth,panelinfooneheight);
        this.setLocation(0,0);
        this.setBackground(Color.GRAY);
        this.setLayout(new GridLayout(2,5));

        /**
         * dodanie pól informacji danej rozgrywki
         */
        JLabel LifeLabel1 = new JLabel("Ilość żyć:",JLabel.CENTER);
        LifeLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        LifeLabel1.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight*0.5));
        add(LifeLabel1);

        JLabel PointLabel1 = new JLabel("Ilość punktów:",JLabel.CENTER);
        PointLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        PointLabel1.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight*0.5));
        add(PointLabel1);

        JLabel TimeLabel1 = new JLabel("Czas:",JLabel.CENTER);
        TimeLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        TimeLabel1.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight*0.5));
        add(TimeLabel1);

        JLabel LevelLabel1 = new JLabel("Poziom:",JLabel.CENTER);
        LevelLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        LevelLabel1.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight*0.5));
        add(LevelLabel1);

        JLabel NameLabel1 = new JLabel("Nick:",JLabel.CENTER);
        NameLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        NameLabel1.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight*0.5));
        add(NameLabel1);

        JLabel LifeLabel2 = new JLabel("3",JLabel.CENTER);
        LifeLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        LifeLabel2.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight));
        add(LifeLabel2);

        JLabel PointLabel2 = new JLabel("0",JLabel.CENTER);
        PointLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        PointLabel2.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight));
        add(PointLabel2);

        JLabel TimeLabel2 = new JLabel("0:00",JLabel.CENTER);
        TimeLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        TimeLabel2.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight));
        add(TimeLabel2);

        JLabel LevelLabel2 = new JLabel("I",JLabel.CENTER);
        LevelLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        LevelLabel2.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight));
        add(LevelLabel2);

        JLabel NameLabel2 = new JLabel("polandball",JLabel.CENTER);
        NameLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        NameLabel2.setSize((int)(panelboardwidth*0.2),(int)(panelinfooneheight));
        add(NameLabel2);


          }

}
