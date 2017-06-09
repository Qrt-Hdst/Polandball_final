package Polandball_pliki;

/**
 * Panel gorny okna gry
 */

import javax.swing.*;
import java.awt.*;
import static Polandball_pliki.GetConstans.*;

public class PanelInfoOne extends JPanel{

    /**
     * Label odpowiedzialny za wyswietlanie ilosci zyc
     */
    public static JLabel LifeLabel2;

    /**
     * Label odpowiedzialny za wyswietlanie punktow
     */

    public static JLabel PointLabel2;

    /**
     * Label odpowiedzialny za pokazywanie, ile pozostalo czasu
     */

    public static  JLabel TimeLabel2;

    /**
     * Label odpowiedzialny za wyswietlanie aktualnego poziomu
     */

    public static JLabel LevelLabel2;

    /**
     * Zmienna przechowujaca nazwe gracza
     */

    public static String PlayerName;

    /**
     * konstruktor panelu pierwszego, zawierający funkcję PanelInfoOne
     */

    public PanelInfoOne(){ PanelInfoOne();}

    /**
     *  funkcja zawierająca parametry panelu, komponenty z informacjami o statystykach gracza
     */

    private void PanelInfoOne(){

        panelinfooneheight = (int)(0.2*Boardheight);
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

        LifeLabel2 = new JLabel(ilosczyc,JLabel.CENTER);
        LifeLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(LifeLabel2);

        //ilosc punktow, zawsze 0, konwersja
        String iloscpunktow = Integer.toString(Amountofpoints=0);

        PointLabel2 = new JLabel(iloscpunktow,JLabel.CENTER);
        PointLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(PointLabel2);

        //nizej zdefinoiwana metoda SetInitialTime()- wyswietlenie czasu poczatkowego
        TimeLabel2 = new JLabel(SetInitialTime(),JLabel.CENTER);
        TimeLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(TimeLabel2);

        LevelLabel2 = new JLabel(Integer.toString(WhiChLevel),JLabel.CENTER);
        LevelLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(LevelLabel2);

        //wyswietlenie nazwy gracza wczytanej w okienku wyboru nicku w odpowiednim labelu
        PlayerName = SetNameFrame.GetName();

        JLabel NameLabel2 = new JLabel(PlayerName,JLabel.CENTER);
        NameLabel2.setFont(new Font("Serif", Font.PLAIN, 30));
        add(NameLabel2);

    }

    /**
     * Metoda zwracajaca odpowiedni napis potrzebny do wyswietlania poczatkowego stanu zegara gry
     */

    public static String SetInitialTime(){

        //czas, od ktorego odliczamy
        String iloscczasu;
        //System.out.println((((double)(LevelTime))/60)-(int)(Math.floor(LevelTime/60)));
        //sprawdzenie czy czas jest podzielny przez 60 bez reszty + kilka innych sprawdzen zeby bylo dobrze wyswietlane na samym poczatku
        if ((LevelTime >= 60 && ((((double)(LevelTime))/60)-(int)(Math.floor(LevelTime/60))) == 0) || LevelTime < 10) {
            iloscczasu = Integer.toString((int) Math.floor(LevelTime / 60)) + ":0"
                    + Integer.toString((int) (LevelTime - (Math.floor(LevelTime / 60)) * 60));
            return iloscczasu;
        }else{
            iloscczasu = Integer.toString((int) Math.floor(LevelTime / 60)) + ":"
                    + Integer.toString((int) (LevelTime - (Math.floor(LevelTime / 60)) * 60));
            return iloscczasu;
        }
    }
}
