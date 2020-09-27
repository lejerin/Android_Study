package com.example.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.CustomRadioView.ButtonClickListener




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClickListener = ButtonClickListener {
            System.out.println("클릭 $it")
        }

        val customView = findViewById<CustomRadioView>(R.id.customView)
        val list = arrayListOf<String>("1","2","3","4","5","6","7")
        customView.setBtn(list)
        customView.setReportListener(buttonClickListener)


    }
}