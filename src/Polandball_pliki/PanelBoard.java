package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Polandball_pliki.Collision.CollisionLivingObjectWithExplosion;
import Polandball_pliki.Collision.CollisionLivingObjectWithTerrain;
import Polandball_pliki.Collision.CollisionExplosionWithSkrzynki;
import Polandball_pliki.Collision.CollisionPlayerWithEnemy;
import Polandball_pliki.Counter.Counter;
import Polandball_pliki.Counter.CounterPlayer;
import Polandball_pliki.Counter.CounterExplosion;
import Polandball_pliki.Counter.CounterNormalBomb;
import Polandball_pliki.GameObjects.*;

import static Polandball_pliki.GetConstans.*;


public class PanelBoard extends JPanel implements ActionListener,KeyListener{

    /**
     * Tablica obiektow przeciwnikow
     */
    private ArrayList<Enemy> enemy=new ArrayList<>();

    /**
     * Tablica obiektow typu gracz
     */
    private ArrayList<Polandball> player=new ArrayList<>();
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
    public ArrayList<Terrain> terrains=new ArrayList<>();

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
     * Zmienna mówiaca czy player zyje
     */
    public static boolean PlayerExistence=false;

    /**
     * konstruktor panelu głównego, zawierający funkcję PanelBoard
     */

    public PanelBoard(){PanelBoard();}

