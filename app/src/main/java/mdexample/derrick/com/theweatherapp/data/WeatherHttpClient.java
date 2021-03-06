package mdexample.derrick.com.theweatherapp.data;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mdexample.derrick.com.theweatherapp.util.Utils;

/**
 * Created by Derrick on 2016/10/20.
 */

public class WeatherHttpClient {
    public  String getWeatherData(String place){
        HttpURLConnection connection = null;
        InputStream inputStream =null;

        try {
            connection=(HttpURLConnection)(new URL(Utils.BASE_URL + place +"&appid="+Utils.API_KEY)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //read the response
            StringBuffer stringBuffer=new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line=null;

            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line +"/r/n") ;
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
