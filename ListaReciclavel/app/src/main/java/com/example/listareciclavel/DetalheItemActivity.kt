package com.example.listareciclavel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listareciclavel.Constants.INDEX_INTENT
import com.example.listareciclavel.Constants.ITEM_INTENT
import com.example.listareciclavel.databinding.ActivityDetalheItemBinding
import com.example.listareciclavel.model.ItemListaModel

class DetalheItemActivity : AppCompatActivity() {
    lateinit var dataBind: ActivityDetalheItemBinding

    var item : ItemListaModel? = null
    var index : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = ActivityDetalheItemBinding.inflate(layoutInflater)
        setContentView(dataBind.root)

        item = intent.getParcelableExtra(ITEM_INTENT)
        index = intent.getIntExtra(INDEX_INTENT, -1)

        if(item != null){
            dataBind.editItem.setText(item?.item)
            dataBind.editItemDetail.setText(item?.detalhe)
        }

    }
}