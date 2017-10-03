import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;


public class Test_GetJson_Data {

    public static void main(String[] args) throws JSONException, IOException{

        //先抓網頁API的JSON

        URLConnection connection = new URL("http://140.128.197.129:8080/rest/buildingMeter/powerUsage/").openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());

        String PowerData = scanner.useDelimiter("\\A").next();
        System.out.println(PowerData);


        //在測試抓JSON的資料


        JSONArray k;
        JSONObject i;

        k = new JSONArray(PowerData);


        for (int p=0; p<k.length(); p++){

            i = k.getJSONObject(p);

            //轉13
            long unixSeconds = Long.parseLong(k.getJSONObject(p).getString("time_stamp"));
            Date date = new Date(unixSeconds); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT+8")); // give a timezone reference for formating (see comment at the bottom
            String formattedDate = sdf.format(date);


            System.out.print(i.getString("location") + ",");
            System.out.println(formattedDate + "," + i.getString("KW"));
            //System.out.println(i.getString("KW"));

        }

        System.out.println("電表目前共有"+ k.length() + "隻");




    }

}
