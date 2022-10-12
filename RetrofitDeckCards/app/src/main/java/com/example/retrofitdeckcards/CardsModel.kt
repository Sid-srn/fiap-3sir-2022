package com.example.retrofitdeckcards

data class CardsModel(
    val cards: List<Card>,
    val deck_id: String,
    val remaining: Int,
    val success: Boolean
)