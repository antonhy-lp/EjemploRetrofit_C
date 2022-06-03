package mx.edu.tecmm.ejemploretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersona (context: Context, private  var datos: List<Persona>) :
    RecyclerView.Adapter<AdaptadorPersona.ViewHolderPersona> (){
    class ViewHolderPersona(item: View):
        RecyclerView.ViewHolder(item){
        var txtNombre : TextView = item.findViewById(R.id.txtNombre)
        var txtDomicilio: TextView = item.findViewById(R.id.txtDomicilio)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersona {
        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent,false)
        return ViewHolderPersona(layoutItem)
    }

    override fun onBindViewHolder(holder: ViewHolderPersona, position: Int) {
        val persona = datos [position]
        holder.txtNombre.text = "${persona.nombre} ${persona.apellido}"
        holder.txtDomicilio.text = persona.edad
    }

    override fun getItemCount(): Int = datos.size



}