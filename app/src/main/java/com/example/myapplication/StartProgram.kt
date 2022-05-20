package com.example.myapplication

import android.content.Context
import android.widget.LinearLayout
import com.example.myapplication.blocks.*
import com.example.myapplication.interpreter.fillTree
import com.example.myapplication.structs.tree.TreeNode
import com.example.myapplication.interpreter.blockMain


class StartProgram(start: StartBtn) {

    private val receivedRoot = start
    private val myTree = TreeNode("start")
    private val varsIntMap: MutableMap<String, Int> = mutableMapOf()

    fun main(console: LinearLayout, ctx: Context) {

        //Заполнение дерева
        fillTree(receivedRoot, myTree, console, ctx)

        //Вывод дерева
        //myTree.printEachLevel()

        printInConsole("@PROGRAM_OUTPUT:", console, ctx)

        //Запуск программы
        blockMain(myTree, varsIntMap, console, ctx)
    }
}
