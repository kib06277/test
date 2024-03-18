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

//每天氣象 Adapter
public class WeatherEverydayAdapter extends RecyclerView.Adapter<WeatherEverydayAdapter.ViewHolder> {
    private List<WeatherData.Forecast> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView day , low_temperature , high_temperature;
        ImageView airquality_icon;

        public ViewHolder(View v) {
            super(v);
            day = v.findViewById(R.id.day);
            low_temperature = v.findViewById(R.id.low_temperature);
            high_temperature = v.findViewById(R.id.high_temperature);
            airquality_icon = v.findViewById(R.id.airquality_icon);
        }
    }

    //傳入資料
    public WeatherEverydayAdapter(List<WeatherData.Forecast> data) {
        mData = data;
    }

    @Override
    public WeatherEverydayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weathereveryday_item, parent, false);
        WeatherEverydayAdapter.ViewHolder vh = new WeatherEverydayAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WeatherEverydayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            holder.day.setText(mData.get(position).daily.date);
            holder.low_temperature.setText(mData.get(position).daily.low_temperature);
            holder.high_temperature.setText(mData.get(position).daily.high_temperature);
            Log.i("Hello" , "airquality = " + mData.get(position).daily.air_quality);
            if(mData.get(position).daily.air_quality.equals("良好")) {
                holder.airquality_icon.setImageResource(R.mipmap.good);
            } else if(mData.get(position).daily.air_quality.equals("尚可")) {
                holder.airquality_icon.setImageResource(R.mipmap.ok);
            } else if(mData.get(position).daily.air_quality.equals("差勁")) {
                holder.airquality_icon.setImageResource(R.mipmap.nogood);
            }else {
                holder.airquality_icon.setImageResource(R.mipmap.good);
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