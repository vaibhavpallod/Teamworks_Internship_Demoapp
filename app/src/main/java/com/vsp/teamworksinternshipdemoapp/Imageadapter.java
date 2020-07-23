package com.vsp.teamworksinternshipdemoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Imageadapter extends RecyclerView.Adapter<Imageadapter.MyviewHolder> {

    Context context;
    List<Jsonreturn> jsonreturnList;

    public Imageadapter(Context context, List<Jsonreturn> jsonreturnList) {
        this.context = context;
        this.jsonreturnList = jsonreturnList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.imagemodel, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        if (jsonreturnList.get(position).getUrl() != null && jsonreturnList.get(position).getTitle() != null) {
            String url = jsonreturnList.get(position).getUrl();
//            Log.i("imageids", url.substring(url.length() - 6, url.length()));
            holder.title.setText(jsonreturnList.get(position).getTitle());
            holder.id.setText(String.valueOf(jsonreturnList.get(position).getId()));

        String color=url.substring(url.length() - 6, url.length());
        if(!color.contains("/"))
            holder.imageView.setBackgroundColor(Color.parseColor("#" + color));


/*
        try {
//           URL url = new URL(jsonreturnList.get(position).getUrl());
           */
/*  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
*//*


//            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
//            toast("exception");
            toast(e.getMessage());
            e.printStackTrace();

        }
*/
        } else
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return jsonreturnList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id, title;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageincardview);
            id = itemView.findViewById(R.id.idid);
            title = itemView.findViewById(R.id.titleid);
        }
    }

    private void toast(String x) {
        Toast.makeText(context, x, Toast.LENGTH_SHORT).show();
    }

}
