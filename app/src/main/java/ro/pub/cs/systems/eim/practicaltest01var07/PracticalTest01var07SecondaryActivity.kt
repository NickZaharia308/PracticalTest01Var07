package ro.pub.cs.systems.eim.practicaltest01var07

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01var07SecondaryActivity : AppCompatActivity() {

    private var editText1: EditText? = null
    private var editText2: EditText? = null
    private var editText3: EditText? = null
    private var editText4: EditText? = null

    private var buttonSum: Button? = null
    private var buttonProduct: Button? = null

    private inner class PracticalTestListener : View.OnClickListener {
        override fun onClick(view: View) {

            when (view.id) {
                R.id.button_sum -> {
                    setResult(Constants.SUM_CODE)
                    finish()
                }
                R.id.button_product -> {
                    setResult(Constants.PRODUCT_CODE)
                    finish()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01var07_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText1 = findViewById(R.id.text1)
        editText2 = findViewById(R.id.text2)
        editText3 = findViewById(R.id.text3)
        editText4 = findViewById(R.id.text4)
        buttonSum = findViewById(R.id.button_sum)
        buttonProduct= findViewById(R.id.button_product)

        val myListener = PracticalTestListener()
        buttonSum!!.setOnClickListener(myListener)
        buttonProduct!!.setOnClickListener(myListener)

        val myIntent = intent
        if (myIntent != null) {
            var sum = myIntent.getStringExtra(Constants.INTENT_NUMBER1)
            editText1?.setText(sum)

            sum = myIntent.getStringExtra(Constants.INTENT_NUMBER2)
            editText2?.setText(sum)

            sum = myIntent.getStringExtra(Constants.INTENT_NUMBER3)
            editText3?.setText(sum)

            sum = myIntent.getStringExtra(Constants.INTENT_NUMBER4)
            editText4?.setText(sum)
        }
    }
}