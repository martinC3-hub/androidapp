package com.example.login.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.Busqueda
import com.example.login.R

class BusquedaAdapter(
    private var listaOriginal: List<Busqueda>,
    private val onClickListener:(Busqueda) -> Unit,
    private val onClickDelete:(Int) -> Unit
) : RecyclerView.Adapter<BusquedaViewHolder>(){
    //public var listaFiltrada: List<Busqueda> = listaOriginal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return BusquedaViewHolder(layoutInflater.inflate(R.layout.item_busqueda, parent, false))
    }

    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
        val item=listaOriginal[position]
        holder.render(item, onClickListener, onClickDelete)

    }

    override fun getItemCount(): Int = listaOriginal.size
}
