package mdexample.derrick.com.theweatherapp.model;

/**
 * Created by Derrick on 2016/10/21.
 */

public class Coord {
    private double lon;

    private double lat;

    public void setLon(double lon){
        this.lon = lon;
    }
    public double getLon(){
        return this.lon;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public double getLat(){
        return this.lat;
    }

}
