package com.moony.LoLTeamGenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.LoLTeamGenerator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), MyFragment.PlayerRemoval, InsertFragment.PlayerRegister {

    override fun removePlayer(string: String?){
        var foundIndex = -1
        for (f in playerList){
            if (f.playerName?.toLowerCase().equals(string?.toLowerCase())){
                foundIndex = 1
                playerList.remove(f)
            }
        }
        when(foundIndex){
            -1 -> {Toast.makeText(this,"Player não encontrado!",Toast.LENGTH_LONG).show()}
            1 -> {initView();Toast.makeText(this,"Player removido!",Toast.LENGTH_LONG).show()}
        }
    }

    override fun addPlayer(player: Player) {
        playerList.add(player)
        initView()
    }

    var playerList = ArrayList<Player>()

    // Iniciando a RecyclerView
    var listaAdapter: ListaAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

        binding.btnInsert.setOnClickListener {
            callInsertDialog()
        }

        binding.btnTeam.setOnClickListener {
            if (playerList.size < 4 )
            {
                Toast.makeText(this,"Players Insuficientes!\n" +
                        "Registre pelo menos mais ${4-playerList.size} players!",Toast.LENGTH_LONG).show()
            }
            else if (playerList.size == 4){
                var tempPlayerList = ArrayList<Player>()
                tempPlayerList.addAll(playerList)
                tempPlayerList.add( Player( "Random","Main",
                    "Main","Main","Main","Main" ) )

                val intent = Intent(this, TeamActivity::class.java)
                intent.putParcelableArrayListExtra("players",tempPlayerList)
                startActivity(intent)
            }
            else if(playerList.size >= 5){
                val intent = Intent(this, TeamActivity::class.java)
                intent.putParcelableArrayListExtra("players",playerList)
                startActivity(intent)
            }

        }

        binding.btnRemove.setOnClickListener {
            chamarMyDialog()
        }
    }

    private fun chamarMyDialog() {
        var myFragment : MyFragment = MyFragment()
        myFragment.show(supportFragmentManager,"my_fragment")
    }

    private fun callInsertDialog() {
        var myFragment : InsertFragment = InsertFragment()
        myFragment.show(supportFragmentManager,"insert_fragment")
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView(){
        listaAdapter = ListaAdapter(playerList,this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = listaAdapter
    }

}