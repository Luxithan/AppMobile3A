package com.example.appmobile3a;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appmobile3a.data.PokeCardApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static PokeCardApi cardApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static PokeCardApi getCardApi(){
        if(cardApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            cardApiInstance = retrofit.create(PokeCardApi.class);

        }
        return cardApiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences(Constants.KEY_APP_3A, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
