package edu.hcvs.weatherforecast.data;

import java.util.List;

//天氣
public class WeatherData {

    public Weather weather; //天氣

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    //當前天氣
    public static class Weather {
        public String location; //當前地點
        public Current current; //當前位置
        public List<Forecast> forecast; //每天每小時預報

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Current getCurrent() {
            return current;
        }

        public void setCurrent(Current current) {
            this.current = current;
        }

        public List<Forecast> getForecast() {
            return forecast;
        }

        public void setForecast(List<Forecast> forecast) {
            this.forecast = forecast;
        }
    }

    //當前位置
    public static class Current {
        public String temperature; //溫度
        public String weather_status; //天氣狀態

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather_status() {
            return weather_status;
        }

        public void setWeather_status(String weather_status) {
            this.weather_status = weather_status;
        }
    }

    //每天每小時預報
    public static class Forecast {
        public Daily daily; //每日預報
        public List<Hourly> hourly; //每小時預報

        public Daily getDaily() {
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }

        public List<Hourly> getHourly() {
            return hourly;
        }

        public void setHourly(List<Hourly> hourly) {
            this.hourly = hourly;
        }

        //每日預報
        public static class Daily {
            public String date; //日期
            public String highTemperature; //最高溫度
            public String lowTemperature; //最低溫度
            public String airQuality; // 空氣品質

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHighTemperature() {
                return highTemperature;
            }

            public void setHighTemperature(String highTemperature) {
                this.highTemperature = highTemperature;
            }

            public String getLowTemperature() {
                return lowTemperature;
            }

            public void setLowTemperature(String lowTemperature) {
                this.lowTemperature = lowTemperature;
            }

            public String getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(String airQuality) {
                this.airQuality = airQuality;
            }
        }

        //每小時預報
        public static class Hourly {
            public String time; //目前時間
            public String temperature; //目前溫度
            public String weatherStatus; //目前天氣狀況

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeatherStatus() {
                return weatherStatus;
            }

            public void setWeatherStatus(String weatherStatus) {
                this.weatherStatus = weatherStatus;
            }
        }
    }
}
