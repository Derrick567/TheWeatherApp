package mdexample.derrick.com.theweatherapp.data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Derrick on 2016/10/21.
 */

public class CityPreference {
    SharedPreferences prefs;
    public CityPreference(Activity activity){
        prefs=activity.getPreferences(Activity.MODE_PRIVATE);
    }
    public String getCity(){return prefs.getString("city","Taipei,TW");}

    public void setCity(String city){
         prefs.edit().putString("city",city).commit();
    }
}
