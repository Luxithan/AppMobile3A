package com.example.appmobile3a.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appmobile3a.Constants;
import com.example.appmobile3a.data.PokeCardApi;
import com.example.appmobile3a.presentation.model.Card;
import com.example.appmobile3a.presentation.model.Deck;
import com.example.appmobile3a.presentation.view.MainActivity;
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

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){

       List<Card> cardList = getDataFromCache();

        if(cardList != null){
            view.showList(cardList);
        }else {
            makeApiCall();
        }

    }

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
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
                    view.showList(cardList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<Deck> call, Throwable t) {
                view.showError();
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

    private List<Card> getDataFromCache() {
        String jsonCard = sharedPreferences.getString(Constants.KEY_CARD_LIST, null);

        if(jsonCard == null){
            return null;
        }else {
            Type listType = new TypeToken<List<Card>>(){}.getType();
            return gson.fromJson(jsonCard, listType);
        }
    }

    public void onItemClick(Card card){

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
