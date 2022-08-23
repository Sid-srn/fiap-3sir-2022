package com.example.listareciclavel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.listareciclavel.databinding.ActivityMainBinding
import com.example.listareciclavel.model.ItemListaModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val lAdapter = ItemLinhaAdapter()
    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var rc = findViewById<RecyclerView>(R.id.lista_reciclavel_rv)
        setupRecyCler()
        binding.addItemBtn.setOnClickListener {
            lAdapter.addLista(ItemListaModel("Item $cont", "Detalhe do item $cont"))
            cont++
        }
    }

    private fun setupRecyCler() {
        binding.listaReciclavelRv.layoutManager = //LinearLayoutManager(this)
        StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL )
        val l = mutableListOf<ItemListaModel>()
        l.add(ItemListaModel("aaaa", "Detalhe AAAA"))
        l.add(ItemListaModel("bbb", "Detalhe BBBB"))
        l.add(ItemListaModel("ccc", "Detalhe CC"))
        lAdapter.setLista(l)
        binding.listaReciclavelRv.adapter = lAdapter
    }
}