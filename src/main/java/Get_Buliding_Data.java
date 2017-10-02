import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class Get_Buliding_Data {

    public static void main(String[] args) throws IOException{

        //String Data_URL = "http://140.128.197.129:8080/rest/buildingMeter/powerUsage/";

        getHttp();


    }

    private static String getHttp() throws IOException {
        try {
            URL url = new URL(
                    "http://140.128.197.129:8080/rest/buildingMeter/powerUsage/");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    huc.getInputStream(), "UTF-8"));
            String str = "";
            StringBuffer sb = new StringBuffer();
            while (null != ((str = br.readLine()))) {
                sb.append(str);
                System.out.print(sb);
            }
            br.close();
            String xmlResponse = sb.toString();

            // System.out.print(xmlResponse);
            //insertObs(xmlResponse);
            huc.disconnect();
            return xmlResponse;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
