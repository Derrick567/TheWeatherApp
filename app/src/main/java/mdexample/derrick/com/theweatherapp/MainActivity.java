package mdexample.derrick.com.theweatherapp;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import mdexample.derrick.com.theweatherapp.data.CityPreference;
import mdexample.derrick.com.theweatherapp.data.JSONWeatherParser;
import mdexample.derrick.com.theweatherapp.data.WeatherHttpClient;
import mdexample.derrick.com.theweatherapp.model.Weather;
import mdexample.derrick.com.theweatherapp.util.Utils;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.cityText)
    TextView cityName;
    @BindView(R.id.thumbnailIcon)
    ImageView iconView;
    @BindView(R.id.tempText)
    TextView temp;
    @BindView(R.id.windText)
    TextView wind;
    @BindView(R.id.cloudText)
    TextView description;
    @BindView(R.id.pressureText)
    TextView pressure;
    @BindView(R.id.humidText)
    TextView humidity;
    @BindView(R.id.riseText)
    TextView sunrise;
    @BindView(R.id.setText)
    TextView sunset;
    @BindView(R.id.updateText)
    TextView updated;

Weather weather ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CityPreference cityPreference=new CityPreference(this);

        renderWeatherData(cityPreference.getCity());

    }

public void renderWeatherData(String city){
    WeatherTask task=new WeatherTask();
    task.execute(new String[]{city+"&units=metric"});


}


private void showInputDialog(){
    AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
    builder.setTitle("Change City");
    final EditText cityInput = new EditText(MainActivity.this);
    cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
    cityInput.setHint("Taipei,TW");
    builder.setView(cityInput);
    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            CityPreference cityPreference = new CityPreference(MainActivity.this);
            cityPreference.setCity(cityInput.getText().toString());
            String newCity=cityPreference.getCity();

            renderWeatherData(newCity);
        }
    });
    builder.show();
}

private class WeatherTask extends AsyncTask<String,Void,Weather>{
    @Override
    protected Weather doInBackground(String... params) {
        String data =((new WeatherHttpClient()).getWeatherData(params[0]) );
        Log.d("Data :" ,data);
        weather = JSONWeatherParser.getWeather(data);
        Log.d("Data :" ,weather.place.getCity());
        weather.iconData=weather.currentCondition.getIcon();
        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);

        DateFormat df= DateFormat.getTimeInstance();
        String sunriseDate =df.format(new Date(weather.place.getSunrise()));
        String sunsetDate =df.format(new Date(weather.place.getSunset()));
        String updatedDate= df.format(new Date(weather.place.getLastupdate()));

        DecimalFormat decimalFormat=new DecimalFormat("#.#");
        String tempFormat=decimalFormat.format(weather.currentCondition.getTemperture());

        cityName.setText(weather.place.getCity()+","+weather.place.getCountry());
        temp.setText(""+tempFormat+"℃ ");
        humidity.setText("Humidity: " +weather.currentCondition.getHumidity()+"%" );
        pressure.setText("Pressure: " + weather.currentCondition.getPressure()+"hPa");
        wind.setText("Wind: "+weather.wind.getSpeed()+"mps");
        sunrise.setText("Sunrise: "+sunriseDate);
        sunset.setText("Sunset: "+sunsetDate);
        updated.setText("Last Updated: " + updatedDate);
        description.setText("Condition: "+weather.currentCondition.getCondition()+"("+
                weather.currentCondition.getDescription()+")");
        Log.d("Icon",weather.iconData);
        Picasso.with(MainActivity.this).load(Utils.ICON_URL+ weather.iconData +".png").into(iconView);

    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.change_cityId){
            showInputDialog();
        }
        return super.onOptionsItemSelected(item);

    }
}
