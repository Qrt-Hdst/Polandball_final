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
import java.util.Random;

import Polandball_pliki.GameObjects.*;
import com.sun.glass.ui.Size;
import com.sun.javafx.scene.control.behavior.KeyBinding;

import static Polandball_pliki.GetConstans.*;


public class PanelBoard extends JPanel implements ActionListener,KeyListener{

    /**
     * Tablica obiektow przeciwnikow
     */
    private ArrayList<Enemy> enemy=new ArrayList<>();

    /**
     * Nasz Gracz
     */
    private Polandball player;
    /**
     * Tablica obiektow przeciwnikow
     */
    private ArrayList<Bomb> bomb=new ArrayList<>();
    /**
     *  Lista obiektow bedacych elementami terenu
     */
    private ArrayList<Terrain> terrains=new ArrayList<>();

    /**
     *  Lista itemow (elementow do zebrania/uruchomienia interakcji przez gracza)
     */
    private ArrayList<Item> items=new ArrayList<>();

    /**
     * tablica znakow, na ktorej jest zapisana plansza
     */
    private ArrayList<ArrayList<String>> field = new ArrayList<>();

    /**
     * tablica, do ktorej beda rozdzielane ciagi znakow z pliku konfiguracyjnego
     */

    public String bufor_string[];

    /**
     * konstruktor panelu głównego, zawierający funkcję PanelBoard
     */

    public PanelBoard(){PanelBoard();}

    /**
     * zmienna odliczajaca czas, wymagana przy interfejsie ActionListener i KeyListener
     */
    Timer tm=new Timer(5,this);
    /**
     * konstruktor zawirający parametry planszy, okreslenie liczby i polozenie GameObject jakie sie znajdą w grze
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
                bufor_string=row[i].split(" ");
                for(int j=0;j<Amountofcolumns;j++) {
                    field.get(i).add(bufor_string[j]);
                }
            }




            for(int i=0;i<Amountoflines;i++){
                for(int j=0;j<Amountofcolumns;j++) {
                    if (field.get(i).get(j).equals("N_")) {
                    } else if (field.get(i).get(j).equals("B_")) {
                        terrains.add(new Beton(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                    } else if (field.get(i).get(j).equals("S_")) {
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                    } else if (field.get(i).get(j).equals("NG")) {
                        player=new Polandball(SizeWidthIcon*(j),SizeHeightIcon*(i));
                    } else if (field.get(i).get(j).equals("NW")) {
                        enemy.add(lottery_of_enemies(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                    } else if (field.get(i).get(j).equals("ND")) {
                        items.add(new Door(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    } else if (field.get(i).get(j).equals("NK")) {
                        items.add(new Key(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    }
                }
            }
            //metoda tworzaca watki dla wrogow, zdefiniowana pozniej, przeniesiona
            //zmienic nazwe, storzyc inna metoda ktora przyjmie zmieniona predkosc i bedzie przemieszczala wrogow
            moveEnemies();

    }

    /**
     * funkcja losujaca typ potwora
     */
    public Enemy lottery_of_enemies(int position_enemyX,int position_enemyY){
        //instancja klasy enemy do ktorej w zaleznosci od losu przypisze konkretny enemyballa
        Enemy enemy_;
        //inicjuje zmienna losujaca
        Random randomGenerator=new Random();
        //losuje liczbe od 0 do 2
        int fate= randomGenerator.nextInt(3);

        if(fate==0){
            enemy_=new Turkeyball(position_enemyX,position_enemyY);
        }else if(fate==1){
            enemy_=new Naziball(position_enemyX,position_enemyY);
        }else if(fate==2){
            enemy_=new Sovietball(position_enemyX,position_enemyY);
        }else {
            enemy_=null;
        }
        return enemy_;

    }


    /**
     * metoda wywolywana przy wywołaniu jakiejś akcji (np. przesuniecia obiektu z punktu a do b)
     * @param e parametr przchowujacy informacje na temat zmian w programie
     */
    public void actionPerformed(ActionEvent e) {
        //funkcja odpowiedzialna za poruszenie gracza
        movePlayer();

        //moveEnemies();
        //odmalowywanie gracza po kazdym wykrytym zdarzeniu
        repaint();
    }

    /**
     * metoda do wywołania zmiany polozenia polandballa
     */

    void movePlayer() {
        boolean I_can_go=new Collision(player,"player").isNotCollision;

        if(I_can_go){
            player.changeX(player.getX() + player.get_velX());
            player.changeY(player.getY() + player.get_velY());
        }

    }
    /**
     * metoda obslugujaca zmiane polozenie wrogów
     */

    void moveEnemies(){
        try {
                for (int i = 0; i < enemy.size(); i++) {
                    //System.out.println(0);
                    new Thread(enemy.get(i).getEnemy()).start();//(new Enemy(enemy.get(i).getEnemy())).start();
                }

        }
        catch(Exception e){
            System.out.println("Blad w watku");
            System.out.println(e);
        }
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
        //stawianie bomby 
        else if (c == KeyEvent.VK_SPACE){
            bomb.add(new Normal_Bomb(player.getX(),player.getY()));
        }
    }

    /**
     * funkcja wymuszona przez intefejs, nieuzywana
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e){

    }

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
     * szerokosc obiektu graficznego, zalezna od szerokosci panela i ilosci kolumn
     */

    public static int SizeWidthIcon = panelboardwidth/Amountofcolumns;

    /**
     * wysokosc obiektu graficznego, zalezna od wysokosci panela i ilosci wierszy
     */

    public static int SizeHeightIcon = panelboardheight/Amountoflines;


    /**
     * funkcja rysująca mape poziomu
     * @param g grafika na której "malujemy" elementy
     */

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,panelboardwidth,panelboardheight);
        drawBomb(g);
        drawItem(g);
        drawLivingObject(g);
        drawTerrain(g);
    }
    /**
     * funkcja rysujaca bomby na grafice
     * @param g grafika na ktorej jest namalowywana obiekty
     */
    public void drawBomb(Graphics g){
        for(int i=0;i<bomb.size();i++){
            g.drawImage(bomb.get(i).getBuffImage(),bomb.get(i).getX(),bomb.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }
    /**
     * funkcja rysujaca itemy na grafice
     * @param g grafika na ktorej jest namalowywana obiekty
     */
    public void drawItem(Graphics g){
        for(int i=0;i<items.size();i++){
            g.drawImage(items.get(i).getBuffImage(),items.get(i).getX(),items.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }
    /**
     * funkcja rysujaca gracza jak i jego wrogow
     * @param g grafika na która jest namalowywana obiekty
     */
    public void drawLivingObject(Graphics g){

        g.drawImage(player.getBuffImage(),player.getX(),player.getY(),SizeWidthIcon,SizeHeightIcon,null);
        for(int i=0;i<enemy.size();i++){
            g.drawImage(enemy.get(i).getBuffImage(),enemy.get(i).getX(),enemy.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }

    /**
     * funkcja rysujaca obiekty terenowe
     * @param g grafika na której są namalowywane obiekty
     */

    public void drawTerrain(Graphics g){
        for(int i=0;i<terrains.size();i++) {
            g.drawImage(terrains.get(i).getBuffImage(),terrains.get(i).getX(),terrains.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }
}
