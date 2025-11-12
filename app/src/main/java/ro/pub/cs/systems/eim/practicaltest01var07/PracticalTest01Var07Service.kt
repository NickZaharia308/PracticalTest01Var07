package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PracticalTest01Var07Service : Service() {

    private lateinit var processingThread: ProcessingThread

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        processingThread = ProcessingThread(this)
        processingThread.start()

        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        processingThread.stopThread()
        super.onDestroy()
    }
}