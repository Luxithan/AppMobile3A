package com.example.appmobile3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appmobile3a.Constants;
import com.example.appmobile3a.R;
import com.example.appmobile3a.data.PokeCardApi;
import com.example.appmobile3a.presentation.model.Card;
import com.example.appmobile3a.presentation.model.Deck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static final String BASE_URL = "https://api.pokemontcg.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.KEY_APP_3A, Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Card> cardList = getDataFromCache();

        if(cardList != null){
            showList(cardList);
        }else {
            makeApiCall();
        }
    }

    private List<Card> getDataFromCache() {
        String jsonCard = sharedPreferences.getString(Constants.KEY_CARD_LIST, null);

        if(jsonCard == null){
            return null;
        }else {
            Type listType = new TypeToken<List<Card>>(){}.getType();
            return gson.fromJson(jsonCard, listType);
        }
    }

    private void showList(List<Card> cardList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(cardList);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeCardApi pokeCardApi = retrofit.create(PokeCardApi.class);

        Call<Deck> call = pokeCardApi.getDeck();
        call.enqueue(new Callback<Deck>() {
            @Override
            public void onResponse(Call<Deck> call, Response<Deck> response) {
                if(response.isSuccessful()  &&  response.body() != null){
                    List<Card> cardList = response.body().getCards();
                    saveList(cardList);
                    showList(cardList);
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<Deck> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Card> cardList) {
        String jsonString = gson.toJson(cardList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CARD_LIST, jsonString)
                .apply();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
