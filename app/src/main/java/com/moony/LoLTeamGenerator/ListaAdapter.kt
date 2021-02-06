package com.moony.LoLTeamGenerator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moony.LoLTeamGenerator.databinding.ContentListaBinding

private lateinit var binding: ContentListaBinding

class ListaAdapter (playerList: List<Player>,
                    internal var ctx: Context): RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    internal var playerList: List<Player> = ArrayList<Player>()
    init {
        this.playerList = playerList
    }

    // Aqui é onde o viewholder é criado a partir do layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ContentListaBinding.inflate(LayoutInflater.from(ctx), parent, false)
        val view = binding.root
//        val view = LayoutInflater.from(ctx).inflate(R.layout.content_lista, parent, false)
        return ViewHolder(view)
    }

    // Nessa parte é onde se modifica o item do viewholder
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0)
        {
            holder.topLane.text = "TOP"
            holder.topLane.setBackgroundColor(Color.WHITE)
            holder.jngLane.text = "JUNGLE"
            holder.jngLane.setBackgroundColor(Color.WHITE)
            holder.midLane.text = "MID"
            holder.midLane.setBackgroundColor(Color.WHITE)
            holder.botLane.text = "ADC"
            holder.botLane.setBackgroundColor(Color.WHITE)
            holder.supLane.text = "SUP"
            holder.supLane.setBackgroundColor(Color.WHITE)
        }
        else {
            val name = playerList[position-1].playerName
            val topLane = playerList[position-1].playerLanes["Top"]
            val jngLane = playerList[position-1].getLane("Jng")
            val midLane = playerList[position-1].getLane("Mid")
            val botLane = playerList[position-1].getLane("Adc")
            val supLane = playerList[position-1].getLane("Sup")


            holder.name.text = name
            if (position-1 % 2 == 0) holder.name.setBackgroundColor(Color.GRAY)
            else holder.name.setBackgroundColor(Color.WHITE)


            holder.topLane.text = topLane
            decideColor(holder.topLane)

            holder.jngLane.text = jngLane
            decideColor(holder.jngLane)

            holder.midLane.text = midLane
            decideColor(holder.midLane)

            holder.botLane.text = botLane
            decideColor(holder.botLane)

            holder.supLane.text = supLane
            decideColor(holder.supLane)
        }
    }
    
    fun decideColor(txtView: TextView){
        when(txtView.text.toString()){
            "Feed" -> {txtView.setBackgroundColor(Color.RED)}
            "Danger" -> {txtView.setBackgroundColor(Color.YELLOW)}
            "Carryable" -> {txtView.setBackgroundColor(Color.LTGRAY)}
            "Experienced" -> {txtView.setBackgroundColor(Color.CYAN)}
            "Main" -> {txtView.setBackgroundColor(Color.GREEN)}
        }
    }

    fun decideColor(skill: String): Int{
        var color:Int = 0
        when(skill){
            "Feed" -> {color = (Color.RED)}
            "Danger" -> {color = (Color.YELLOW)}
            "Carryable" -> {color = (Color.LTGRAY)}
            "Experienced" -> {color = (Color.CYAN)}
            "Main" -> {color = (Color.GREEN)}
        }
        return color
    }
    
    // Devolve quantidade de itens do nameList
    override fun getItemCount(): Int {
        return playerList.size+1
    }

    // Aqui é a criação dos itens do viewholder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var  name = binding.tbName
        var topLane = binding.topLane
        var jngLane = binding.jngLane
        var midLane = binding.midLane
        var botLane = binding.botLane
        var supLane = binding.supLane
//        var name = view.tvAdpNome
    }
}