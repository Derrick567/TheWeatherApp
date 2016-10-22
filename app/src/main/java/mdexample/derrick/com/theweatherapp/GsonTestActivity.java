package mdexample.derrick.com.theweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import mdexample.derrick.com.theweatherapp.model.WeatherBean;


public class GsonTestActivity extends AppCompatActivity {
    private String data="{\"coord\":{\"lon\":120.68,\"lat\":24.15},\"weather\":" +
            "[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}]" +
            ",\"base\":\"stations\",\"main\":{\"temp\":23.91,\"pressure\":996.19,\"humidity\":98,\"temp_min\":23.91," +
            "\"temp_max\":23.91,\"sea_level\":1021.05,\"grnd_level\":996.19},\"wind\":{\"speed\":2.56,\"deg\":136.509}" +
            ",\"rain\":{\"3h\":6.59},\"clouds\":{\"all\":92},\"dt\":1477052870,\"sys\":{\"message\":0.0036," +
            "\"country\":\"TW\",\"sunrise\":1477000676,\"sunset\":1477041917},\"id\":1668399,\"name\":\"Taichung\",\"cod\":200}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_test);
        Gson gson = new Gson();
        WeatherBean weather= gson.fromJson(data,WeatherBean.class);
        Log.d("name:",weather.getName());
    }
}
