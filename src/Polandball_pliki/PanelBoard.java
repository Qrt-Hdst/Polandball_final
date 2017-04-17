package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Polandball_pliki.GameObjects.*;
import com.sun.glass.ui.Size;

import static Polandball_pliki.GetConstans.*;


public class PanelBoard extends JPanel implements ActionListener,KeyListener{
    /**
     * Liczba przeciwnikow na planszy
     */
    private int number_of_enemy=0;

    /**
     * Tablica obiektow przeciwnikow
     */
    private ArrayList<Enemy> enemy=new ArrayList<>();

    /**
     * Nasz Gracz
     */
    private Polandball player;
    /**
     *  Lista obiektow stacjonarnych
     */
    private ArrayList<StationaryObject> stationaryObjects=new ArrayList<>();
    /**
     *Liczba obiektow stacjonarnych
     */
    private int number_of_stationary_object=0;

    /**
     *  Lista itemow (elementow do zebrania/uruchomienia interakcji przez gracza)
     */
    private ArrayList<Item> items=new ArrayList<>();
    /**
     *Liczba itemow na planszy
     */
    private int number_of_item=0;

    /**
     * tablica znakow, na ktorej jest zapisana plansza
     */
    private ArrayList<ArrayList<String>> field = new ArrayList<>();

    /**
     * konstruktor panelu głównego, zawierający funkcję PanelBoard
     */

    public PanelBoard(){PanelBoard();}

    /**
     * zmienna odliczajaca czas, wymagana przy interfejsie ActionListener i KeyListener
     */
    Timer tm=new Timer(5,this);
    /**
     * funkcja zawirająca parametry planszy, wczytywanie grafik do bufora
     */


    private void PanelBoard() {

        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //ogólne parametry planszy
        this.setSize(panelboardwidth, panelboardheight);
        this.setLocation(0, panelinfooneheight);
        this.setBackground(Color.BLACK);



            //dwuwymiarowa tablica, w której zawarte są kody poszczególnych pól planszy, wczytywane z pliku konfiguracyjnego

            for (int i=0;i<Amountoflines;i++) {
                field.add(new ArrayList<>());
                String bufor_string[]=row[i].split(" ");
                for(int j=0;j<Amountofcolumns;j++) {
                    field.get(i).add(bufor_string[j]);
                }
            }


            for(int i=0;i<Amountoflines;i++){
                for(int j=0;j<Amountofcolumns;j++) {
                    if (field.get(i).get(j).equals("N_")) {
                    } else if (field.get(i).get(j).equals("B_")) {
                        stationaryObjects.add(new Beton(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        number_of_stationary_object++;
                    } else if (field.get(i).get(j).equals("S_")) {
                        stationaryObjects.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        number_of_stationary_object++;
                    } else if (field.get(i).get(j).equals("NG")) {
                        player=new Polandball(SizeWidthIcon*(j),SizeHeightIcon*(i));
                    } else if (field.get(i).get(j).equals("NW")) {
                        enemy.add(new Sovietball(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        number_of_enemy++;
                    } else if (field.get(i).get(j).equals("ND")) {
                        items.add(new Door(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        number_of_item++;
                        stationaryObjects.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                        number_of_stationary_object++;
                    } else if (field.get(i).get(j).equals("NK")) {
                        items.add(new Key(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        number_of_item++;
                        stationaryObjects.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                        number_of_stationary_object++;
                    }
                }
            }


    }

    /**
     * metoda wywolywana przy wywołaniu jakiejś akcji (np. przesuniecia obiektu z punktu a do
     * @param e parametr przchowujacy informacje na temat zmian w programie
     */
    public void actionPerformed(ActionEvent e){
        player.changeX(player.getX()+player.get_velX());
        player.changeY(player.getY()+player.get_velY());
        repaint();//jesli sie zakomentuje tą linie to polandball nie bedzie sie poruszal, ale za to bedzie widziec elementy terenu
    }

    /**
     * metoda reagujaca na nacisniecie klawisza
     * @param e wydarzenie przechowujace informacje na temat wcisnietego klawisza
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT) {
            player.change_velX(SpeedPlayer * (-1));
            player.change_velY(0);
        } else if (c == KeyEvent.VK_RIGHT) {
            player.change_velX(SpeedPlayer * (1));
            player.change_velY(0);
        } else if (c == KeyEvent.VK_DOWN){
            player.change_velY(SpeedPlayer * (1));
            player.change_velX(0);
        }else if (c == KeyEvent.VK_UP){
            player.change_velY(SpeedPlayer * (-1));
            player.change_velX(0);
        }
    }

    /**
     * funkcja wymuszona przez intefejs, nieuzywana
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e){}

    /**
     * Metoda opisujaca co sie dzieje gdy nie jest wciskany zaden klawisz
     * @param e zmienna przechowujaca informacje o dzialaniach w danej chwili na klawiszach
     */
    @Override
    public void keyReleased(KeyEvent e) {
        player.change_velY(0);
        player.change_velX(0);
    }

    /**
     * punkt X, od ktorego zaczniemy rysowanie obiektu
     */

    public int StartDrawingX = 0;

    /**
     * punkt Y, od ktorego zaczniemy rysowanie obiektu
     */

    public int StartDrawingY = 0;

    /**
     * szerokosc obiektu graficznego, zalezna od szerokosci panela i ilosci kolumn
     */

    public int SizeWidthIcon = panelboardwidth/Amountofcolumns;

    /**
     * wysokosc obiektu graficznego, zalezna od wysokosci panela i ilosci wierszy
     */

    public int SizeHeightIcon = panelboardheight/Amountoflines;

    /*@Override
    public void update(Graphics g){
        paint(g);
    }*/

    /**
     * funkcja rysująca mape poziomu
     * @param g grafika na której "malujemy" elementy
     */

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,panelboardwidth,panelboardheight);
        drawItem(g);
        drawPlayerAndEnemy(g);
        drawStationaryObject(g);
        //drawBackground(g);

        //repaint();
    }
    public void drawItem(Graphics g){
        for(int i=0;i<number_of_item;i++){
            g.drawImage(items.get(i).getBuffImage(),items.get(i).getX(),items.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }
    /**
     * funkcja rysujaca gracza jak i jego wrogow
     * @param g grafika na która jest namalowywana grafika
     */
    public void drawPlayerAndEnemy(Graphics g){
        g.drawImage(player.getBuffImage(),player.getX(),player.getY(),SizeWidthIcon,SizeHeightIcon,null);
        for(int i=0;i<number_of_enemy;i++){
            g.drawImage(enemy.get(i).getBuffImage(),enemy.get(i).getX(),enemy.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }
    public void drawStationaryObject(Graphics g){
        for(int i=0;i<number_of_stationary_object;i++) {
            g.drawImage(stationaryObjects.get(i).getBuffImage(),stationaryObjects.get(i).getX(),stationaryObjects.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }


}
