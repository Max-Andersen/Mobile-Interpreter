package com.example.myapplication.polish


fun calculatePolishString (polishString: String, variables: MutableMap<String, Int>) : Int{
    println(polishString)
    val stack = Stack()
    var localPolishString = polishString
    while (localPolishString.isNotEmpty()){
        val expr = localPolishString.substringBefore(',')
        if (expr.matches(Regex("[a-zA-Z_][\\w]*"))){
            stack.ordinaryPush(variables[expr])
        }
        else{
            if (expr.matches(Regex("[1-9][\\d]*"))){
                stack.ordinaryPush(expr.toInt())
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