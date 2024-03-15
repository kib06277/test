package edu.hcvs.weatherforecast.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.hcvs.weatherforecast.R;
import edu.hcvs.weatherforecast.data.WeatherData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<WeatherData> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Day , temperature , weather_status;
        ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            Day = v.findViewById(R.id.Day);
            temperature = v.findViewById(R.id.temperature);
            weather_status = v.findViewById(R.id.weather_status);
            imageView = v.findViewById(R.id.imageView);
        }
    }

    //傳入資料
    public MyAdapter(List<WeatherData> data) {
        mData = data;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            Log.i("Hello" , "date = " + mData.get(position).weather.forecast.get(position).daily.date);
            Log.i("Hello" , "highTemperature = " + mData.get(position).weather.forecast.get(position).daily.highTemperature);
            Log.i("Hello" , "lowTemperature = " + mData.get(position).weather.forecast.get(position).daily.lowTemperature);
            Log.i("Hello" , "airQuality = " + mData.get(position).weather.forecast.get(position).daily.airQuality);
            holder.Day.setText(mData.get(position).weather.forecast.get(position).daily.date);
            String high_temperature = mData.get(position).weather.forecast.get(position).daily.highTemperature;
            String low_temperature = mData.get(position).weather.forecast.get(position).daily.lowTemperature;
            holder.temperature.setText(high_temperature + "°/" + low_temperature + "°");
            holder.weather_status.setText(high_temperature + "°/" + low_temperature + "°");
            if(mData.get(position).weather.forecast.get(position).daily.airQuality.equals("良好")){
                holder.imageView.setImageResource(R.mipmap.good);
            } else {
                holder.imageView.setImageResource(R.mipmap.nogood);
            }
        } catch (Exception e) {
            Log.i("Hello" , "adapter e = " + e);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
