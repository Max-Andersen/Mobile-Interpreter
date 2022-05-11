package com.example.myapplication

import com.example.myapplication.structs.tree.TreeNode


//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~~~Глобальные важные штуки~~~~~~~~~~~~~~~~~~~~~

val myTree = TreeNode("start")
val varsIntMap: MutableMap<String, Int> = mutableMapOf("a" to 10, "b" to 20, "c" to 0)
val errorList: MutableList<String> = mutableListOf()
val outputList: MutableList<String> = mutableListOf()


//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~Некоторые отдельные функции~~~~~~~~~~~~~~~~~~~

fun fillTree() {
    myTree.add(TreeNode("while"))
    myTree.add(TreeNode("print"))

    myTree.children[0].add(TreeNode("condition"))
    myTree.children[0].add(TreeNode("body"))
    myTree.children[1].add(TreeNode("varInt"))

    myTree.children[0].children[0].add(TreeNode("compare_operator"))
    myTree.children[0].children[0].add(TreeNode("varInt"))
    myTree.children[0].children[0].add(TreeNode("varInt"))
    myTree.children[0].children[1].add(TreeNode("if_block"))
    myTree.children[1].children[0].add(TreeNode("a"))

    myTree.children[0].children[0].children[0].add(TreeNode("!="))
    myTree.children[0].children[0].children[1].add(TreeNode("b"))
    myTree.children[0].children[0].children[2].add(TreeNode("c"))
    myTree.children[0].children[1].children[0].add(TreeNode("condition"))
    myTree.children[0].children[1].children[0].add(TreeNode("if_body"))
    myTree.children[0].children[1].children[0].add(TreeNode("else_body"))

    myTree.children[0].children[1].children[0].children[0].add(TreeNode("compare_operator"))
    myTree.children[0].children[1].children[0].children[0].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[0].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[1].add(TreeNode("assign"))
    myTree.children[0].children[1].children[0].children[2].add(TreeNode("assign"))

    myTree.children[0].children[1].children[0].children[0].children[0].add(TreeNode(">"))
    myTree.children[0].children[1].children[0].children[0].children[1].add(TreeNode("a"))
    myTree.children[0].children[1].children[0].children[0].children[2].add(TreeNode("b"))

    myTree.children[0].children[1].children[0].children[1].children[0].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[1].children[0].add(TreeNode("minus"))
    myTree.children[0].children[1].children[0].children[2].children[0].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[2].children[0].add(TreeNode("minus"))

    myTree.children[0].children[1].children[0].children[1].children[0].children[0].add(TreeNode("a"))
    myTree.children[0].children[1].children[0].children[1].children[0].children[1].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[1].children[0].children[1].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[2].children[0].children[0].add(TreeNode("b"))
    myTree.children[0].children[1].children[0].children[2].children[0].children[1].add(TreeNode("varInt"))
    myTree.children[0].children[1].children[0].children[2].children[0].children[1].add(TreeNode("varInt"))

    myTree.children[0].children[1].children[0].children[1].children[0].children[1].children[0].add(TreeNode("a"))
    myTree.children[0].children[1].children[0].children[1].children[0].children[1].children[1].add(TreeNode("b"))
    myTree.children[0].children[1].children[0].children[2].children[0].children[1].children[0].add(TreeNode("b"))
    myTree.children[0].children[1].children[0].children[2].children[0].children[1].children[1].add(TreeNode("a"))
}


//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Блоки~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

fun blockMain(tree: TreeNode<String>) {
    for (i in tree.children) {
        if (i.value == "while") {
            if (i.children.size == 2) {
                blockWhile(i)
            } else
                errorList += "Empty while_block found"
        }
        if (i.value == "if_block") {
            if (i.children.size == 3) {
                blockIf(i)
            } else
                errorList += "Empty if_block found"
        }
        if (i.value == "print") {
            if (i.children.size == 1) {
                blockPrint(i.children[0])
            } else
                errorList += "Empty print_block found"
        }
        if (i.value == "assign") {
            if (i.children.size == 2) {
                blockAssign(i)
            } else
                errorList += "Empty assign_block found"
        }
    }
}

fun blockWhile(children: TreeNode<String>) {
    while (blockCondition(children.children[0])) {
        blockMain(children.children[1])
    }
}

fun blockIf(children: TreeNode<String>) {
    if (blockCondition(children.children[0])) {
        blockMain(children.children[1])
    } else {
        blockMain(children.children[2])
    }
}

//НЕ ДОДЕЛАН
fun blockPrint(children: TreeNode<String>) {

    when (children.value) {
        "varInt" -> {
            outputList += varsIntMap[children.children[0].value].toString()
        }
        "..." -> {

        }
        else -> errorList += "Unknown var type found"
    }

}

//НЕ ДОДЕЛАН
fun blockAssign(children: TreeNode<String>) {
    if (children.children[0].value == "varInt") {
        varsIntMap[children.children[0].children[0].value] = blockIntExpression(children.children[1])
    }
}

//НЕ ДОДЕЛАН
fun blockCondition(children: TreeNode<String>): Boolean {
    //есть оператор сравнения(compare operator)
    //есть операнд 1 и операнд 2
    var res = false

    if (children.children[0].value == "compare_operator") {
        if (children.children[0].children[0].value == "!=") {
            if (blockIntExpression(children.children[1]) != blockIntExpression(children.children[2])) {
                res = true
            }
        }
        if (children.children[0].children[0].value == "==") {
            if (blockIntExpression(children.children[1]) == blockIntExpression(children.children[2])) {
                res = true
            }
        }
        if (children.children[0].children[0].value == ">") {
            if (blockIntExpression(children.children[1]) > blockIntExpression(children.children[2])) {
                res = true
            }
        }
        if (children.children[0].children[0].value == "<") {
            if (blockIntExpression(children.children[1]) < blockIntExpression(children.children[2])) {
                res = true
            }
        }
        if (children.children[0].children[0].value == ">=") {
            if (blockIntExpression(children.children[1]) >= blockIntExpression(children.children[2])) {
                res = true
            }
        }
        if (children.children[0].children[0].value == "<=") {
            if (blockIntExpression(children.children[1]) <= blockIntExpression(children.children[2])) {
                res = true
            }
        }
    }

    return res
}

//НЕ ДОДЕЛАН
fun blockIntExpression(expression: TreeNode<String>): Int {
    var res = 0
    when (expression.value) {
        "minus" -> {
            res = blockIntExpression(expression.children[0]) - blockIntExpression(expression.children[1])
        }
        "plus" -> {
            res = blockIntExpression(expression.children[0]) + blockIntExpression(expression.children[1])
        }
        "multiply" -> {
            res = blockIntExpression(expression.children[0]) * blockIntExpression(expression.children[1])
        }
        "division" -> {
            res = blockIntExpression(expression.children[0]) / blockIntExpression(expression.children[1])
        }
        "varInt" -> {
            res = varsIntMap[expression.children[0].value]!!
        }
        else -> errorList += "Unknown int expression found"
    }

    return res
}

fun main() {

    //Заполнение дерева
    fillTree()

    //Вывод дерева
    myTree.printEachLevel()

    //Запуск программы
    blockMain(myTree)

    //Вывод полученных ошибок
    if (errorList.size != 0) {
        println()
        println("---------------")
        println("ERROR_LIST:")
        for (i in errorList)
            println(i)
    }

    //Вывод в консоль, если нет ошибок
    if (errorList.size == 0) {
        println()
        println("---------------")
        println("PROGRAM_OUTPUT:")
        for (i in outputList)
            println(i)
    }

}
