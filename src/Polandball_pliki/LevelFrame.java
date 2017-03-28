package Polandball_pliki;


import javax.swing.*;
import java.awt.*;

import static Polandball_pliki.GetConstans.*;


/**
 * Created by Matball on 2017-03-27.
 */
public class LevelFrame extends JFrame {

    private JLabel[] arrayLabel_;
    private String[] arrayString_;

    public LevelFrame() {
        initUI();
    }

    private void initUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Boardheight,Boardwidth);

        /**
         * Ogólnie te dwie linie to taka troche magia która pozwala mi wyłączyć
         * domyślne Layouty  i ustawiać nasze kochane pola tak jak chce
         */
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);
        /**
         * męczyłem się żeby początkowo to było dynamicznie alokowa ale... w sumie lliczbe elementow
         * bedziemy wczytywac z config wiec mysle ze jak bedzie statyczne to też bedzie gut
         */
        arrayString_ =new String[10];
        arrayLabel_ = new JLabel[10];
        /**
         * Ogólnie cel jest taki by to co mamy w pliku rozbić na takie pola
         * wtedy juz wczytanie bedzie prawie gotowe
         */
        arrayString_[0]="N_";
        arrayString_[1]="N_";
        arrayString_[2]="B_";
        arrayString_[3]="S_";
        try{
        for(int i=0;i<4;i++){
                if (arrayString_[i].equals("N_")) {
                    arrayLabel_[i]=new JLabel(new ImageIcon(PolandBall));
                }
                else if (arrayString_[i].equals("B_")){
                    arrayLabel_[i]=new JLabel(new ImageIcon(NaziBall));
                }
                else if (arrayString_[i].equals("S_")){
                    arrayLabel_[i]=new JLabel(new ImageIcon(TurkeyBall));
                }
            }
        }
        catch(NullPointerException e)
        {
            System.out.print("NullPointerException caught");
        }
        /**
         * dodaje do naszego JFrame - w przyszlosci trzeba zrobic funkcje ktora to robi w petli
         */
        this.add(arrayLabel_[0]);
        this.add(arrayLabel_[1]);
        this.add(arrayLabel_[2]);
        this.add(arrayLabel_[3]);
        /**
         * zapisuje rozmiar naszych grafik
         */
        Dimension size = arrayLabel_[0].getPreferredSize();
        /**ustawiam element na ramce
         */
        arrayLabel_[0].setBounds(0, 0, size.width, size.height);
        arrayLabel_[1].setBounds(400, 0, size.width, size.height);
        arrayLabel_[2].setBounds(0, 400, size.width, size.height);
        arrayLabel_[3].setBounds(400, 400, size.width, size.height);
        setVisible(true);
    }



    public static void main(String[] args) {        //tej funkcji jeszcze nie do konca ogarniam - ogolnie podejrzewam że to macza w jakiś
                                                    //w wątku tzn. -odmalowuje nam co chwila levelFrame wraz z ramkami

        EventQueue.invokeLater(() -> {
            LevelFrame levelFrame = new LevelFrame();
            levelFrame.setVisible(true);
        });
    }
}
