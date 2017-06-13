package Polandball_pliki;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Matball on 2017-06-13.
 */
public class ButtonLabel extends JButton {

    public ButtonLabel(String text) {
        this.setText(text);
       this.setFont(new Font("Serif", Font.PLAIN, 30));
       this.setFocusPainted(false);
       this.setMargin(new Insets(0, 0, 0, 0));
       this.setContentAreaFilled(false);
       this.setBorderPainted(false);
       this.setOpaque(false);
    }
}
