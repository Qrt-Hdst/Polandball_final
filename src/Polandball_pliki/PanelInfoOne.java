package Polandball_pliki;

/**
 * Panel gorny okna gry
 */

import javax.swing.*;
import java.awt.*;
import static Polandball_pliki.GetConstans.*;

public class PanelInfoOne extends JPanel{

    /**
     * konstruktor panelu pierwszego, zawierający funkcję PanelInfoOne
     */

    public PanelInfoOne(){ PanelInfoOne();}

    /**
     *  funkcja zawierająca parametry panelu, komponenty z informacjami o statystykach gracza
     */

    private void PanelInfoOne(){

        this.setSize(Boardwidth,panelinfooneheight);
        this.setLocation(0,0);
        this.setBackground(Color.GRAY);
        this.setLayout(new GridLayout(2,5));//ustawienie siatki, aby pustawiac labele

        /****************************************************/
         //labele panela górnego, zawierające informaje o rozgrywce
        /****************************************************/

        JLabel LifeLabel1 = new JLabel("Ilość żyć:",JLabel.CENTER);
        LifeLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        add(LifeLabel1);

        JLabel PointLabel1 = new JLabel("Ilość punktów:",JLabel.CENTER);
        PointLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        add(PointLabel1);

        JLabel TimeLabel1 = new JLabel("Czas:",JLabel.CENTER);
        TimeLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        add(TimeLabel1);

        JLabel LevelLabel1 = new JLabel("Poziom:",JLabel.CENTER);
        LevelLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        add(LevelLabel1);

        JLabel NameLabel1 = new JLabel("Nick:",JLabel.CENTER);
        NameLabel1.setFont(new Font("Serif", Font.PLAIN, 30));
        add(NameLabel1);

        //ilosc zyc zawarta w pliku konfiguracyjnym
        String ilosczyc =Integer.toString(Amountoflifes);

        JLabel LifeLabel2 = new JLabel(ilosczyc,JLabel.CENTER);
        LifeLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(LifeLabel2);

        JLabel PointLabel2 = new JLabel("0",JLabel.CENTER);
        PointLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(PointLabel2);

        JLabel TimeLabel2 = new JLabel("0:00",JLabel.CENTER);
        TimeLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(TimeLabel2);

        JLabel LevelLabel2 = new JLabel("I",JLabel.CENTER);
        LevelLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(LevelLabel2);

        //stworzenie obiektu klasy klasy SetNameFrame w celu wczytania nazwy podanej w textfieldzi tej klasy
        String name;
        SetNameFrame nick = new SetNameFrame();
        name=nick.GetName();

        JLabel NameLabel2 = new JLabel(name,JLabel.CENTER);
        NameLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(NameLabel2);


    }

}
