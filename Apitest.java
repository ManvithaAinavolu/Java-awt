import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Apitest{
    public static void main(String args[]){
        String apiurl="https://api.rootnet.in/covid19-in/stats/latest";
        try{
            URL url=new URL(apiurl);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int Responsecode = connection.getResponseCode();
            System.out.println("responsecode:"+Responsecode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

        }
        in.close();
                    System.out.println("Response Body:");
            System.out.println(response.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}