import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.parser.Parser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Main {
    public Main() throws IOException, ParseException {
    }

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello world!");
        System.out.println("This is an example!");


        //Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //Spara värden i JSON Object
        //första är nyckelord och andra är värdet
        jsonOb.put("namn", "Maurice");
        jsonOb.put("age", 31);

        //skriva ut värden
        System.out.println("Mitt namn är: " + jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal");


        /*Object o = new JSONParser().parse(new FileReader("C:\\Users\\mkale\\OneDrive\\Skrivbord\\JAVA22\\JWS lektion 2\\JSONexample\\data.json"));
        JSONObject jsonData = (JSONObject) o;
        JSONObject person = (JSONObject) jsonData.get("p1");
        String name = (String) person.get("name");
        String favoriteColor = (String) person.get ("favoriteColor");
         Integer age = (Integer) person.get("age");

        System.out.println("P1 Name is : " + name + "and his favorite color is: " + favoriteColor + "" + age);*/

        //Måste alltid anropa metod i main method för att det ska läsas av
        fetchJsonFromFile();
        fetchJsonFromAPI();
    }

    static void fetchJsonFromFile() throws IOException, ParseException {

        String filepath = "C:\\Users\\mkale\\OneDrive\\Skrivbord\\JAVA22\\JWS lektion 2\\JSONexample\\data.json";

        //Hämta data från JSON fil

        JSONObject fetchData = (JSONObject) new JSONParser().parse(new FileReader(filepath));

        //Konvertera data till ett JSONobject

        JSONObject p1 = (JSONObject) fetchData.get("p1");
        JSONObject p2 = (JSONObject) fetchData.get("p2");

        //Hämta och skriv ut data
        String nameP1= p1.get("name").toString(), nameP2 =p2.get("name").toString();
        int ageP1 = Integer.parseInt(p1.get("age").toString()),ageP2 = Integer.parseInt(p2.get("age").toString());

        System.out.println("Yo my name is " + nameP1 + " I am " +ageP1 + " years old");
        System.out.println("Yo my name is " + nameP2 + " I am " +ageP2 + " years old");

    }

    static void fetchJsonFromAPI() throws IOException, ParseException {

        //Spara URL till API
        URL url = new URL ( "https://api.wheretheiss.at/v1/satellites/25544");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() == 200) System.out.println("Koppling lyckades");
            else System.out.println("Koppling misslyckades");

        // skapa StrBuilder och Scan object
        StringBuilder strData = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        //Bygger upp str med ISS data
        while (scanner.hasNext()) {
            strData.append(scanner.nextLine());
        }
        scanner.close(); // Stänger kopplingen

        System.out.println(strData);


        //Skapar ett JSON object av fetched data
        JSONObject issData = (JSONObject) new JSONParser().parse(String.valueOf(strData));


        System.out.println(issData.get("latitude") + " ya bitch");

    /*    Double latitude = Double.parseDouble(issData.get("latitude").toString());
        Double longitude = Double.parseDouble(issData.get("longitude").toString());
        Double altitude = Double.parseDouble(issData.get("altitude").toString());

        System.out.println("The latitude is: " + latitude);
        System.out.println("The longitude is: " + longitude);
        System.out.println("The altitude is: " + altitude);*/
    }
}