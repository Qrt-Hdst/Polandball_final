package Polandball_pliki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.PanelBoard.ChangeInfoStatus;
import static Polandball_pliki.SetNameFrame.levelframe;
import static Polandball_pliki.SetConnection.LoadLevel;

/**
 * Klasa odpowiedzialna za wyswietlenie okna o ukonczeniu poziomu
 */
public class NextLevelInfo extends JFrame implements ActionListener, WindowListener {

    /**
     * Label zawierajacy informacje okna
     */

    private JLabel NextLevelInfoLabel;

    /**
     * Przycisk, po wcisnieciu ktorego zostaje zaladowany nowy poziom
     */
    private JButton Okey;

    /**
     * Kopia liczby zyc z poprzedniego poziomu
     */

    public int Amountoflifescopy;

    /**
     * Kopia liczby punktow z poprzedniego poziomu
     */

    public int Amountofpointscopy;

    /**
     * Kopia liczby bomb zwyklych
     */

    public int Amountofordinarybombscopy;

    /**
     * Kopia licbzy bomb zdalnych
     */

    public int Amountofremotebombscopy;

    /**
     * Kopia liczby skrzydel husarskich
     */

    public int Amountofhusarswingscopy;

    /**
     * Kopia liczby laserow
     */

    public int Amountoflaserscopy;

    /**
     * Konstruktor okna, zawierający funkcję initGameOver
     */

    public NextLevelInfo() {
        initNextLevelInfo();
    }

    /**
     * Metoda tworzaca okno konca gry
     */
    private void initNextLevelInfo() {

        this.setBackground(Color.WHITE);
        this.setSize(600, 200);
        this.setLayout(null);
        this.addWindowListener(this);


        //label, zawierajacy informacje o ukonczeniu poziomu
        NextLevelInfoLabel = new JLabel("Gratulacje ukonczyłeś "+Integer.toString(WhiChLevel)+" poziom!");
        NextLevelInfoLabel.setBounds(100, 50, 400, 50);
        NextLevelInfoLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        add(NextLevelInfoLabel);

        //Przycisk zatwierdzajacy wyslanie do serwera wyniku gracza, po tym nastepuje powrot do menu
        Okey = new JButton("OK");
        Okey.setBounds(250, 100, 100, 30);
        add(Okey);
        Okey.addActionListener(this);

    }

    /**
     * Metoda obslugujaca wcisniecie przycisku, przejscie do nastepnego poziomu
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source==Okey){
            try{
                WhiChLevel++;//zwiekszenie poziomu
                LoadLevel=true;//ustawianie na true zeby bylo dobrze
                CopyParameters();//skopiowanie parametrow, z ktorymi gracz zakonczyl poziom i kore maja byc uwzglednione w nastepnym
                this.dispose();//setVisible(false);
                levelframe.dispose();//zamkniecie okna gry
                LevelFrame.tm.stop();//zatrzymanie timera
                PanelBoard.MakeDefaultOption(WhiChLevel);//przywrocenie domyslnych parametrow dla danego poziomu
                if(LastLevel==false && LoadLevel == true) {//sprawdzenie czy to nie ostatni poziom
                    IncludeParametersFromThePreviousLevel();//aktualizacja wczytanych parametrow o parametry z poprzedniego poziomu
                    Thread.sleep(200);//uspanie watku, zeby wszystko sie na nowo przleiczylo
                    levelframe = new LevelFrame();
                    levelframe.setVisible(true);
                }

            } catch(Exception e){
                System.out.println(e+"Blad przycisku OK - klasa NextLevelInfo");
            }
        }
    }
    public void windowClosing(WindowEvent e) {
    }
    public void windowActivated(WindowEvent e) {
    }
    public void windowClosed(WindowEvent e) {
    }
    public void windowDeactivated(WindowEvent e) {
    }
    public void windowDeiconified(WindowEvent e) {
    }
    public void windowIconified(WindowEvent e) {
    }
    public void windowOpened(WindowEvent e) {
    }

    /**
     * Metoda kopiujaca odpowiednie parametry, ktore maja byc zachowane w nastepnym poziomie
     */

    private void CopyParameters()
    {
        Amountoflifescopy = Amountoflifes;
        Amountofpointscopy = Amountofpoints + (PointsForSecond*(LevelTime+1));
        Amountofordinarybombscopy = Amountofordinarybombs;
        Amountofremotebombscopy = Amountofremotebombs;
        Amountofhusarswingscopy = Amountofhusarswings;
        Amountoflaserscopy = Amountoflasers;
    }

    /**
     * Metoda uwzgledniajaca osiagniecia (parametry) z poprzedniego poziomiu w biezacym poziomie
     */

    private void IncludeParametersFromThePreviousLevel(){
        Amountoflifes = ChangeInfoStatus(Amountoflifes,Amountoflifescopy);
        Amountofpoints = ChangeInfoStatus(Amountofpoints,Amountofpointscopy);
        Amountofordinarybombs = ChangeInfoStatus(Amountofordinarybombs,Amountofordinarybombscopy);
        Amountofremotebombs = ChangeInfoStatus(Amountofremotebombs,Amountofremotebombscopy);
        Amountofhusarswings = ChangeInfoStatus(Amountofhusarswings,Amountofhusarswingscopy);
        Amountoflasers = ChangeInfoStatus(Amountoflasers,Amountoflaserscopy);
    }
}