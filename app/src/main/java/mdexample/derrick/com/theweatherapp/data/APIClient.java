package mdexample.derrick.com.theweatherapp.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mdexample.derrick.com.theweatherapp.util.Utils.BASE_URL;

/**
 * Created by Derrick on 2016/10/22.
 */

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
