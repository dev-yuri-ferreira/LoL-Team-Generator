package com.moony.LoLTeamGenerator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class MyFragment : DialogFragment() {

    var edtPlayerName: EditText? = null
    var btnRemove: Button? = null

    interface  PlayerRemoval{
        fun removePlayer(string: String?)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var _view: View? = getActivity()?.getLayoutInflater()?.inflate(R.layout.my_fragment, null)

        this.edtPlayerName = _view?.findViewById(R.id.edtPlayerName)
        this.btnRemove = _view?.findViewById(R.id.btnRemove)

        var alert = AlertDialog.Builder(activity)
        alert.setView(_view)

        this.btnRemove!!.setOnClickListener {

            var b = edtPlayerName?.text.toString()

            (activity as (PlayerRemoval)).removePlayer(b)
            dismiss()

        }

        return alert.create()
    }
}