package com.example.myapplication

import android.content.Context
import android.view.ViewGroup
import androidx.core.view.children
import com.example.myapplication.structs.tree.TreeNode

import androidx.core.view.get
import com.example.myapplication.blocks.*

class StartProgram(context: Context, start: StartBtn) {

    private val ctx = context

    private val receivedRoot = start

//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~~~Глобальные важные штуки~~~~~~~~~~~~~~~~~~~~~

    private val myTree = TreeNode("start")
    private val varsIntMap: MutableMap<String, Int> = mutableMapOf("a" to 10, "b" to 20, "c" to 0)
    private val errorList: MutableList<String> = mutableListOf()
    private val outputList: MutableList<String> = mutableListOf()


//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~Некоторые отдельные функции~~~~~~~~~~~~~~~~~~~

    private fun workWithWhile(tree: TreeNode<String>, view: WhileBtn){

        if((view[2] as ViewGroup).children.count()!=0)
        {

            when((view[2] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.add(TreeNode("while"))
                    workWithWhile(tree.children.last(), (view[2] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.add(TreeNode("print"))
                    workWithPrint(tree.children.last(), (view[2] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.add(TreeNode("assign"))
                    workWithVarAssignment(tree.children.last(), (view[2] as ViewGroup)[0] as VariableBtn)
                }
            }
        }
        if((view[5] as ViewGroup).children.count()!=0){

            when((view[5] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.parent?.add(TreeNode("while"))
                    workWithWhile(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.parent?.add(TreeNode("print"))
                    workWithPrint(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.parent?.add(TreeNode("assign"))
                    workWithVarAssignment(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as VariableBtn)
                }
            }
        }
    }

    private fun workWithPrint(tree: TreeNode<String>, view: OutputBtn){

        if((view[2] as ViewGroup).children.count()!=0){
            when((view[2] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.parent?.add(TreeNode("while"))
                    workWithWhile(tree.parent?.children?.last()!!, (view[2] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.parent?.add(TreeNode("print"))
                    workWithPrint(tree.parent?.children?.last()!!, (view[2] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.parent?.add(TreeNode("assign"))
                    workWithVarAssignment(tree.parent?.children?.last()!!, (view[2] as ViewGroup)[0] as VariableBtn)
                }
            }
        }
    }

    private fun workWithVarAssignment(tree: TreeNode<String>, view: VariableBtn){

        if((view[1] as ViewGroup).children.count()!=0){

            when((view[1] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.parent?.add(TreeNode("while"))
                    workWithWhile(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.parent?.add(TreeNode("print"))
                    workWithPrint(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.parent?.add(TreeNode("assign"))
                    workWithVarAssignment(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as VariableBtn)
                }
            }
        }
    }

    private fun fillTree(start: StartBtn) {

        if((start[2] as ViewGroup).children.count() != 0){
            when((start[2] as ViewGroup)[0]){
                is WhileBtn-> {
                    myTree.add(TreeNode("while"))
                    workWithWhile(myTree.children.last(), (start[2] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    myTree.add(TreeNode("print"))
                    workWithPrint(myTree.children.last(), (start[2] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    myTree.add(TreeNode("assign"))
                    workWithVarAssignment(myTree.children.last(), (start[2] as ViewGroup)[0] as VariableBtn)
                }
            }
        }
    }


//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Блоки~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private fun blockMain(tree: TreeNode<String>) {
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

    private fun blockWhile(children: TreeNode<String>) {
        while (blockCondition(children.children[0])) {
            blockMain(children.children[1])
        }
    }

    private fun blockIf(children: TreeNode<String>) {
        if (blockCondition(children.children[0])) {
            blockMain(children.children[1])
        } else {
            blockMain(children.children[2])
        }
    }

    //НЕ ДОДЕЛАН
    private fun blockPrint(children: TreeNode<String>) {

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
    private fun blockAssign(children: TreeNode<String>) {
        if (children.children[0].value == "varInt") {
            varsIntMap[children.children[0].children[0].value] = blockIntExpression(children.children[1])
        }
    }

    //НЕ ДОДЕЛАН
    private fun blockCondition(children: TreeNode<String>): Boolean {
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
    private fun blockIntExpression(expression: TreeNode<String>): Int {
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
        fillTree(receivedRoot)

        //Вывод дерева
        myTree.printEachLevel()

        //Запуск программы
        //blockMain(myTree)

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
