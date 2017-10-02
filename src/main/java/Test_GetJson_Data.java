import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class Test_GetJson_Data {

    public static void main(String[] args) throws JSONException, IOException{

        URLConnection connection = new URL("http://140.128.197.129:8080/rest/buildingMeter/powerUsage/").openConnection();
        try(Scanner scanner = new Scanner(connection.getInputStream());){
            String PowerData = scanner.useDelimiter("\\A").next();
            System.out.println(PowerData);
        }

        JSONObject j;
        try {
            String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

            j = new JSONObject(tmp);

            Object jsonOb = j.getJSONObject("Data").get("Name");

            System.out.println(jsonOb);

        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }


    }

}
