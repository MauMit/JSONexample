import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("This is an example!");


        //Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //Spara värden i JSON Object
        //första är nyckelord och andra är värdet
        jsonOb.put("namn", "Maurice");
        jsonOb.put("age", 31);

        //skriva ut värden
        System.out.println("Mitt namn är: " +jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal");

    }

}