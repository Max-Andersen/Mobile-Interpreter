package com.example.myapplication

import android.content.Context
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
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

        tree.add(TreeNode("condition"))
        tree.add(TreeNode("body"))

        if((view[2] as ViewGroup).children.count()!=0)
        {
            when((view[2] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.children[1].add(TreeNode("while"))
                    workWithWhile(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.children[1].add(TreeNode("print"))
                    workWithPrint(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.children[1].add(TreeNode("assign"))
                    workWithVarAssignment(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as VariableBtn)
                }
                is CreateVarBtn->{
                    tree.children[1].add(TreeNode("new"))
                    workWithNewVar(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.children[1].add(TreeNode("if_block"))
                    workWithIf(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.children[1].add(TreeNode("if_block"))
                    workWithIfElse(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as IfElseBtn)
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
                is CreateVarBtn->{
                    tree.parent?.add(TreeNode("new"))
                    workWithNewVar(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIf(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIfElse(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
    }

    private fun workWithPrint(tree: TreeNode<String>, view: OutputBtn){


        //-----------------------------------------------------
        //-----------------------------------------------------
        //-----------------------------------------------------


        //ВНИМАНИЕ!!!!!!!!!!!! ТЕСТОВЫЙ ОБРАЗЕЦ!!!!
        tree.add(TreeNode("varInt"))
        tree.children[0].add(TreeNode((view[3] as EditText).text.toString()))

        //-----------------------------------------------------
        //-----------------------------------------------------
        //-----------------------------------------------------

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
                is CreateVarBtn->{
                    tree.parent?.add(TreeNode("new"))
                    workWithNewVar(tree.parent?.children?.last()!!, (view[2] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIf(tree.parent?.children?.last()!!, (view[2] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIfElse(tree.parent?.children?.last()!!, (view[2] as ViewGroup)[0] as IfElseBtn)
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
                is CreateVarBtn->{
                    tree.parent?.add(TreeNode("new"))
                    workWithNewVar(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIf(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIfElse(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
    }

    private fun workWithNewVar(tree: TreeNode<String>, view: CreateVarBtn){

        if((view[3] as Spinner).selectedItem.toString() == "Int"){
            tree.add(TreeNode("int"))
            tree.add(TreeNode("children"))

            var matchResult = "[a-zA-Z_0-9\\s,]*".toRegex().find((view[4] as EditText).text.toString())

            if(matchResult?.value.toString() == (view[4] as EditText).text.toString()
                && !(view[4] as EditText).text.toString().contains(",\\s*,".toRegex())
                && !(view[4] as EditText).text.toString().contains("^\\s*[,0-9]".toRegex())
                && !(view[4] as EditText).text.toString().contains(",\\s*$".toRegex())
                && !(view[4] as EditText).text.toString().contains(",\\s*[0-9]".toRegex())
                && !(view[4] as EditText).text.toString().contains("[a-zA-Z0-9_]\\s+[a-zA-Z0-9_]".toRegex())){
                val str = (view[4] as EditText).text.toString().replace("\\s\\s".toRegex(), " ")

                matchResult = "[a-zA-Z_][a-zA-Z0-9_]*".toRegex().find(str)

                while(matchResult != null){

                    tree.children[1].add(TreeNode(matchResult.value.toString()))

                    matchResult = matchResult.next()
                }
            }
            else
                errorList.add("Invalid variable name")
        }

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
                is CreateVarBtn->{
                    tree.parent?.add(TreeNode("new"))
                    workWithNewVar(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIf(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIfElse(tree.parent?.children?.last()!!, (view[1] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
    }

    private fun workWithIf(tree: TreeNode<String>, view: IfBtn){

        tree.add(TreeNode("condition"))
        tree.add(TreeNode("true_body"))


        if((view[2] as ViewGroup).children.count()!=0)
        {
            when((view[2] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.children[1].add(TreeNode("while"))
                    workWithWhile(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.children[1].add(TreeNode("print"))
                    workWithPrint(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.children[1].add(TreeNode("assign"))
                    workWithVarAssignment(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as VariableBtn)
                }
                is CreateVarBtn->{
                    tree.children[1].add(TreeNode("new"))
                    workWithNewVar(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.children[1].add(TreeNode("if_block"))
                    workWithIf(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.children[1].add(TreeNode("if_block"))
                    workWithIfElse(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as IfElseBtn)
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
                is CreateVarBtn->{
                    tree.parent?.add(TreeNode("new"))
                    workWithNewVar(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIf(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIfElse(tree.parent?.children?.last()!!, (view[5] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
    }

    private fun workWithIfElse(tree: TreeNode<String>, view: IfElseBtn){

        tree.add(TreeNode("condition"))
        tree.add(TreeNode("true_body"))
        tree.add(TreeNode("false_body"))


        if((view[2] as ViewGroup).children.count()!=0)
        {
            when((view[2] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.children[1].add(TreeNode("while"))
                    workWithWhile(tree.children.last(), (view[2] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.children[1].add(TreeNode("print"))
                    workWithPrint(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.children[1].add(TreeNode("assign"))
                    workWithVarAssignment(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as VariableBtn)
                }
                is CreateVarBtn->{
                    tree.children[1].add(TreeNode("new"))
                    workWithNewVar(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.children[1].add(TreeNode("if_block"))
                    workWithIf(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.children[1].add(TreeNode("if_block"))
                    workWithIfElse(tree.children[1].children.last(), (view[2] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
        if((view[3] as ViewGroup).children.count()!=0)
        {
            when((view[3] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.children[2].add(TreeNode("while"))
                    workWithWhile(tree.children.last(), (view[3] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.children[2].add(TreeNode("print"))
                    workWithPrint(tree.children[2].children.last(), (view[3] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.children[2].add(TreeNode("assign"))
                    workWithVarAssignment(tree.children[2].children.last(), (view[3] as ViewGroup)[0] as VariableBtn)
                }
                is CreateVarBtn->{
                    tree.children[2].add(TreeNode("new"))
                    workWithNewVar(tree.children[2].children.last(), (view[3] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.children[2].add(TreeNode("if_block"))
                    workWithIf(tree.children[2].children.last(), (view[3] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.children[2].add(TreeNode("if_block"))
                    workWithIfElse(tree.children[2].children.last(), (view[3] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
        if((view[10] as ViewGroup).children.count()!=0){

            when((view[10] as ViewGroup)[0]){
                is WhileBtn-> {
                    tree.parent?.add(TreeNode("while"))
                    workWithWhile(tree.parent?.children?.last()!!, (view[10] as ViewGroup)[0] as WhileBtn)
                }
                is OutputBtn->{
                    tree.parent?.add(TreeNode("print"))
                    workWithPrint(tree.parent?.children?.last()!!, (view[10] as ViewGroup)[0] as OutputBtn)
                }
                is VariableBtn->{
                    tree.parent?.add(TreeNode("assign"))
                    workWithVarAssignment(tree.parent?.children?.last()!!, (view[10] as ViewGroup)[0] as VariableBtn)
                }
                is CreateVarBtn->{
                    tree.parent?.add(TreeNode("new"))
                    workWithNewVar(tree.parent?.children?.last()!!, (view[10] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIf(tree.parent?.children?.last()!!, (view[10] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    tree.parent?.add(TreeNode("if_block"))
                    workWithIfElse(tree.parent?.children?.last()!!, (view[10] as ViewGroup)[0] as IfElseBtn)
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
                is CreateVarBtn->{
                    myTree.add(TreeNode("new"))
                    workWithNewVar(myTree.children.last(), (start[2] as ViewGroup)[0] as CreateVarBtn)
                }
                is IfBtn->{
                    myTree.add(TreeNode("if_block"))
                    workWithIf(myTree.children.last(), (start[2] as ViewGroup)[0] as IfBtn)
                }
                is IfElseBtn->{
                    myTree.add(TreeNode("if_block"))
                    workWithIfElse(myTree.children.last(), (start[2] as ViewGroup)[0] as IfElseBtn)
                }
            }
        }
    }


//-----------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Блоки~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private fun blockMain(tree: TreeNode<String>) {

        val localVarsIntMap: MutableMap<String, Int> = mutableMapOf()

        for (i in tree.children) {
            if (i.value == "while") {
                if (i.children.size == 2) {
                    blockWhile(i)
                } else
                    errorList += "Empty while_block found"
            }
            if (i.value == "if_block") {
                if (i.children.size == 2 || i.children.size == 3) {
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
            if (i.value == "new") {
                if (i.children.size == 2) {
                    blockNew(i, localVarsIntMap)
                } else
                    errorList += "Empty new_var block found"
            }
        }

        for(i in localVarsIntMap.keys)
            if(varsIntMap.containsKey(i))
                varsIntMap.remove(i)
    }

    private fun blockWhile(children: TreeNode<String>) {
        while (blockCondition(children.children[0])) {
            blockMain(children.children[1])
        }
    }

    private fun blockIf(children: TreeNode<String>) {
        if(children.children.size == 3) {
            if (blockCondition(children.children[0])) {
                blockMain(children.children[1])
            } else {
                blockMain(children.children[2])
            }
        }
        else if(children.children.size == 2){
            if (blockCondition(children.children[0])) {
                blockMain(children.children[1])
            }
        }
    }

    //НЕ ДОДЕЛАН
    private fun blockPrint(children: TreeNode<String>) {

        when (children.value) {
            "varInt" -> {
                if(varsIntMap.containsKey(children.children[0].value))
                    outputList += varsIntMap[children.children[0].value].toString()
                else
                    errorList.add("Unknown var name in output")
            }
            "..." -> {

            }
            else -> errorList += "Unknown var type in output"
        }

    }

    //НЕ ДОДЕЛАН
    private fun blockAssign(children: TreeNode<String>) {
        if (children.children[0].value == "varInt") {
            varsIntMap[children.children[0].children[0].value] = blockIntExpression(children.children[1])
        }
        else
            errorList.add("Unknown var type in var creation")
    }


    private fun blockNew(children: TreeNode<String>, localVarsIntMap: MutableMap<String, Int>) {
        if (children.children[0].value == "int") {
            for(i in children.children[1].children){
                if(varsIntMap.containsKey(i.value)){
                    errorList.add("Var with name \"${i.value}\" already exists")
                }
                else{
                    varsIntMap[i.value] = 0
                    localVarsIntMap[i.value] = 0
                }
            }
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
