package org.electroncash.electroncash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chaquo.python.PyObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val daemon: PyObject = Daemon.instance
        val height: PyObject = daemon["network"]!!.callAttr("get_local_height")
        Log.i("daemon", height.toString())
    }
}
