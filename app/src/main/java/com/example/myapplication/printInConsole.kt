package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

fun printInConsole(text: String, CreateConsole: LinearLayout, ctx: Context) {

    val tv = TextView(ctx)
    tv.typeface = ResourcesCompat.getFont(ctx, R.font.russo_one)
    tv.text = ">>"
    tv.textSize = 25f
    tv.setTextColor(Color.WHITE)
    CreateConsole.addView(tv)

    val textViewToConsole = TextView(ctx)
    textViewToConsole.text = text
    textViewToConsole.textSize = 25f
    textViewToConsole.typeface = ResourcesCompat.getFont(ctx, R.font.russo_one)
    textViewToConsole.setTextColor(Color.WHITE)
    CreateConsole.addView(textViewToConsole)

}