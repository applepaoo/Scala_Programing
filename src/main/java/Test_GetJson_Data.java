import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class Test_GetJson_Data {

    public static void main(String[] args) throws JSONException, IOException{

        //先抓網頁API的JSON

        URLConnection connection = new URL("http://140.128.197.129:8080/rest/buildingMeter/powerUsage/").openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());

        String PowerData = scanner.useDelimiter("\\A").next();
        System.out.println(PowerData);


        //在測試抓JSON的資料


        JSONArray k;
        JSONObject i = new JSONObject();

        k = new JSONArray(PowerData);


        for (int p=0; p<k.length(); p++){

            i = k.getJSONObject(p);

            System.out.print(i.getString("location") + ",");
            System.out.println(i.getString("KW"));

        }

        System.out.println("電表目前共有"+ k.length() + "隻");

        
      /*  JSONArray k;
        JSONObject i = new JSONObject();

        try {

            k = new JSONArray(PowerData);

            i = k.getJSONObject(0);

            System.out.print(i.getString("location") + ",");
            System.out.println(i.getString("KW"));



        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }*/



    }

}
