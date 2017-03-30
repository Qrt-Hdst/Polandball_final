package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;

import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.LevelFrame.panelboardwidth;

public class PanelBoard extends JPanel{

    public PanelBoard(){PanelBoard();}

    private void PanelBoard() {

        this.setSize(panelboardwidth,panelboardheight);
        this.setLocation(0,panelinfooneheight);
        this.setBackground(Color.BLACK);

        try{
            String field[] = null; //do tej tablicy wpsizemy znaki z pliku konfiguracyjnego
            field = row.split(" "); //wpisywanie znakow
            //bedziemy "wklejac" obiekty graficzne po kolei, najpierw pierwszy wiersz  potem drugi itd
            int StartDrawingX = 0;//punkt X, od ktorego zaczniemy rysowanie obiektu
            int StartDrawingY = panelinfooneheight;//punkt Y, od ktorego zaczniemy rysowanie obiektu
            int SizeWidthIcon = panelboardwidth/Amountofcolumns;//szerokosc obiektu graficznego, zalezna od szerokosci panelu i ilosci kolumn
            int SizeHightIcon = panelboardheight/Amountoflines;//wysokosc obiektu graficznego, zalezna od wysokosci panelu i ilosci wierszy

            //NARAZIE DLA JEDNEGO WIERSZA
            for(int i=0;i<Amountoflines;i++){//do poprawy, sprawdzanie po kolei kazdego znaku
                if (field[i].equals("N_")) {
                    JLabel label1 = new JLabel(new ImageIcon(Nothing));
                    label1.setLocation(StartDrawingX, StartDrawingY);
                    label1.setSize(SizeWidthIcon, SizeHightIcon);
                    this.add(label1);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }
                else if (field[i].equals("B_")){
                    JLabel label2 = new JLabel(new ImageIcon(Beton));
                    label2.setLocation(StartDrawingX, StartDrawingY);
                    label2.setSize(SizeWidthIcon, SizeHightIcon);
                    this.add(label2);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }
                else if (field[i].equals("S_")){
                    JLabel label3 = new JLabel(new ImageIcon(Skrzynka));
                    label3.setLocation(StartDrawingX, StartDrawingY);
                    label3.setSize(SizeWidthIcon, SizeHightIcon);
                    this.add(label3);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }

            }
        }
        catch(NullPointerException e)
        {
            System.out.print("NullPointerException caught");
        }

    }

}
