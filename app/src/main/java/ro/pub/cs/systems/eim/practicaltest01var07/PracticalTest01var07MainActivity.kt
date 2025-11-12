package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object Constants {

    const val SUM_CODE = 2
    const val PRODUCT_CODE = 3
    const val MY_CODE = 4

    const val INTENT_NUMBER1 = "ro.pub.cs.systems.eim.practicaltest01var07.Number1_key"
    const val INTENT_NUMBER2 = "ro.pub.cs.systems.eim.practicaltest01var07.Number2_key"
    const val INTENT_NUMBER3 = "ro.pub.cs.systems.eim.practicaltest01var07.Number3_key"
    const val INTENT_NUMBER4 = "ro.pub.cs.systems.eim.practicaltest01var07.Number4_key"
    const val BROADCAST_MESSAGE_KEY = "broadcast_message_key"
    const val ACTION = "ro.pub.cs.systems.eim.practicaltest01var07.action1"
    const val TEXT_SERVICE_KEY = "Text_key"

    const val SERVICE_NUMBER1 = "ro.pub.cs.systems.eim.practicaltest01var07.Number1_key_service"
    const val SERVICE_NUMBER2 = "ro.pub.cs.systems.eim.practicaltest01var07.Number2_key_service"
    const val SERVICE_NUMBER3 = "ro.pub.cs.systems.eim.practicaltest01var07.Number3_key_service"
    const val SERVICE_NUMBER4 = "ro.pub.cs.systems.eim.practicaltest01var07.Number4_key_service"
}
class PracticalTest01var07MainActivity : AppCompatActivity() {

    private var editText1: EditText? = null
    private var editText2: EditText? = null
    private var editText3: EditText? = null
    private var editText4: EditText? = null

    private var buttonSet: Button? = null
    private var buttonRandom: Button? = null

    private var sum = 0
    private var product = 1

    private var isServiceStarted = false

    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val message = intent?.getStringExtra(Constants.BROADCAST_MESSAGE_KEY)
            var rnd1_str = intent?.getStringExtra(Constants.SERVICE_NUMBER1)
            var rnd2_str = intent?.getStringExtra(Constants.SERVICE_NUMBER2)
            var rnd3_str = intent?.getStringExtra(Constants.SERVICE_NUMBER3)
            var rnd4_str = intent?.getStringExtra(Constants.SERVICE_NUMBER4)

            val val1 = rnd1_str?.toIntOrNull() ?: 0
            val val2 = rnd2_str?.toIntOrNull() ?: 0
            val val3 = rnd3_str?.toIntOrNull() ?: 0
            val val4 = rnd4_str?.toIntOrNull() ?: 0
            sum = val1 + val2 + val3 + val4
            product = val1 * val2 * val3 * val4

            editText1!!.setText(rnd1_str)
            editText2!!.setText(rnd2_str)
            editText3!!.setText(rnd3_str)
            editText4!!.setText(rnd4_str)

            Log.d("MainActivity", "Received broadcast: $message")
        }
    }
    private inner class PracticalTestListener : View.OnClickListener {


        override fun onClick(view: View) {
            when (view.id) {
                R.id.button_random -> {

                    sum = 0
                    product = 1

                    var currentText = editText1!!.text.toString()
                    if (currentText == "") {
                        val rnds = (0..10).random()
                        sum = sum + rnds
                        product = product * rnds
                        editText1!!.setText(rnds.toString())
                    }

                    currentText = editText2!!.text.toString()
                    if (currentText == "") {
                        val rnds = (0..10).random()
                        sum = sum + rnds
                        product = product * rnds
                        editText2!!.setText(rnds.toString())
                    }

                    currentText = editText3!!.text.toString()
                    if (currentText == "") {
                        val rnds = (0..10).random()
                        sum = sum + rnds
                        product = product * rnds
                        editText3!!.setText(rnds.toString())
                    }

                    currentText = editText4!!.text.toString()
                    if (currentText == "") {
                        val rnds = (0..10).random()
                        sum = sum + rnds
                        product = product * rnds
                        editText4!!.setText(rnds.toString())
                    }
                }
                R.id.button_set -> {
                    val intent = Intent("ro.pub.cs.systems.eim.practicaltest01var07.intent.action.PracticalTest01var07SecondaryActivity")
                    intent.putExtra(Constants.INTENT_NUMBER1, editText1!!.text.toString())
                    intent.putExtra(Constants.INTENT_NUMBER2, editText2!!.text.toString())
                    intent.putExtra(Constants.INTENT_NUMBER3, editText3!!.text.toString())
                    intent.putExtra(Constants.INTENT_NUMBER4, editText4!!.text.toString())

                    startActivityForResult(intent, Constants.MY_CODE)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.`activity_practical_test01_var07_main`)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText1 = findViewById(R.id.text1)
        editText2 = findViewById(R.id.text2)
        editText3 = findViewById(R.id.text3)
        editText4 = findViewById(R.id.text4)
        buttonSet = findViewById(R.id.button_set)
        buttonRandom = findViewById(R.id.button_random)

        val myListener = PracticalTestListener()
        buttonSet!!.setOnClickListener(myListener)
        buttonRandom!!.setOnClickListener(myListener)

        if (savedInstanceState != null) {
            val savedSum = savedInstanceState.getString("savedSum", "1")
            val savedProduct = savedInstanceState.getString("savedProduct", "2")
            Log.d("SUMAPROD", "Suma este $savedSum")
            Log.d("SUMAPROD", "Produsul este $savedProduct")
        }

        startTheService()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedSum", sum.toString())
        outState.putString("savedProduct", product.toString())
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == Constants.MY_CODE) {
            if (resultCode == Constants.SUM_CODE)
                Toast.makeText(this, "The activity returned with result SUM " + sum.toString(), Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "The activity returned with result PRODUCT " + product.toString(), Toast.LENGTH_LONG).show()

        }

    }


    private fun startTheService() {
        val serviceIntent = Intent(applicationContext, PracticalTest01Var07Service::class.java)

        applicationContext.startService(serviceIntent)
        isServiceStarted = true
        Log.d("MainActivity", "Service started.")
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.ACTION)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(messageBroadcastReceiver, intentFilter, RECEIVER_EXPORTED)
        }
    }

    override fun onPause() {
        unregisterReceiver(messageBroadcastReceiver)
        super.onPause()
    }

    override fun onDestroy() {
        stopTheService()
        super.onDestroy()
    }

    private fun stopTheService() {
        if (isServiceStarted) {
            val serviceIntent = Intent(applicationContext, PracticalTest01Var07Service::class.java)
            applicationContext.stopService(serviceIntent)
            isServiceStarted = false
            Log.d("MainActivity", "Service stopped.")
        }
    }
}