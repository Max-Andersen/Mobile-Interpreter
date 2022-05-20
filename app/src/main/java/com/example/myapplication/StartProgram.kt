package com.example.myapplication

import com.example.myapplication.blocks.*
import com.example.myapplication.interpreter.fillTree
import com.example.myapplication.structs.tree.TreeNode
import com.example.myapplication.interpreter.blockMain


class StartProgram(start: StartBtn) {

    private val receivedRoot = start
    private val myTree = TreeNode("start")
    private val varsIntMap: MutableMap<String, Int> = mutableMapOf()
    private val errorList: MutableList<String> = mutableListOf()
    private val outputList: MutableList<String> = mutableListOf()

    fun main() {

        //Заполнение дерева
        fillTree(receivedRoot, myTree, errorList)

        //Вывод дерева
        //myTree.printEachLevel()

        //Запуск программы
        blockMain(myTree, varsIntMap, errorList)

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
}
