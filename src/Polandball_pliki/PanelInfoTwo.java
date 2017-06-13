package Polandball_pliki;

/**
 * Panel boczny okna gry
 */

import javax.swing.*;
import java.awt.*;
import static Polandball_pliki.GetConstans.*;

public class PanelInfoTwo extends JPanel{

    /**
     * Label odpowiedzialny za wyswietlenie ilosci zwyklych bomb
     */

    public static JLabel Iloscbombzwyklych;

    /**
     * Label odpowiedzialny za wyswietlenie ilosci bomb zdalnych
     */

    public static JLabel Iloscbombzdalnych;

    /**
     * Label odpowiedzialny za wyswietlenie ilosci skrzydel husarskich
     */

    public static JLabel Iloscskrzydelhusarskich;

    /**
     * Label odpowiedzialny za wyswietlenie ilosci laserow
     */

    public static JLabel Ilosclaserow;

    /**
     * Label odpowiedzialny za wyswietlanie ilosci kluczy
     */

    public static JLabel Ilosckluczy;

    /**
     * kontruktor panelu drugiego, zawierajacy funkcję PanelInfoTwo
     */

    public PanelInfoTwo(){ PanelInfoTwo();}

    /**
     * funkcja zawierająca parametry panelu, komponenty z informacjami dodatkowymi o statystykach gracza
     */

    private void PanelInfoTwo()
    {
        panelboardwidth =(int)(0.8*Boardwidth);
        panelinfooneheight = (int)(0.2*Boardheight);
        panelinfotwoheight =(int)(0.8*Boardheight);
        panelinfotwowidth = (int)(0.2*Boardwidth);
        this.setSize(panelinfotwowidth,panelinfotwoheight);
        this.setLocation(panelboardwidth,panelinfooneheight);
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(9,1));

        /****************************************************/
        //labele panela górnego, zawierające dodatkowe informaje o rozgrywce
        /****************************************************/

        JLabel Amountofbomb = new JLabel("Ilość bomb:",JLabel.CENTER);
        Amountofbomb.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Amountofbomb);

        //ilość bomb zwykłych zawarta w pliku konfiguracyjnym
        String iloscbombzwyklych =Integer.toString(Amountofordinarybombs);

        Iloscbombzwyklych = new JLabel(iloscbombzwyklych,JLabel.CENTER);
        Iloscbombzwyklych.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Iloscbombzwyklych);

        //ilość bomb zdalnych zawarta w pliku konfiguracyjnym
        String iloscbombzdalnych =Integer.toString(Amountofremotebombs);

        Iloscbombzdalnych = new JLabel(iloscbombzdalnych,JLabel.CENTER);
        Iloscbombzdalnych.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Iloscbombzdalnych);

        JLabel Husarswings = new JLabel("Skrzydła husarskie",JLabel.CENTER);
        Husarswings.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Husarswings);

        //ilość skrzydeł husarskich zawarta w pliku konfiguracyjnym
        String iloscskrzydelhusarskich =Integer.toString(Amountofhusarswings);

        Iloscskrzydelhusarskich = new JLabel(iloscskrzydelhusarskich,JLabel.CENTER);
        Iloscskrzydelhusarskich.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Iloscskrzydelhusarskich);

        JLabel Laser = new JLabel("Laser",JLabel.CENTER);
        Laser.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Laser);

        //ilość laserów zawarta w pliku konfiguracyjnym
        String ilosclaserow =Integer.toString(Amountoflasers);

        Ilosclaserow = new JLabel(ilosclaserow,JLabel.CENTER);
        Ilosclaserow.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Ilosclaserow);

        JLabel Key = new JLabel("Key",JLabel.CENTER);
        Key.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Key);

        //ilość zawarta w pliku konfiguracyjnym ( ->parametr do cheatowania ;)<- )
        String ilosckluczy =Integer.toString(Amountofkeys);

        Ilosckluczy = new JLabel(ilosckluczy,JLabel.CENTER);
        Ilosckluczy.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Ilosckluczy);

    }
}
