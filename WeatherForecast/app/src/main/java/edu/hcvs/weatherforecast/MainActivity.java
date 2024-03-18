package edu.hcvs.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import edu.hcvs.weatherforecast.adapter.WeatherEverydayAdapter;
import edu.hcvs.weatherforecast.adapter.WeatherEverytimeAdapter;
import edu.hcvs.weatherforecast.data.WeatherData;

//首頁
public class MainActivity extends AppCompatActivity {

    //基本宣告
    TextView WeatherTitle , City , temperature , temperature_higtAndlow , air_quality;
    RecyclerView recyclerview_everytime , recyclerview_everyday;
    List<WeatherData> weatherDataList; //資料儲存容器
    WeatherEverytimeAdapter weathereverytimeadapter;
    WeatherEverydayAdapter weathereverydayadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xmltocode(); //layout xml 和程式碼關聯
        weatherDataList = readXMLFromAssets(this); //將 xml 讀取出來
        setViewData(); //設定畫面資料
    }


    //layout xml 和程式碼關聯
    public void xmltocode(){
        WeatherTitle = findViewById(R.id.WeatherTitle);
        City = findViewById(R.id.City);
        temperature = findViewById(R.id.temperature);
        temperature_higtAndlow = findViewById(R.id.temperature_higtAndlow);
        air_quality = findViewById(R.id.air_quality);
        recyclerview_everytime = findViewById(R.id.recyclerview_everytime);
        recyclerview_everyday = findViewById(R.id.recyclerview_everyday);
    }

    //設定畫面資料
    public void setViewData(){
        try {
            City.setText(weatherDataList.get(0).weather.location);
            temperature.setText(weatherDataList.get(0).weather.current.temperature);
            String high_Temperature = weatherDataList.get(0).weather.forecast.get(0).daily.high_temperature;
            String low_Temperature = weatherDataList.get(0).weather.forecast.get(0).daily.low_temperature;
            temperature_higtAndlow.setText("最高 " + high_Temperature + "最低" + low_Temperature);
            air_quality.setText(weatherDataList.get(0).weather.forecast.get(0).daily.air_quality);

            //顯示今天每小時的氣溫
            weathereverytimeadapter = new WeatherEverytimeAdapter(weatherDataList.get(0).weather.forecast.get(0).hourly);
            final LinearLayoutManager layoutManager_everytime = new LinearLayoutManager(this);
            layoutManager_everytime.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerview_everytime.setLayoutManager(layoutManager_everytime);
            recyclerview_everytime.setAdapter(weathereverytimeadapter);

            //顯示每周氣溫
            weathereverydayadapter = new WeatherEverydayAdapter(weatherDataList.get(0).weather.forecast);
            final LinearLayoutManager layoutManager_everyday = new LinearLayoutManager(this);
            layoutManager_everyday.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview_everyday.setLayoutManager(layoutManager_everyday);
            recyclerview_everyday.setAdapter(weathereverydayadapter);
        } catch (Exception e) {
            Log.i("WeatherError" , "e = " + e);
        }
    }

    //讀取 Assets xml
    public static List<WeatherData> readXMLFromAssets(Context context) {
        Gson gson = new Gson(); //使用 gson
        Type listType = new TypeToken<List<WeatherData>>(){}.getType(); //轉換格式
        String readxml;
        try {
            InputStream is = context.getAssets().open("WeatherForecastData.xml"); //從 Assets 讀取 WeatherForecastData xml
            int size = is.available(); //查檔案大小
            byte[] buffer = new byte[size]; //建立存放檔案的 byte
            is.read(buffer); //將 xml 內容讀取出來存入 byte
            is.close(); //關閉 InputStream 通道
            readxml = new String(buffer, "UTF-8"); //將 byte 轉成 String 字串
            String json = "[" + convertXmlToJson(readxml).toString() + "]";
            List<WeatherData> Weather = gson.fromJson(json, listType); //將字串轉乘 gson 格式
            return Weather;
        } catch (Exception e) {
            Log.i("WeatherError" , "e = " + e);
            e.printStackTrace();
            return null;
        }
    }

    //將 xml 轉換成 json
    public static JSONObject convertXmlToJson(String xml) {
        JSONObject jsonObject = new JSONObject(); //建立一個 JSONObject
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            Element root = doc.getDocumentElement();
            JSONObject weather = new JSONObject();
            //組裝 location
            weather.put("location", root.getElementsByTagName("location").item(0).getTextContent());

            //組裝 current
            weather.put("current", parseCurrentWeather(root));

            // 組裝每日天氣情況
            JSONArray forecastArray = new JSONArray();
            for(int i = 0 ; i < root.getElementsByTagName("forecast").getLength() ; i++) {
                forecastArray.put(parseForecast(root , i));
            }

            // 組裝 forecast
            weather.put("forecast", forecastArray);

            // 組裝 weather
            jsonObject.put("weather", weather);
        } catch (Exception e) {
            Log.i("WeatherError" , "e = " + e);
            e.printStackTrace();
        }
        return jsonObject;
    }

    //讀取 xml 格式轉換成 json 並組裝
    private static JSONObject parseCurrentWeather(Element root) throws JSONException {
        JSONObject currentWeather = new JSONObject();
        Element current = (Element) root.getElementsByTagName("current").item(0); //讀取 xml current 值
        currentWeather.put("temperature", current.getElementsByTagName("temperature").item(0).getTextContent()); //讀取 temperature 並組裝成 json
        currentWeather.put("weather_status", current.getElementsByTagName("weather_status").item(0).getTextContent()); //讀取 weather_status 並組裝成 json
        return currentWeather;
    }

    //讀取 xml 格式轉換成 json 並組裝
    private static JSONObject parseForecast(Element root , int i) throws JSONException {
        JSONObject forecast = new JSONObject();
        Element forecastElement = (Element) root.getElementsByTagName("forecast").item(i); //讀取 xml forecast 值
        NodeList dailyList = forecastElement.getElementsByTagName("daily"); //讀取 xml daily 值
        Element dailyElement = (Element) dailyList.item(0);
        JSONObject dailyObject = new JSONObject();
        dailyObject.put("date", dailyElement.getElementsByTagName("date").item(0).getTextContent()); //讀取 date 並組裝成 json
        dailyObject.put("high_temperature", dailyElement.getElementsByTagName("high_temperature").item(0).getTextContent()); //讀取 high_temperature 並組裝成 json
        dailyObject.put("low_temperature", dailyElement.getElementsByTagName("low_temperature").item(0).getTextContent()); //讀取 low_temperature 並組裝成 json
        dailyObject.put("air_quality", dailyElement.getElementsByTagName("air_quality").item(0).getTextContent()); //讀取 air_quality 並組裝成 json
        forecast.put("daily", dailyObject); //組裝 daily

        JSONArray hourlyArray = new JSONArray();
        NodeList hourlyList = forecastElement.getElementsByTagName("hourly"); //讀取 xml hourly 值
        for (int j = 0; j < hourlyList.getLength(); j++) {
            Element hourlyElement = (Element) hourlyList.item(j);
            JSONObject hourlyObject = new JSONObject();
            hourlyObject.put("time", hourlyElement.getElementsByTagName("time").item(0).getTextContent()); //讀取 time 並組裝成 json
            hourlyObject.put("temperature", hourlyElement.getElementsByTagName("temperature").item(0).getTextContent()); //讀取 temperature 並組裝成 json
            hourlyObject.put("status", hourlyElement.getElementsByTagName("status").item(0).getTextContent()); //讀取 weather_status 並組裝成 json
            hourlyArray.put(hourlyObject);
        }
        forecast.put("hourly", hourlyArray);
        return forecast;
    }
}



