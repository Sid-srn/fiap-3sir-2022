package com.example.retrofitdeckcards

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDeckGame {
    @GET("new/shuffle")
    fun getDeck(
        @Query("deck_count")
        deckCount: Int
    ): Call<DeckModel>

    @GET("{deck_id}/draw")
    fun getDeckCard(
        @Path("deck_id") deckId: String,
        @Query("count") count: Int
    ): Call<CardsModel>


}