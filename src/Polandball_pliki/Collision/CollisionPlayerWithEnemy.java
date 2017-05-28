package Polandball_pliki.Collision;


import Polandball_pliki.GameObjects.Enemy;
import Polandball_pliki.GameObjects.Polandball;

import static Polandball_pliki.PanelBoard.SizeHeightIcon;
import static Polandball_pliki.PanelBoard.SizeWidthIcon;

/**
 * Created by Matball on 2017-05-24.
 */
public class CollisionPlayerWithEnemy extends Collision {

    /**
     * Max zachodni punkt eksplozji
     */
    int Player_point_west;
    /**
     * Max wschodni punkt eksplozji
     */
    int Player_point_east;
    /**
     * Max polnocny punkt eksplozji
     */
    int Player_point_north;
    /**
     * Max poludniowy punkt eksplozji
     */
    int Player_point_south;

    /**
     * Max zachodni punkt liingObject
     */
    int Enemy_point_west;
    /**
     * Max wschodni punkt liingObject
     */
    int Enemy_point_east;
    /**
     * Max polnocne punkt liingObject
     */
    int Enemy_point_north;
    /**
     * Max poludniowe punkt liingObject
     */
    int Enemy_point_south;

    public  CollisionPlayerWithEnemy(Polandball player, Enemy enemy){
        super();

        //Punkty skrajne player

        //przydzielam zachodni punkt player
        // 1)pobieram polozenie lewego gornego rogu player
        // 2)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego roza player
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia oefektow dzialania eksplozji na brzegach
        Player_point_west=player.getX()-(int)Math.floor((double)SizeWidthIcon  * player.getDistance_from_azimuth_walls() );//przydzielam zachodni punkt livingObject
        //przydzielam wschodni punkt player
        // 1)pobieram polozenie lewego gornego rogu player
        // 2)Dodaje szerokosc jednej komorki
        // 3)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego rodzaju player
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia oefektow dzialania eksplozji na brzegach
        Player_point_east=player.getX()+SizeWidthIcon-(int)Math.floor((double)SizeWidthIcon  * player.getDistance_from_azimuth_walls() );
        //przydzielam polnocny punkt player
        // 1)pobieram polozenie lewego gornego rogu eksplozji
        // 2)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego rodzaju player
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia oefektow dzialania eksplozji na brzegach
        Player_point_north=player.getY()-(int)Math.floor((double)SizeHeightIcon  * player.getDistance_from_elevation_walls() );//przydzielam polnocny punkt skrzynki
        //przydzielam wschodni punkt player
        // 1)pobieram polozenie lewego gornego rogu player
        // 2)Dodaje wysokosc jednej komorki
        // 3)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego rodzaju player
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia efektow dzialania eksplozji na brzegach
        Player_point_south=player.getY()+SizeHeightIcon-(int)Math.floor((double)SizeHeightIcon  * player.getDistance_from_elevation_walls() );//przydzielam poludniowy punkt livingObject


        //Punkty skrajne Enemy

        //przydzielam zachodni punkt enemy
        // 1)pobieram polozenie lewego gornego rogu enemy
        // 2)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego roza enemy
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia oefektow dzialania eksplozji na brzegach
        Enemy_point_west=enemy.getX()+(int)Math.floor((double)SizeWidthIcon  * enemy.getDistance_from_azimuth_walls() );//przydzielam zachodni punkt livingObject
        //przydzielam wschodni punkt enemy
        // 1)pobieram polozenie lewego gornego rogu enemy
        // 2)Dodaje szerokosc jednej komorki
        // 3)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego rodzaju enemy
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia oefektow dzialania eksplozji na brzegach
        Enemy_point_east=enemy.getX()+SizeWidthIcon-(int)Math.floor((double)SizeWidthIcon  * enemy.getDistance_from_azimuth_walls() );
        //przydzielam polnocny punkt enemy
        // 1)pobieram polozenie lewego gornego rogu eksplozji
        // 2)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego rodzaju enemy
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia oefektow dzialania eksplozji na brzegach
        Enemy_point_north=enemy.getY()+(int)Math.floor((double)SizeHeightIcon  * enemy.getDistance_from_elevation_walls() );//przydzielam polnocny punkt skrzynki
        //przydzielam wschodni punkt enemy
        // 1)pobieram polozenie lewego gornego rogu enemy
        // 2)Dodaje wysokosc jednej komorki
        // 3)odejmuje ilosc pikseli odzwierciedlajaca fakt ze kula jest mniejsza niz kwadrat który jest jego obramowka
        // , specyficznie dobrany dla kazdego rodzaju enemy
        // UWAGA ostatni człon może pójść do wymiany, służy do ograniczenia efektow dzialania eksplozji na brzegach
        Enemy_point_south=enemy.getY()+SizeHeightIcon-(int)Math.floor((double)SizeHeightIcon  * enemy.getDistance_from_elevation_walls() );//przydzielam poludniowy punkt livingObject



        //kolizja  player|enemy

        //sprawdzam czy player nachodzi na enemy na osi x
        if( Player_point_east > Enemy_point_west && Player_point_east<Enemy_point_east ) {


            if( ( Player_point_south > Enemy_point_north )  &&
                    Player_point_south < Enemy_point_south )
            {
                isNotCollision = false; // zwraca ze zaszla kolizja z czego wynika że obiekt typu polandball powinien zostac zlikwidowany
            }

        }

        //kolizja z wschodnia czescia enemy    enemy|player


        else if( Enemy_point_east > Player_point_west && Player_point_west>Enemy_point_west ) {
            //sprawdzam czy obiekt miesci sie w polnoc i poludniowym punkcie eksplozji
            if( ( Player_point_south > Enemy_point_north )  &&
                    Player_point_south < Enemy_point_south )
            {
                isNotCollision = false;// zwraca ze zaszla kolizja  z czego wynika że obiekt typu polandball powinien zostac zlikwidowany
            }

        }



        //kolizja z polnocna czescia enemy          player
        //                                               -
        //                                              item

        else if( Player_point_south > Enemy_point_north && Player_point_south<Enemy_point_south ) {


            if(Player_point_east > Enemy_point_west && Player_point_east < Enemy_point_east){
                isNotCollision = false;// zwraca ze zaszla kolizja z czego wynika że obiekt typu polandball powinien zostac zlikwidowany
            }

        }

        //kolizja z poludniowa czescia enemy              enemy
        //                                                 -
        //                                                player
        if( Player_point_north < Enemy_point_south && Player_point_south>Enemy_point_north ){


            if(Player_point_east > Enemy_point_west && Player_point_east < Enemy_point_east){
                isNotCollision = false;// zwraca ze zaszla kolizja z czego wynika że obiekt typu polandball powinien zostac zlikwidowany
            }
        }
        else {
            isNotCollision =true;// zwraca ze nie zaszla kolizja
        }
    }

}
