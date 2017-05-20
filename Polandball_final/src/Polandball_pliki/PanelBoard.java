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

import Polandball_pliki.Counter.Counter;
import Polandball_pliki.Counter.Counter_Explosion;
import Polandball_pliki.Counter.Counter_Normal_Bomb;
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
     * Tablica obiektow zwykle bomby
     */
    private ArrayList<Normal_Bomb> normal_bomb=new ArrayList<>();

    /**
     *  Tablica obiektow eksplozji
     */

    private ArrayList<Explosion> explosions =new ArrayList<>();

    /**
     * Tablica licznikow jakie sa w tej chwili w bazie danych
     */
    private ArrayList<Counter> counters=new ArrayList<>();
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
    Timer tm=new Timer(15,this);
    /**
     * konstruktor zawirający parametry planszy, okreslenie liczby i polozenie GameObject jakie sie znajdą w grze
     */

    private void PanelBoard() {
        panelboardheight =(int)(0.75*Boardheight);
        panelboardwidth =(int)(0.8*Boardwidth);
        panelinfooneheight = (int)(0.2*Boardheight);
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


            //wypelnienie dynamicznej tablicy elementami, w zaleznosci od wczytanej konfiguracji
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
            //tworzenie watkow wrogow
            createThreadsForEnemy();
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
        movePlayer();//meteda odpowiedzialna za poruszenie gracza
        moveEnemy();//metoda odpowiedzialna za poruszanie wrogow

        checkNormalBombStatus();//sprawdzanie na bieżąco czy bomba nie ma juz wybuchnąć
        checkExplosionStatus();//sprawdzanie czy przypadkiem eksplozja nie powinna sie juz skonczyc
        //removeBombingTerrain();// jeszcze nic nie robi, ma usuwac zniszczone obikety terenu
        checkCounter();//funkcja sprawdzajaca stan licznik w grze
        repaint();//odmalowywanie gracza po kazdym wykrytym zdarzeniu
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
     * Metoda tworzaca watki dla wrogow
     */

    void createThreadsForEnemy(){
        try {
            for (int i = 0; i < enemy.size(); i++) {
                //System.out.println(0);
                new Thread(enemy.get(i).getEnemy()).start();//(new Enemy(enemy.get(i).getEnemy())).start();
            }

        } catch(Exception e){
            System.out.println("Blad w watku");
            System.out.println(e);
        }
    }
    /**
     * Metoda poruszajaca wrogow
     */

    void moveEnemy(){
        for(int i=0;i<enemy.size();i++){
        //dla kazdego worga kierunek ruchu jest wyliczany w niezaleznym watku
        int kierunek = enemy.get(i).getEnemydirection();
            //zmiana predkosci (poruszania sie) wroga, w zaleznosci od kierunku
            switch(kierunek) {
                case 0:
                enemy.get(i).change_velX(1);
                enemy.get(i).change_velY(0);
                enemy.get(i).changeX(enemy.get(i).getX() + enemy.get(i).get_velX());
                enemy.get(i).changeY(enemy.get(i).getY() + enemy.get(i).get_velY());
                break;
                case 1:
                enemy.get(i).change_velX(-1);
                enemy.get(i).change_velY(0);
                enemy.get(i).changeX(enemy.get(i).getX() + enemy.get(i).get_velX());
                enemy.get(i).changeY(enemy.get(i).getY() + enemy.get(i).get_velY());
                break;
                case 2:
                enemy.get(i).change_velY(-1);
                enemy.get(i).change_velX(0);
                enemy.get(i).changeX(enemy.get(i).getX() + enemy.get(i).get_velX());
                enemy.get(i).changeY(enemy.get(i).getY() + enemy.get(i).get_velY());
                break;
                case 3:
                enemy.get(i).change_velY(1);
                enemy.get(i).change_velX(0);
                enemy.get(i).changeX(enemy.get(i).getX() + enemy.get(i).get_velX());
                enemy.get(i).changeY(enemy.get(i).getY() + enemy.get(i).get_velY());
                break;
            }
        }
    }

    /**
     * Metoda sprawdzajaca, czy bomba juz wybuchla
     */

    private void checkNormalBombStatus(){
        for(int i=0;i<normal_bomb.size();i++) {
            if (normal_bomb.get(i).getExplosionflag() == true){
                createExplosion(normal_bomb.get(i));
                normal_bomb.remove(i);//usuniecie danej bomby z tablicy bomb, jesli wybuchla(czas sie skonczyl)
            }
        }
    }

    /**
     * Metoda tworzaca eksplozja w okreslonym obszarze
     *
     * @param bomb  bomba ktora wybucha i zostawia eksplozje
     */
    private void createExplosion(Bomb bomb){

        explosions.add(new Explosion(bomb.getX()-SizeWidthIcon,bomb.getY()-SizeHeightIcon));//dodaje do tablicy obiekt typu eksplozja
        // UWAGAbedzie wymiarow 3 na 3 wiec przesuwam go o jedna kolumne na zachod i o wiersz na polnoc


        counters.add(new Counter_Explosion(explosions.get(explosions.size()-1  ) ) );//dodaje licznik liczacy czas trwania eksplozji
    }

    /**
     * Metoda sprawdzajaca status Eksplozji
     */
    private void checkExplosionStatus(){
        for (int i=0;i<explosions.size();i++){
            if(explosions.get(i).get_end_of_explosion()==true){
                explosions.remove(i);
            }
        }
    }

    /**
     * metoda sprawdzajaca czy ktorys z licznikow nie wykryl konca odliczania i jesli tak to go usunie z tablicy
     */
    void checkCounter(){
        for(int i=0;i<counters.size();i++){
            counters.get(i).checkTime();
            if(counters.get(i).getisStillNeed()==false){
                counters.remove(i);
            }
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
        else if (c == KeyEvent.VK_SPACE) {
            normal_bomb.add(new Normal_Bomb(player.getX(), player.getY()));//dodanie dodani bomby do tablicy
            counters.add(new Counter_Normal_Bomb(normal_bomb.get(normal_bomb.size() - 1) ) );//ustawienie licznika dla danego obiekty typu bomba
                                                                     //znajdujacego sie w tablicy
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

    public static int SizeWidthIcon = ((int)(0.8*Boardwidth))/Amountofcolumns;

    /**
     * wysokosc obiektu graficznego, zalezna od wysokosci panela i ilosci wierszy
     */

    public static int SizeHeightIcon = ((int)(0.75*Boardheight))/Amountoflines;

    /**
     * funkcja rysująca mape poziomu
     * @param g grafika na której "malujemy" elementy
     */

    public void paint(Graphics g){

        g.setColor(Color.black);
        g.fillRect(0,0,(int)(0.8*Boardwidth),(int)(0.75*Boardheight)); // rysuje czarny kwadrat bedacy tlem dla naszych grafik
        drawBomb(g); //rysuje bomby na planszy
        drawItem(g); //rysuje itemy na planszy
        drawPlayerObject(g);//rysuje playera
        drawEnemyObject(g);//rysuje wrogow
        drawTerrain(g);//rysuje elementy terenu - skrzynki, beton itd
        drawExplosion(g);//rysuje eksplozje
    }
    /**
     * funkcja rysujaca bomby na grafice
     * @param g grafika na ktorej jest namalowywana obiekty
     */
    public void drawBomb(Graphics g){
        //rysowanie wszystkich bomb na ekran
        for(int i=0;i<normal_bomb.size();i++){
                 g.drawImage(normal_bomb.get(i).getGIF(),normal_bomb.get(i).getX(),normal_bomb.get(i).getY(),SizeWidthIcon,SizeHeightIcon,this);
        }
    }

    /**
     * funkcja rysujaca eksplozji na grafice
     * @param g grafika na którje jest namalowywane obiekty
     */
    public void drawExplosion(Graphics g) {
        //rysowanie wszystkich eksplozji na ekran
        for (int i = 0; i < explosions.size(); i++) {
            {
                g.drawImage(explosions.get(i).getGIF(), explosions.get(i).getX(), explosions.get(i).getY(), 3*SizeWidthIcon, 3*SizeHeightIcon, this);
            }
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
     * funkcja rysujaca gracza
     * @param g grafika na która jest namalowywana obiekty
     */
    public void drawPlayerObject(Graphics g) {
        g.drawImage(player.getBuffImage(), player.getX(), player.getY(), SizeWidthIcon, SizeHeightIcon, null);
    }
    /**
     * funkcja rysujaca wrogow
     * @param g grafika na która jest namalowywana obiekty
     */

    public void drawEnemyObject(Graphics g){
        for(int i=0;i<enemy.size();i++){
                g.drawImage(enemy.get(i).getBuffImage(), enemy.get(i).getX(), enemy.get(i).getY(), SizeWidthIcon, SizeHeightIcon, null);

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
