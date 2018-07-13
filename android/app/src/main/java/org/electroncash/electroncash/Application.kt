package org.electroncash.electroncash

import com.chaquo.python.android.PyApplication

class Application : PyApplication() {
    override fun onCreate() {
        super.onCreate()
        Daemon.start()
    }
}