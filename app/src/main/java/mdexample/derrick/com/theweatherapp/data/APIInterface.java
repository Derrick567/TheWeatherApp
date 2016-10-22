package mdexample.derrick.com.theweatherapp.data;

import mdexample.derrick.com.theweatherapp.model.WeatherBean;
import mdexample.derrick.com.theweatherapp.util.Utils;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Derrick on 2016/10/22.
 */

public interface APIInterface {
    // //http://api.openweathermap.org/data/2.5/weather?q=Taichung,TW&units=metric&appid=8d363f3f6527595edd1909e68d287c5b
    //public  static final String BASE_URL="http://api.openweathermap.org/";
    @GET("/data/2.5/weather/")
    Call<WeatherBean> getWeather(
            @Query("q") String place,
            @Query("units") String units
            ,@Query("appid") String apiKey);

}
