package com.example.myapplication.polish

import java.lang.Exception


fun calculatePolishString (polishString: String, variables: MutableMap<String, Int>) : Int{
    val stack = Stack()
    var localPolishString = polishString
    while (localPolishString.isNotEmpty()){
        val expr = localPolishString.substringBefore(',')
        if (expr.matches(Regex("[a-zA-Z_][\\w]*"))){
            stack.ordinaryPush(variables[expr])
        }
        else{
            if (expr.matches(Regex("[1-9][\\d]*|0"))){
                try {
                    stack.ordinaryPush(expr.toInt())
                }
                catch (e: Exception){
                    stack.ordinaryPush(0)
                }
            }
            else{
                val second = stack.ordinaryPop()
                val first = stack.ordinaryPop()
                when (expr){
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