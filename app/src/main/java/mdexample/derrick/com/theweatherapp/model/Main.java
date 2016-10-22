package mdexample.derrick.com.theweatherapp.model;

/**
 * Created by Derrick on 2016/10/21.
 */

public class Main {
    private double temp;

    private double pressure;

    private int humidity;

    private double temp_min;

    private double temp_max;

    private double sea_level;

    private double grnd_level;

    public void setTemp(double temp){
        this.temp = temp;
    }
    public double getTemp(){
        return this.temp;
    }
    public void setPressure(double pressure){
        this.pressure = pressure;
    }
    public double getPressure(){
        return this.pressure;
    }
    public void setHumidity(int humidity){
        this.humidity = humidity;
    }
    public int getHumidity(){
        return this.humidity;
    }
    public void setTemp_min(double temp_min){
        this.temp_min = temp_min;
    }
    public double getTemp_min(){
        return this.temp_min;
    }
    public void setTemp_max(double temp_max){
        this.temp_max = temp_max;
    }
    public double getTemp_max(){
        return this.temp_max;
    }
    public void setSea_level(double sea_level){
        this.sea_level = sea_level;
    }
    public double getSea_level(){
        return this.sea_level;
    }
    public void setGrnd_level(double grnd_level){
        this.grnd_level = grnd_level;
    }
    public double getGrnd_level(){
        return this.grnd_level;
    }

}