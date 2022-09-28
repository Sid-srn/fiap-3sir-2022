package com.example.listareciclavel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemListaModel(
    var item: String,
    var detalhe: String,
    var isDetailVisile: Boolean = false
) : Parcelable