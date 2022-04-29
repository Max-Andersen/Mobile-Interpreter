package com.example.myapplication

import android.graphics.Color
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonplus: ImageButton = findViewById(R.id.plus1)
        val screen: ConstraintLayout = findViewById(R.id.blockscreen)
        val close: ImageButton = findViewById(R.id.closeBlockScreen)
        buttonplus.setOnClickListener() {
            screen.visibility = View.VISIBLE
            buttonplus.visibility = View.INVISIBLE
        }
        close.setOnClickListener(){
            screen.visibility = View.INVISIBLE
            buttonplus.visibility = View.VISIBLE
        }
    }

}