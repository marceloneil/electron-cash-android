package org.electroncash.electroncash

import com.chaquo.python.PyObject
import com.chaquo.python.Python

class Daemon private constructor() {

    companion object {
        @get:Synchronized

        val instance: PyObject
            get () {
                if (_instance == null) start()
                return _instance!!
            }

        private val isStarted: Boolean
            get() = _instance != null

        // False positive warning
        // https://kotlinlang.org/docs/reference/coding-conventions.html#property-names
        private var _instance: PyObject? = null
        private var failed: Boolean = false

        @Synchronized
        fun start() {
            when {
                isStarted -> throw IllegalStateException("Daemon already started")
                failed -> throw IllegalStateException("Daemon startup previously failed, and cannot be retried")
                else -> {
                    try {
                        val py: Python = Python.getInstance()
                        _instance = py.getModule("utils.daemon").callAttr("get_daemon")
                    } catch (error: Throwable) {
                        failed = true
                        throw error
                    }
                }
            }
        }
    }
}