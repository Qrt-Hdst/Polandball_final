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
		* Scie�ka do pliku konfiguracyjnego
	**/
	public static final String Config = "src\\Polandball_pliki\\config.xml"; //poprawiłem ścieszke
	
	/**
		* Wysoko�� planszy
	**/
	
	public static int Boardheight;
	
	/**
		* Szeroko�� planszy
	**/
	
	public static int Boardwidth;
	
	/**
		* szybko�� potwor�w
	**/
	
	public static int Monsterspeed;
	
	/**
		* Ilo�� �y� na pocz�tku rozgrywki
	**/
	
	public static int Amountoflifes;
	
	/**
		* Rozk�ad element�w planszy
	**/
	
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
		* Wczytywanie p�l z pliku konfiguracyjnego
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
			//row=doc.getElementsByTagName("row").item(0).getTextContent();
			PolandBall=doc.getElementsByTagName("PolandBall").item(0).getTextContent();
			TurkeyBall=doc.getElementsByTagName("TurkeyBall").item(0).getTextContent();
			SovietBall=doc.getElementsByTagName("SovietBall").item(0).getTextContent();
			NaziBall=doc.getElementsByTagName("NaziBall").item(0).getTextContent();
			Skrzynka=doc.getElementsByTagName("Skrzynka").item(0).getTextContent();
			Beton=doc.getElementsByTagName("Beton").item(0).getTextContent();
			Nothing=doc.getElementsByTagName("Nothing").item(0).getTextContent();

		}
		catch(FileNotFoundException e){
            e.printStackTrace();
			}
        catch(Exception e){
            e.printStackTrace();
			}
	}

	
	
}
