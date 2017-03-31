package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;

import static Polandball_pliki.GetConstans.*;
//import static Polandball_pliki.LevelFrame.panelboardwidth;

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


            int default_width_image_from_file=400;//domyslna szerokosc pliku w GameGraphics - potem dodam do stałych
            int default_height_image_from_file=400;//domyslna wysokosc pliku w GameGraphics - potem dodam do stałych
            int scaleGraphic=5;//stala przez ktora bedziemy dzielic rozmiary obraza
            //NARAZIE DLA JEDNEGO WIERSZA
            for(int i=0;i<Amountoflines;i++){//do poprawy, sprawdzanie po kolei kazdego znaku
                if (field[i].equals("N_")) {
                    JLabel label1 = new JLabel(new ImageIcon(scaleImage(Nothing,default_height_image_from_file/scaleGraphic,default_width_image_from_file/scaleGraphic)));
                    label1.setLocation(StartDrawingX, StartDrawingY);
                    this.add(label1);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }
                else if (field[i].equals("B_")){
                    JLabel label2 = new JLabel(new ImageIcon(scaleImage(Beton,default_height_image_from_file/scaleGraphic,default_width_image_from_file/scaleGraphic)));
                    label2.setLocation(StartDrawingX, StartDrawingY);
                    this.add(label2);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }

                else if (field[i].equals("S_")){
                    JLabel label2 = new JLabel(new ImageIcon(scaleImage(Skrzynka,default_height_image_from_file/scaleGraphic,default_width_image_from_file/scaleGraphic)));
                    label2.setLocation(StartDrawingX, StartDrawingY);
                    this.add(label2);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }
                else if (field[i].equals("NG")){
                    JLabel label3 = new JLabel(new ImageIcon(scaleImage(PolandBall,default_height_image_from_file/scaleGraphic,default_width_image_from_file/scaleGraphic)));
                    label3.setLocation(StartDrawingX, StartDrawingY);
                    this.add(label3);
                    StartDrawingX = StartDrawingX+SizeWidthIcon;
                }
                else if (field[i].equals("NW")){
                    JLabel label3 = new JLabel(new ImageIcon(scaleImage(TurkeyBall,default_height_image_from_file/scaleGraphic,default_width_image_from_file/scaleGraphic)));
                    label3.setLocation(StartDrawingX, StartDrawingY);
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

    /**
     * funkcja "skalujaca" naszą grafike
     * @param path_to_graphic scieszka do pliku
     * @param w  szerokosc jaka chcemy by mial obrazek bedacy efektem skalowani
     * @param h  szerokosc jaka chcemy by mial obrazek bedacy efektem skalowani
     * @return
     */

    private Image scaleImage(String path_to_graphic,int w,int h) {
        ImageIcon original_ImageIcon=new ImageIcon(path_to_graphic);//Tworze zdjęcie naszej Grafiki do obiekt nieabstrakcyjnej klasy ImageIcon
        Image originalImage_=original_ImageIcon.getImage();//wyciagam z niej do abstrakcyjenj obiektu klasy Image zdjęcie
        Image scaled = originalImage_.getScaledInstance(w,h,Image.SCALE_SMOOTH);//skaluje do podanych parametrów skali

        return scaled;//skaluje

    }


}
