package com.example.appmobile3a;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeCardApi {
    @GET("v1/cards")
    Call<Deck> getDeck();
}
