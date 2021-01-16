package com.example.pruebacertificacion.vista

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebacertificacion.R
import com.example.pruebacertificacion.pojo.Books


class BooksAdaptador(var list: List<Books>) :
    RecyclerView.Adapter<BooksAdaptador.LibrosHolder>() {

    fun updateData(listBooks: List<Books>) {
        Log.d("ACTUALIZA", "update ${listBooks.size}")
        list = listBooks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contenedor_items, parent, false)
        return LibrosHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LibrosHolder, position: Int) {

        val libro = list[position]
        holder.id.text = libro.id.toString()
        holder.author.text = libro.author
        holder.country.text = libro.country
        holder.language.text = libro.language
        holder.title.text = libro.title

        val imgURL: String = libro.imageLink

        Glide.with(holder.img.getContext())
            .load(imgURL)
            .centerCrop()
            .into(holder.img)

    }

    class LibrosHolder(view: View) : RecyclerView.ViewHolder(view) {

        val id: TextView = itemView.findViewById(R.id.txtId)
        val author: TextView = itemView.findViewById(R.id.txtAuthor)
        val country: TextView = itemView.findViewById(R.id.txtCountry)
        val language: TextView = itemView.findViewById(R.id.txtLanguage)
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val img: ImageView = itemView.findViewById(R.id.imagen)

    }
}