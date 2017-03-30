package Polandball_pliki;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;



/**
 * Parametry pierwszego poziomu
 **/


public final class GetConstans {

	/**
	 * Sciezka do pliku konfiguracyjnego
	 **/
	public static final String Config = "src\\Polandball_pliki\\config.xml"; //poprawiłem ścieszke

	/**
	 * Wysokosc ramki
	 **/

	public static int Boardheight;

	/**
	 * Szerokosc ramki
	 **/

	public static int Boardwidth;

	/**
	 * szybkosc potworow
	 **/

	public static int Monsterspeed;

	/**
	 * Ilosc zyc na poczatku rozgrywki
	 **/

	public static int Amountoflifes;
	/**
	 * Ilosc kolumn
	 **/

	public static int Amountofcolumns;

	/**
	 *Ilosc wierszy
	 **/
	public static int Amountoflines;


	/**
	 * pomocnicza zmienna, okreslajaca wysokosc panelboard
	 */

	public static int panelboardheight;

	/**
	 * pomocnicza zmienna, okeslajaca szerokosc panelboard
	 */

	public static int panelboardwidth;


	/**
	 * Rozklad elementow planszy
	 **/

	/**
	 * pomocnicza zmienna, okreslajaca wysokosc gornego panelu
	 */

	public static int panelinfooneheight;

	/**
	 * pomocnicza zmienna, okreslajaca wysokosc bocznego panelu
	 */

	public static int panelinfotwoheight;

	/**
	 * pomocnicza zmienna, okreslajaca szerokosc gornego panelu
	 */

	public static int panelinfotwowidth;


	public static String row;
	/**
	 * Pola z ścieszkami do grafiki
	 */
	public static String PolandBall;
	public static String TurkeyBall;
	public static String NaziBall;
	public static String SovietBall;
	public static String Skrzynka;
	public static String Beton;
	public static String Nothing;


	/**
	 * Wczytywanie pol z pliku konfiguracyjnego
	 **/

	public GetConstans(){ // to co wcześniej było w funkcji parser config wrzuciłem do konstruktora bo powodowało błędy syntaktyczne
		try{
			File file = new File(Config);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			Boardheight=Integer.parseInt(doc.getElementsByTagName("Boardheight").item(0).getTextContent());
			Boardwidth=Integer.parseInt(doc.getElementsByTagName("Boardwidth").item(0).getTextContent());
			Monsterspeed=Integer.parseInt(doc.getElementsByTagName("Monsterspeed").item(0).getTextContent());
			Amountoflifes=Integer.parseInt(doc.getElementsByTagName("Amountoflifes").item(0).getTextContent());
			Amountofcolumns=Integer.parseInt(doc.getElementsByTagName("Amountofcolumns").item(0).getTextContent());
			Amountoflines=Integer.parseInt(doc.getElementsByTagName("Amountoflines").item(0).getTextContent());
			row=doc.getElementsByTagName("row").item(0).getTextContent();
			PolandBall=doc.getElementsByTagName("PolandBall").item(0).getTextContent();
			TurkeyBall=doc.getElementsByTagName("TurkeyBall").item(0).getTextContent();
			SovietBall=doc.getElementsByTagName("SovietBall").item(0).getTextContent();
			NaziBall=doc.getElementsByTagName("NaziBall").item(0).getTextContent();
			Skrzynka=doc.getElementsByTagName("Skrzynka").item(0).getTextContent();
			Beton=doc.getElementsByTagName("Beton").item(0).getTextContent();
			Nothing=doc.getElementsByTagName("Nothing").item(0).getTextContent();

			//pomocnicze zmienne, potrzebne to ustalenia proporocji paneli - proporcje sa niezmienne, mozna zmieniac wymiary calej ramki
			panelboardheight =(int)(0.8*Boardheight);
			panelboardwidth =(int)(0.8*Boardwidth);
			panelinfooneheight = (int)(0.2*Boardheight);
			panelinfotwoheight =(int)(0.8*Boardheight);
			panelinfotwowidth = (int)(0.2*Boardwidth);

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



}