    /**
     * zmienna odliczajaca czas, wymagana przy interfejsie ActionListener i KeyListener
     */
    Timer tm=new Timer(30,this);
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
                        terrains.add(new Beton(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie betonu w pole j,i
                    } else if (field.get(i).get(j).equals("S_")) {
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie skrzynki w pole j,i
                    } else if (field.get(i).get(j).equals("NG")) {
                        player.add(new Polandball(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie olayer w polu j,i
                        PlayerExistence=true;
                    } else if (field.get(i).get(j).equals("NW")) {
                        enemy.add(lottery_of_enemies(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie wroga w  pole j,i
                    } else if (field.get(i).get(j).equals("SD")) {
                        items.add(new Door(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie itemu-Drzwi w polu j,i
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    } else if (field.get(i).get(j).equals("SK")) {
                        items.add(new Key(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie itemu-Klucz w polu j,i
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    } else if (field.get(i).get(j).equals("SI")) {
                        //kiedy ogarne itemy trzeba wrzucic funkcje do losowania itemu w tym miejscu
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    }
                    //do zrobienia jeszcze if z itemami typu skrzydla husarskie czy laser sprawiedliwosci
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

        if(fate==0){//kiedy wylosuje zero losuje turkey balla
            enemy_=new Turkeyball(position_enemyX,position_enemyY);
        }else if(fate==1){//kiedy wylosuje jeden losuje naziballa
            enemy_=new Naziball(position_enemyX,position_enemyY);
        }else if(fate==2){//kiedy wylosuje dwa losuje sovietballa
            enemy_=new Sovietball(position_enemyX,position_enemyY);
        }else {
            enemy_=null;//zwraca null w innych przypadkach, jakis blad w losowaniu
        }
        return enemy_;//zwracam wylosowanego wroga

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
        checkRespawnPlayer(); // sprawdzanie czy w razie gdyby player zginal nie nastapil czas jego respawnu
        checkCounter();//funkcja sprawdzajaca stan licznik w grze
        repaint();//odmalowywanie gracza po kazdym wykrytym zdarzeniu
    }

    /**
     * metoda do wywołania zmiany polozenia polandballa
     */

    void movePlayer() {
        if(player.size()>0) {

            //zmienna przechowujaca informacje czy player nie zachacza o instancje jakiegos terenu
            boolean I_can_go = new CollisionLivingObjectWithTerrain(player.get(0), "player").getIsNotCollision();
            boolean Enemy_didnt_caught_player= checkPotentialCollisionWithEnemy();
            if (I_can_go && Enemy_didnt_caught_player) {//jesli jest I_can_go oraz Enemy_didnt_caught_player jest true to player moze sie poruszyc
                player.get(0).changeX(player.get(0).getX() + player.get(0).get_velX());
                player.get(0).changeY(player.get(0).getY() + player.get(0).get_velY());
            }
        }
    }

    /**
     * Sprawdza potencjalne kolizje Gracza z wrogami i zwraca odpowiednia wartosc true/false.
     */
    boolean checkPotentialCollisionWithEnemy(){
        boolean isNotCollision=true;
        for(Iterator<Enemy> iteratoEnemy = enemy.iterator(); iteratoEnemy.hasNext();){
            Enemy enemy1=iteratoEnemy.next();
            isNotCollision=new CollisionPlayerWithEnemy(player.get(0),enemy1).getIsNotCollision();
            if(isNotCollision==false){
                PlayerExistence=false;
                counters.add(new CounterPlayer(player.get(0)));
                player.remove(0); //usuwa playera z mapy
                return isNotCollision;
            }
        }
        return isNotCollision;

    }

    /**
     * Metoda tworzaca watki dla wrogow
     */

    void createThreadsForEnemy(){
        try {
            for (int i = 0; i < enemy.size(); i++) {
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
        //sprawdzam wszystkie bomby po kolei
        for(int i=0;i<normal_bomb.size();i++) {
            if (normal_bomb.get(i).getExplosionflag() == true){//wywoluje sie gdy flaga bomby informujaca ze ma teraz eksplozje jest true
                createExplosion(normal_bomb.get(i)); // tworze eksplozje na ekranie
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
        checkTerrainToDestroyByExplosion();//wywoluje funkcje sprawdzajaca czy w wyniku ekplozji nie powinno sie zniszczyc jakiejs skrzynki
        counters.add(new CounterExplosion(explosions.get(explosions.size()-1) ) );//dodaje licznik liczacy czas trwania eksplozji
    }

    /**
     * Metoda Sprawdza czy eksplozja nie zniszczyla jakichs skrzynek, jesli tak to je niszczy
     */
    public void checkTerrainToDestroyByExplosion(){

        boolean notDestroy=true;//zmienna przechowujaca informacje czy aby napewno dochodzi do jakiegoś zniszczenia obiektu

        for(Iterator<Terrain> iteratoTerrain =terrains.iterator();iteratoTerrain.hasNext();) {// dokonuje iteracji po calym zbiorze terrain za pomoca iteratorow, 1) tworze iterator 2) ustawiam go na pierwszy element tej kolejkcji
            Terrain ter = iteratoTerrain.next();//tworze instancje obiektu w petli na podstawie obiektu na ktory wskazuje iterator
            if (ter.getNameClassObject().equals(SkrzynkaString)) {//jesli element skrzynka zwroci parametr name_class_object taki sam jak SkrzynkaString, to wykona sie if
                notDestroy = new CollisionExplosionWithSkrzynki( ter, explosions.get(explosions.size() - 1) ).getIsNotCollision();//sprawdzam czy ta konkretne skrzynke moze zniszczyc eksplozja
                if (!notDestroy) {//jesli eksplozja pokazuje ze mamy zniszczyc obiekt wykonuje sie if
                    StatioonaryObjectTab[ter.getRowY()][ter.getColumnX()]=0;//zmieniam wartosc w tablicy stworzonej w GetConstan apropo pol w ktore mogą wejsc living object
                    iteratoTerrain.remove();//usun obiekt wskazywany przez iterator ( a wlasciwie to pozbadz sie wszelkich wskaznikow na niego wskazujacych )
                }
            }
        }

    }

    /**
     * Metoda sprawdzajaca status Eksplozji ( czy sie juz skonczyla czy nie , jesli tak to usuwa obiekt eksplozja
     */
    private void checkExplosionStatus(){
        for (int i=0;i<explosions.size();i++){//iteruje po co calej kolekcji eksplozje
            if(explosions.get(i).get_end_of_explosion()==true){//jesli eksplozja ma flage oznaczajaca koniec jej istnienie to odpala sie if z usunieciem eksplozji z ekranu
                explosions.remove(i);
            }
        }
        checkPlayerToDestroy();//sprawdza czy player nie zginie w wyniku wybuchu eksplozji
        checkEnemyToDestroy();//sprawdza czy ktorys z potwoorow nie zginie w wyniku eksplozji
    }

    /**
     * Metoda sprawdza czy obiekt typu player nie zostanie zniszczonyw  wyniku eksplozjii
     */

    private void checkPlayerToDestroy(){
        if(player.size()>0) {
            boolean notDestroy = true;// flaga jednoznacznie stwierdzajaca czy obiekt typu player powinnien zostac usuniety
            for (Iterator<Explosion> iteratoExplosion = explosions.iterator(); iteratoExplosion.hasNext(); ) {//iteruje po kolekcji explosion 1) tworze iterator dla kolekcji ekplozjii 2) przypisuje go do do pierwszego elementu kolekcji
                Explosion explInstance = iteratoExplosion.next();//tworze instancje obiektu typu eksplozja na podstawie obiektu na ktory wskazuje w danej chwili iterator
                notDestroy = new CollisionLivingObjectWithExplosion(player.get(0), explInstance).getIsNotCollision();//sprawdzam czy player napewno nie ucierpi w wyniku eksplozji
                if (!notDestroy) {//jesli w wyniku obliczen wyszlo ze gracz ucierpi w wyniku eksplozji
                    PlayerExistence=false;
                    counters.add(new CounterPlayer(player.get(0)));
                    player.remove(0); //usuwa playera z mapy
                }
            }
        }
    }

    /**
     * Metoda sprawdzająca czy obiekt typu Enemy nie zostanie zniszczony w wyniku eksplozji
     */

    private void checkEnemyToDestroy(){
        boolean notDestroy=true;// flaga jednoznacznie stwierdzajaca czy obiekt typu player powinnien zostac usuniety

        for(Iterator<Explosion> iteratoExplosion = explosions.iterator();iteratoExplosion.hasNext();) {//iteruje po kolekcji explosion 1) tworze iterator dla kolekcji ekplozjii 2) przypisuje go do do pierwszego elementu kolekcji
            Explosion explInstance=iteratoExplosion.next();//tworze instancje obiektu typu eksplozja na podstawie obiektu na ktory wskazuje w danej chwili iterator

            for (Iterator<Enemy> iteratoEnemy = enemy.iterator(); iteratoEnemy.hasNext(); ) {//iteruje po kolekcji enemy 1) tworze iterator dla kolekcji enemy 2) przypisuje go do do pierwszego elementu kolekcji enemy
                Enemy enemInstance = iteratoEnemy.next();//tworze instancje obiektu typu enemy na podstawie obiektu na ktory wskazuje w danej chwili iterator
                    notDestroy = new CollisionLivingObjectWithExplosion(enemInstance, explInstance).getIsNotCollision();//sprawdzam czy enemy napewno nie ucierpi w wyniku eksplozji
                    if (!notDestroy) {//jesli w wyniku obliczen wyszlo ze gracz ucierpi w wyniku eksplozji
                        iteratoEnemy.remove();//usuwa enemy wskazywany przez iterator
                    }

            }
        }
    }

    /**
     * Metoda sprawdza czy istnieje w tej chwili na planszy player, jesli nie (a powinnien bo ma np. trzy życia i jedno przed chwilą stracił w wyniku wybuchu) ustawia playera w polu startowy
     */
    public void checkRespawnPlayer(){

        if(PlayerExistence==true && player.size()==0) {
            setRespawnPolandball();//wywolanie metody ustawiajacej gracza w polu startowym
        }
    }

    /**
     * Ustawienie Polandballa w miejscu startowym po respawnie
     */
    public void setRespawnPolandball() {
        for (int i = 0; i < Amountoflines; i++) {
            for (int j = 0; j < Amountofcolumns; j++) {
                if (field.get(i).get(j).equals("NG")) {
                    player.add(new Polandball(SizeWidthIcon * (j), SizeHeightIcon * (i)));//dodanie player w polu j,i
                }
            }
        }
    }

    /**
     * metoda sprawdzajaca czy ktorys z licznikow nie wykryl konca odliczania i jesli tak to go usunie z tablicy
     */
    void checkCounter(){
        for(int i=0;i<counters.size();i++){//iteruje po kolekcji liczniki
            counters.get(i).checkTime();//sprawdzam czy aby i-ty element kolekcji liczniki nie powinien juz zakonczyc swojego dzialania
            if(counters.get(i).getisStillNeed()==false){//sprawdzam czy licznik w ogole jeszcze jest potrzebny
                counters.remove(i);//usuwam niepotrzebny licznik
            }
        }
    }
    /**
     * metoda reagujaca na nacisniecie klawisza
     * @param e wydarzenie przechowujace informacje na temat wcisnietego klawisza
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(player.size()>0) {
            int c = e.getKeyCode();

            if (c == KeyEvent.VK_LEFT) {
                player.get(0).change_velX(SpeedPlayer * (-1));
                player.get(0).change_velY(0);
            } else if (c == KeyEvent.VK_RIGHT) {
                player.get(0).change_velX(SpeedPlayer * (1));
                player.get(0).change_velY(0);
            } else if (c == KeyEvent.VK_DOWN) {
                player.get(0).change_velY(SpeedPlayer * (1));
                player.get(0).change_velX(0);
            } else if (c == KeyEvent.VK_UP) {
                player.get(0).change_velY(SpeedPlayer * (-1));
                player.get(0).change_velX(0);
            }
            //stawianie bomby
            else if (c == KeyEvent.VK_SPACE) {
                normal_bomb.add(new Normal_Bomb(player.get(0).getX(), player.get(0).getY()));//dodanie dodani bomby do tablicy
                counters.add(new CounterNormalBomb(normal_bomb.get(normal_bomb.size() - 1)));//ustawienie licznika dla danego obiekty typu bomba
                // znajdujacego sie w tablicy
            }
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
        if(player.size()>0) {
            player.get(0).change_velY(0);
            player.get(0).change_velX(0);
        }
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
        //System.out.println("Size of players "+ player.size());
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
        //System.out.println("Size of players "+ player.size());
        if(player.size()>0) {
            g.drawImage(player.get(0).getBuffImage(), player.get(0).getX(), player.get(0).getY(), SizeWidthIcon, SizeHeightIcon, null);
        }
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
