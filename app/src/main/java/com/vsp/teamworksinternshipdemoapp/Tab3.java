package com.vsp.teamworksinternshipdemoapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Tab3 extends Fragment {
    View view;
    RecyclerView recyclerView;
    Imageadapter imageadapter;


    interface MyAPIService {

        @GET("/photos")
        Call<List<Jsonreturn>> getimages();
    }
    static class RetrofitClientInstance {

        private static Retrofit retrofit;
        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
    void setrecycler(List<Jsonreturn> jsonreturnList){
        imageadapter = new Imageadapter(getContext(),jsonreturnList);
        recyclerView.setAdapter(imageadapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.tab3, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAPIService myAPIService = RetrofitClientInstance.getRetrofitInstance().create(MyAPIService.class);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Call<List<Jsonreturn>> call = myAPIService.getimages();
        call.enqueue(new Callback<List<Jsonreturn>>() {
            @Override
            public void onResponse(Call<List<Jsonreturn>> call, Response<List<Jsonreturn>> response) {
                setrecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<Jsonreturn>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }
}
