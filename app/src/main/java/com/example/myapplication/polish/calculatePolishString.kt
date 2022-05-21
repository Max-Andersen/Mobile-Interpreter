package com.example.myapplication.polish

import android.content.Context
import android.widget.LinearLayout
import com.example.myapplication.printInConsole
import java.lang.Exception


fun calculatePolishString(polishString: String, variables: MutableMap<String, Int>, console: LinearLayout, ctx: Context): Int {
    val stack = Stack()
    var localPolishString = polishString
    while (localPolishString.isNotEmpty()) {
        val expr = localPolishString.substringBefore(',')
        if (expr.matches(Regex("[a-zA-Z_][\\w]*"))) {
            stack.ordinaryPush(variables[expr])
        } else {
            if (expr.matches(Regex("[1-9][\\d]*|0"))) {
                try {
                    stack.ordinaryPush(expr.toInt())
                } catch (e: Exception) {
                    stack.ordinaryPush(0)
                }
            } else {
                if(stack.isEmpty()){
                    printInConsole("#Invalid expression", console, ctx)
                    return 0
                }
                val second = stack.ordinaryPop()
                if(stack.isEmpty()){
                    printInConsole("#Invalid expression", console, ctx)
                    return 0
                }
                val first = stack.ordinaryPop()
                when (expr) {
                    "+" -> stack.ordinaryPush(first + second)
                    "-" -> stack.ordinaryPush(first - second)
                    "*" -> stack.ordinaryPush(first * second)
                    "/" -> stack.ordinaryPush(first / second)
                    "%" -> stack.ordinaryPush(first % second)
                }
            }
        }
        localPolishString = localPolishString.slice(expr.length + 1 until localPolishString.length)
    }

    return stack.ordinaryPop()
}