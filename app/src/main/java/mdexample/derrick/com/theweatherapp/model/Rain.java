package mdexample.derrick.com.theweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("3h")
    private double volume;

    public void setVolume(double volume){
        this.volume = volume;
    }
    public double getVolume(){
        return this.volume;
    }

}