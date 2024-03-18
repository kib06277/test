package edu.hcvs.weatherforecast.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.hcvs.weatherforecast.R;
import edu.hcvs.weatherforecast.data.WeatherData;

//每小時氣象 Adapter
public class WeatherEverytimeAdapter extends RecyclerView.Adapter<WeatherEverytimeAdapter.ViewHolder> {
    private List<WeatherData.Forecast.Hourly> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time , temperature;
        ImageView weather_icon;

        public ViewHolder(View v) {
            super(v);
            time = v.findViewById(R.id.time);
            temperature = v.findViewById(R.id.temperature);
            weather_icon = v.findViewById(R.id.weather_icon);
        }
    }

    //傳入資料
    public WeatherEverytimeAdapter(List<WeatherData.Forecast.Hourly> data) {
        mData = data;
    }

    @Override
    public WeatherEverytimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weathereverytime_item, parent, false);
        WeatherEverytimeAdapter.ViewHolder vh = new WeatherEverytimeAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WeatherEverytimeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            holder.time.setText(mData.get(position).getTime());
            holder.temperature.setText(mData.get(position).getTemperature());
            if(mData.get(position).getStatus().equals("打雷")) {
                holder.weather_icon.setImageResource(R.mipmap.thunder);
            } else if(mData.get(position).getStatus().equals("陣雨")) {
                holder.weather_icon.setImageResource(R.mipmap.rain);
            } else if(mData.get(position).getStatus().equals("多雲")) {
                holder.weather_icon.setImageResource(R.mipmap.cloud);
            } else if(mData.get(position).getStatus().equals("晴天")) {
                holder.weather_icon.setImageResource(R.mipmap.sun);
            } else {
                holder.weather_icon.setImageResource(R.mipmap.sun);
            }
        } catch (Exception e) {
            Log.i("WeatherError" , "adapter e = " + e);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}