package com.moony.LoLTeamGenerator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moony.LoLTeamGenerator.databinding.ContentListaBinding

private lateinit var binding: ContentListaBinding

class TeamAdapter (playerList: List<Player>,internal var ctx: Context
,internal var order: Int): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    internal var playerList: List<Player> = ArrayList<Player>()
    internal var matchList: List<List<Player>> = ArrayList<ArrayList<Player>>()
    init {
        this.playerList = playerList
        this.order = order
        this.matchList = permute(this.playerList)
        this.matchList = sortMatches(matchList,order)
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
        holder.value.visibility = View.GONE
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
        else
        {
            val topLane = matchList[position-1][0].playerName
            val jngLane = matchList[position-1][1].playerName
            val midLane = matchList[position-1][2].playerName
            val botLane = matchList[position-1][3].playerName
            val supLane = matchList[position-1][4].playerName

            holder.topLane.text = topLane
            val topColor = decideColor(matchList[position-1][0].playerLanes["Top"])
            holder.topLane.setBackgroundColor(topColor)

            holder.jngLane.text = jngLane
            val jngColor = decideColor(matchList[position-1][1].playerLanes["Jng"])
            holder.jngLane.setBackgroundColor(jngColor)


            holder.midLane.text = midLane
            val midColor = decideColor(matchList[position-1][2].playerLanes["Mid"])
            holder.midLane.setBackgroundColor(midColor)


            holder.botLane.text = botLane
            val adcColor = decideColor(matchList[position-1][3].playerLanes["Adc"])
            holder.botLane.setBackgroundColor(adcColor)


            holder.supLane.text = supLane
            val supColor = decideColor(matchList[position-1][4].playerLanes["Sup"])
            holder.supLane.setBackgroundColor(supColor)
        }

    }

    fun decideColor(skill: String?): Int{
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
        return matchList.size+1
    }

    // Aqui é a criação dos itens do viewholder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var value = binding.tbName
        var topLane = binding.topLane
        var jngLane = binding.jngLane
        var midLane = binding.midLane
        var botLane = binding.botLane
        var supLane = binding.supLane
//        var name = view.tvAdpNome
    }

    fun <T> permute(input: List<T>): List<List<T>> {
        if (input.size == 1) return listOf(input)
        val perms = mutableListOf<List<T>>()
        val sortPerm = mutableMapOf<List<T>,Int>()
        val toInsert = input[0]
        for (perm in permute(input.drop(1))) {
            for (i in 0..perm.size) {
                val newPerm = perm.toMutableList()
                newPerm.add(i, toInsert)
                perms.add(newPerm)

            }
        }

        return perms
    }

    fun sortMatches(teams: List<List<Player>>, order: Int): List<List<Player>>{
        val sortedList = mutableListOf<List<Player>>()
        val sorterMap = mutableMapOf<List<Player>,Int>()
        for (t in teams){
            sorterMap[t] = calcValue(t)
        }
        val result = sorterMap.toList().sortedBy { (_, value) -> value}.toMap()
        var count = 0
        for (r in result){
            sortedList.add(r.key)
        }

        var finalList: List<List<Player>> = ArrayList<ArrayList<Player>>()
        if (order.equals(1)){finalList = sortedList.toList()}
        else if(order.equals(-1)){finalList = sortedList.asReversed() }

        return finalList

    }

    fun calcValue(match: List<Player>):Int{
        var value = 0
        value += getValue(match[0].getLane("Top"))
        value += getValue(match[1].getLane("Jng"))
        value += getValue(match[2].getLane("Mid"))
        value += getValue(match[3].getLane("Adc"))
        value += getValue(match[4].getLane("Sup"))
        return value
    }

    fun getValue(skill: String?): Int{
        var v = 0
        when(skill){
            "Feed" -> v = 10000
            "Danger" -> v = 1000
            "Carryable" -> v = 100
            "Experienced" -> v = 10
            "Main" -> v = 1
        }
        return v
    }

}