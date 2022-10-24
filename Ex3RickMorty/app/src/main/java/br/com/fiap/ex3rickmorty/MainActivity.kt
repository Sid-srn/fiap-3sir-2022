package br.com.fiap.ex3rickmorty

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.ex3rickmorty.Contants.ITEM_RM
import br.com.fiap.ex3rickmorty.Contants.LIST_RM
import br.com.fiap.ex3rickmorty.Contants.SHARED_RM
import br.com.fiap.ex3rickmorty.databinding.ActivityMainBinding
import br.com.fiap.ex3rickmorty.model.ResultModel
import br.com.fiap.ex3rickmorty.model.RickMortyModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class MainActivity : AppCompatActivity(), IFavoriteRickMorty {

    private lateinit var databind: ActivityMainBinding
    private val adapter = RickMortyAdapter(this)
    private var favoriteList = mutableListOf<ResultModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        favoriteList = getFavoriteRickMortyList()
        setupReclycler()
        getCharacters()
    }

    private fun setupReclycler() {
        databind.rvRickMortyList.layoutManager = LinearLayoutManager(this)
        databind.rvRickMortyList.adapter = adapter

        adapter.setList(mutableListOf())
    }

    private fun getCharacters() {
        val endPoint = getRickMortyEndPoint()
        val callBack = endPoint.getCharacters()
        callBack.enqueue(object : Callback<RickMortyModel> {
            override fun onResponse(call: Call<RickMortyModel>, response: Response<RickMortyModel>) {
                val list = response.body()?.results?.let { mergeFavorites(it) }
                if (list != null) {
                    adapter.setList(list)
                }
            }

            override fun onFailure(call: Call<RickMortyModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getRickMortyEndPoint(): IRickMortyApi {
        val retroFitClient = RetrofitFactory.retroFitInstance("https://rickandmortyapi.com/api/")
        return retroFitClient.create(IRickMortyApi::class.java)
    }

    private fun getFavoriteRickMortyList(): ArrayList<ResultModel> {

        val sharedPref = this.getSharedPreferences(SHARED_RM, Context.MODE_PRIVATE)
        val gsonValue = sharedPref?.getString(LIST_RM, null)
        if (gsonValue != null) {
            val itemType: Type = object : TypeToken<ArrayList<ResultModel>>() {}.type
            return Gson().fromJson(gsonValue, itemType)
        }
        return ArrayList()
    }

    private fun saveFavoriteRickMortyList(favorites: List<ResultModel>) {
        val sharedPreference =  getSharedPreferences(SHARED_RM, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val json = Gson().toJson(favorites)
        editor.putString(LIST_RM,json)
        editor.apply()
    }

    private fun mergeFavorites(characters: List<ResultModel>): List<ResultModel> {
        return characters.mapIndexed { _, character ->
            character.copy(favorite = (favoriteList.any { it.id == character.id }))
        }
    }

    override fun addFavorite(model: ResultModel) {
        favoriteList.add(model)
        saveFavoriteRickMortyList(favoriteList)
    }

    override fun detailItem(model: ResultModel) {
        val intent = Intent(this, RickMortyDetailActivity::class.java)
        intent.putExtra(ITEM_RM, model)
        startActivity(intent)
    }

    override fun removeFavorite(model: ResultModel) {
        val removedIndex = favoriteList.indexOf(model)
        favoriteList.remove(model)
        saveFavoriteRickMortyList(favoriteList)
    }
}