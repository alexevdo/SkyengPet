package com.sano.skyengpet.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sano.skyengpet.R

class TranslatedWordsAdapter: RecyclerView.Adapter<TranslatedWordsAdapter.ViewHolder>() {

    private val items: MutableList<String> = mutableListOf()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val wordTextView: TextView = itemView.findViewById(R.id.word_tv)

        fun bind(word: String) {
            wordTextView.text = word
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int) = R.layout.translated_row

    override fun getItemCount() = items.count()

    fun setItems(translatedWords: List<String>?) {
        items.clear()
        translatedWords?.let {
            items.addAll(it)
            notifyDataSetChanged()
        }
    }
}