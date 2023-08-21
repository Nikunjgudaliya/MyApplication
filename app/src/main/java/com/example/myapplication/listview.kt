package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class listview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        val users = arrayOf("a ", "b ", "c ", "d ", "e ", "f ", "g ", "h ", "i ", "j ", "k ", "l ", "m ", "n ", "o ", "p ", "q ", "r ", "s ", "t ", "u ", "v ", "w ", "x ", "y ", "z ")
        val mListView = findViewById<ListView>(R.id.list_item)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        mListView.adapter = arrayAdapter
    }
}