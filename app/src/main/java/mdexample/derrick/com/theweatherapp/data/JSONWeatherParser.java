package mdexample.derrick.com.theweatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mdexample.derrick.com.theweatherapp.model.Place;
import mdexample.derrick.com.theweatherapp.model.Weather;
import mdexample.derrick.com.theweatherapp.util.Utils;

/**
 * Created by Derrick on 2016/10/21.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data){
        Weather weather =new Weather();

        //create JSONObject from data
        try {
            JSONObject jsonObject =new JSONObject(data);
            Place place =new Place();
            JSONObject coordObj = Utils.getObject("coord",jsonObject);
            place.setLat(Utils.getFloat("lat",coordObj));
            place.setLon(Utils.getFloat("lon",coordObj));

            //get sys obj
            JSONObject sysObj = Utils.getObject("sys",jsonObject);
            place.setCountry(Utils.getString("country",sysObj));
            place.setLastupdate(Utils.getInt("dt",jsonObject));
            place.setSunrise(Utils.getInt("sunrise",sysObj));
            place.setSunset(Utils.getInt("sunset",sysObj));
            place.setCity(Utils.getString("name",jsonObject));
            weather.place=place;

            JSONArray jsonArray =jsonObject.getJSONArray("weather");
            JSONObject jsonWeather= jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id",jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description",jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main",jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon",jsonWeather));

            JSONObject mainObj=Utils.getObject("main",jsonObject);
            weather.currentCondition.setHumidity(Utils.getInt("humidity",mainObj));
            weather.currentCondition.setPressure(Utils.getInt("pressure",mainObj));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min",mainObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max",mainObj));
            weather.currentCondition.setTemperture(Utils.getDouble("temp",mainObj));



            JSONObject windObj=Utils.getObject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));
            weather.wind.setDeg(Utils.getFloat("deg",windObj));

            JSONObject cloudObj=Utils.getObject("clouds",jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all",cloudObj));

            return weather;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
