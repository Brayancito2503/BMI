package com.example.pesoapp.View.ViewModel

import android.app.Activity
import android.app.AlertDialog
import com.example.pesoapp.R
import com.example.pesoapp.View.Fragment_sugerencias

class LoadingDialog(val mActivity: Fragment_sugerencias) {
    private lateinit var isdialog:AlertDialog
    fun startLoading(){
        /**set View*/
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.fragment_sugerencias,null)
        /**set Dialog*/
        val bulider = AlertDialog.Builder(mActivity.context)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}