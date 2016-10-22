package mdexample.derrick.com.theweatherapp;


import android.content.DialogInterface;
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
import mdexample.derrick.com.theweatherapp.data.APIClient;
import mdexample.derrick.com.theweatherapp.data.APIInterface;
import mdexample.derrick.com.theweatherapp.data.CityPreference;
import mdexample.derrick.com.theweatherapp.model.WeatherBean;
import mdexample.derrick.com.theweatherapp.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private static final String TAG="MainActivity";
    WeatherBean weatherBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CityPreference cityPreference=new CityPreference(this);

        renderWeatherData(cityPreference.getCity());

    }

public void renderWeatherData(String city) {
    getDataFromServer(city);

}

    private void setDataToView() {
        DateFormat df= DateFormat.getTimeInstance();
        String sunriseDate =df.format(new Date(weatherBean.getSys().getSunrise()));
        String sunsetDate =df.format(new Date(weatherBean.getSys().getSunset()));
        String updatedDate= df.format(new Date(weatherBean.getDt()));

        DecimalFormat decimalFormat=new DecimalFormat("#.#");
        String tempFormat=decimalFormat.format(weatherBean.getMain().getTemp());

        cityName.setText(weatherBean.getName()+","+weatherBean.getSys().getCountry());
        temp.setText(""+tempFormat+"℃ ");
        humidity.setText("Humidity: " +weatherBean.getMain().getHumidity()+"%" );
        pressure.setText("Pressure: " + weatherBean.getMain().getPressure()+"hPa");
        wind.setText("Wind: "+weatherBean.getWind().getSpeed()+"mps");
        sunrise.setText("Sunrise: "+sunriseDate);
        sunset.setText("Sunset: "+sunsetDate);
        updated.setText("Last Updated: " + updatedDate);
        description.setText("Condition: "+weatherBean.getWeather().get(0).getMain() +"("+
                weatherBean.getWeather().get(0).getDescription()+")");
        Log.d("Icon",weatherBean.getWeather().get(0).getIcon());
        Picasso.with(MainActivity.this).load(
                Utils.ICON_URL+
                weatherBean.getWeather().get(0).getIcon() +".png")
                .into(iconView);
    }

    private void getDataFromServer(String city){
        APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<WeatherBean> call = apiService.getWeather(city, "metric", Utils.API_KEY);
        call.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                weatherBean = response.body();
                setDataToView();
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
//
//}
//
//
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
