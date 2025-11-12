package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Date
import kotlin.collections.get
import kotlin.math.sqrt
import kotlin.random.Random

class ProcessingThread(    private val context: Context,
    ) : Thread() {

    private var isRunning = true

    override fun run() {
        while (isRunning) {
            Log.d("ProcessingThread", "Thread has started")
            sendMessage()
            sleep(10000)
        }
    }

    private fun sendMessage() {
        val intent = Intent()

        val action = Constants.ACTION
        intent.action = action

        val rnds1 = (0..10).random()
        val rnds2 = (0..10).random()
        val rnds3 = (0..10).random()
        val rnds4 = (0..10).random()

        val message = "Values: $rnds1 $rnds2 $rnds3 $rnds4"
        intent.putExtra(Constants.BROADCAST_MESSAGE_KEY, message)
        intent.putExtra(Constants.SERVICE_NUMBER1, rnds1.toString())
        intent.putExtra(Constants.SERVICE_NUMBER2, rnds2.toString())
        intent.putExtra(Constants.SERVICE_NUMBER3, rnds3.toString())
        intent.putExtra(Constants.SERVICE_NUMBER4, rnds4.toString())


        context.sendBroadcast(intent)
    }

    fun stopThread() {
        isRunning = false
    }
}