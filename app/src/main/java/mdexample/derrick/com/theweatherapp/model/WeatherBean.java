package mdexample.derrick.com.theweatherapp.model;

import java.util.List;

public class WeatherBean {
    private Coord coord;

    private List<Weather> weather ;

    private String base;

    private Main main;

    private Wind wind;

    private Rain rain;

    private Clouds clouds;

    private int dt;

    private Sys sys;

    private int id;

    private String name;

    private int cod;

    public void setCoord(Coord coord){
        this.coord = coord;
    }
    public Coord getCoord(){
        return this.coord;
    }
    public void setWeather(List<Weather> weather){
        this.weather = weather;
    }
    public List<Weather> getWeather(){
        return this.weather;
    }
    public void setBase(String base){
        this.base = base;
    }
    public String getBase(){
        return this.base;
    }
    public void setMain(Main main){
        this.main = main;
    }
    public Main getMain(){
        return this.main;
    }
    public void setWind(Wind wind){
        this.wind = wind;
    }
    public Wind getWind(){
        return this.wind;
    }
    public void setRain(Rain rain){
        this.rain = rain;
    }
    public Rain getRain(){
        return this.rain;
    }
    public void setClouds(Clouds clouds){
        this.clouds = clouds;
    }
    public Clouds getClouds(){
        return this.clouds;
    }
    public void setDt(int dt){
        this.dt = dt;
    }
    public int getDt(){
        return this.dt;
    }
    public void setSys(Sys sys){
        this.sys = sys;
    }
    public Sys getSys(){
        return this.sys;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCod(int cod){
        this.cod = cod;
    }
    public int getCod(){
        return this.cod;
    }

}