package com.example.newskmm.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newskmm.usecase.ResponseModel

class Adapter(private val items: List<ResponseModel.Articles>) :
    RecyclerView.Adapter<Adapter.ViewModel>() {

    inner class ViewModel(v: View) : RecyclerView.ViewHolder(v) {

        private val textView: TextView = v.findViewById(R.id.textView)

        fun onBind(item: ResponseModel.Articles) {
            textView.text = item.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        return ViewModel(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size
}