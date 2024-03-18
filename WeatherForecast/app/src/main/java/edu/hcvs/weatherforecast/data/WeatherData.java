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
            public String high_temperature; //最高溫度
            public String low_temperature; //最低溫度
            public String air_quality; // 空氣品質

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh_temperature() {
                return high_temperature;
            }

            public void setHigh_temperature(String high_temperature) {
                this.high_temperature = high_temperature;
            }

            public String getLow_temperature() {
                return low_temperature;
            }

            public void setLow_temperature(String low_temperature) {
                this.low_temperature = low_temperature;
            }

            public String getAir_quality() {
                return air_quality;
            }

            public void setAir_quality(String air_quality) {
                this.air_quality = air_quality;
            }
        }

        //每小時預報
        public static class Hourly {
            public String time; //目前時間
            public String temperature; //目前溫度
            public String status; //目前天氣狀況

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
