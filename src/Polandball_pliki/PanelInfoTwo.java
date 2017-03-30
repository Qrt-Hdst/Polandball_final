package Polandball_pliki;

/**
 * Panel boczny okna gry
 */

import javax.swing.*;
import java.awt.*;
import static Polandball_pliki.GetConstans.*;

public class PanelInfoTwo extends JPanel{

    public PanelInfoTwo(){ PanelInfoTwo();}

    private void PanelInfoTwo()
    {
        this.setSize(panelinfotwowidth,panelinfotwoheight);
        this.setLocation(panelboardwidth,panelinfooneheight);
        this.setBackground(Color.BLUE);
    }
}
