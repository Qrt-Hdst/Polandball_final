package Polandball_pliki;

/**
 * Panel boczny okna gry
 */

import javax.swing.*;
import java.awt.*;
import static Polandball_pliki.GetConstans.*;

public class PanelInfoTwo extends JPanel{

    /**
     * kontruktor panelu drugiego, zawierajacy funkcję PanelInfoTwo
     */

    public PanelInfoTwo(){ PanelInfoTwo();}

    /**
     * funkcja zawierająca parametry panelu, komponenty z informacjami dodatkowymi o statystykach gracza
     */

    private void PanelInfoTwo()
    {
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

        JLabel Amountofordinarybomb = new JLabel(iloscbombzwyklych,JLabel.CENTER);
        Amountofordinarybomb.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Amountofordinarybomb);

        //ilość bomb zdalnych zawarta w pliku konfiguracyjnym
        String iloscbombzdalnych =Integer.toString(Amountofremotebombs);

        JLabel Amountofremotebomb = new JLabel(iloscbombzdalnych,JLabel.CENTER);
        Amountofremotebomb.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Amountofremotebomb);

        JLabel Husarswings = new JLabel("Skrzydła husarskie",JLabel.CENTER);
        Husarswings.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Husarswings);

        //ilość skrzydeł husarskich zawarta w pliku konfiguracyjnym
        String iloscskrzydelhusarskich =Integer.toString(Amountofhusarswings);

        JLabel Amountofhusarswings = new JLabel(iloscskrzydelhusarskich,JLabel.CENTER);
        Amountofhusarswings.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Amountofhusarswings);

        JLabel Laser = new JLabel("Laser",JLabel.CENTER);
        Laser.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Laser);

        //ilość laserów zawarta w pliku konfiguracyjnym
        String ilosclaserow =Integer.toString(Amountoflasers);

        JLabel Amountoflasers = new JLabel(ilosclaserow,JLabel.CENTER);
        Amountoflasers.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Amountoflasers);

        JLabel Key = new JLabel("Key",JLabel.CENTER);
        Key.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Key);

        //ilość zawarta w pliku konfiguracyjnym ( ->parametr do cheatowania ;)<- )
        String ilosckluczy =Integer.toString(Amountofkeys);

        JLabel Amountofkeys = new JLabel(ilosckluczy,JLabel.CENTER);
        Amountofkeys.setFont(new Font("Serif", Font.PLAIN, 30));
        add(Amountofkeys);

    }
}
