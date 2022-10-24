package br.com.fiap.ex3rickmorty

import br.com.fiap.ex3rickmorty.model.ResultModel

interface IFavoriteRickMorty {
    fun addFavorite(model: ResultModel)
    fun detailItem(model: ResultModel)
    fun removeFavorite(model: ResultModel)
}