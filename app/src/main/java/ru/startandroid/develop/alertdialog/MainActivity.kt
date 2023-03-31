package ru.startandroid.develop.alertdialog

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val DIALOG = 1

class MainActivity : AppCompatActivity() {

    var btn: Int? = null
    var view: LinearLayout? = null
    var sdf: SimpleDateFormat? = SimpleDateFormat("HH:mm:ss")
    var tvCount: TextView? = null
    var textView: ArrayList<TextView>? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = ArrayList(10)
    }

    fun onClick(v: View) {
        btn = v.id
        showDialog(DIALOG)
    }

    override fun onCreateDialog(id: Int): Dialog? {
        val adb: AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle("Custom dialog")
        view = layoutInflater.inflate(R.layout.dialog, null) as LinearLayout?
        adb.setView(view)
        tvCount = view!!.findViewById(R.id.tvCount)
        return adb.create()
    }

    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
        super.onPrepareDialog(id, dialog)
        if (id == DIALOG) {
            val tvTime: TextView = dialog!!.window!!.findViewById(R.id.tvTime)
            tvTime.text = sdf!!.format(Date(System.currentTimeMillis()))
            if (btn == R.id.btnAdd) {
                val tv = TextView(this)
                view!!.addView(tv, LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT))
                tv.text = "TextView ${textView!!.size + 1}"
                textView!!.add(tv)
            } else if (textView!!.size > 0) {
                val tv: TextView = textView!!.get(textView!!.size -1)
                view!!.removeView(tv)
                textView!!.remove(tv)
            }
        }
        tvCount!!.text = "Кол-во TextView = ${textView!!.size}"
    }
}