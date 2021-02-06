package com.moony.LoLTeamGenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.LoLTeamGenerator.databinding.ActivityTeamBinding

private lateinit var binding: ActivityTeamBinding

class TeamActivity : AppCompatActivity() {

    var playerList = ArrayList<Player>()

    var teamAdapter: TeamAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        playerList = intent.getParcelableArrayListExtra<Player>("players") as ArrayList<Player>

        initView()

    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView(){
        teamAdapter = TeamAdapter(playerList,this,-1)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerTeam.layoutManager = linearLayoutManager
        binding.recyclerTeam.adapter = teamAdapter
    }
}