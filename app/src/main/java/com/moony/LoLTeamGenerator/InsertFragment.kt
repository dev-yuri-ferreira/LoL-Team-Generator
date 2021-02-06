package com.moony.LoLTeamGenerator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.moony.LoLTeamGenerator.databinding.InsertFragmentBinding

class InsertFragment:DialogFragment() {

    interface PlayerRegister{
        fun addPlayer(player: Player)
    }

    private var _binding: InsertFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = InsertFragmentBinding.inflate(LayoutInflater.from(context))
        var alert = AlertDialog.Builder(activity)
        alert.setView(binding.root)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnInsertNome.setOnClickListener {
            val name = binding.etNome.text.toString()
            val top = binding.spin01.selectedItem.toString()
            val jng = binding.spin02.selectedItem.toString()
            val mid = binding.spin03.selectedItem.toString()
            val adc = binding.spin04.selectedItem.toString()
            val sup = binding.spin05.selectedItem.toString()
            (activity as (PlayerRegister)).
            addPlayer(Player(name,top,jng,mid,adc,sup))
            dismiss()
        }

        return alert.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}