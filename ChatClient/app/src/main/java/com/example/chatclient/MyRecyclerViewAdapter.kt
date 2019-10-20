package com.example.chatclient

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyRecyclerViewAdapter(private val context: Context, private val myData:List<String>):
    RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): MyViewHolder {
        Log.d("MyActivity", "OnCreateVIewHolder()")
        val itemView = LayoutInflater.from(context).inflate(R.layout.my_text_view, vg, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    override fun onBindViewHolder(vh: MyViewHolder, pos: Int) {
        Log.d("MyActivity", "onBindViewHolder ($pos)")
        vh.itemView.findViewById<TextView>(R.id.textView).text = myData[pos]

    }
}

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)