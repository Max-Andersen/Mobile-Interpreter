package com.example.myapplication.interpreter

import com.example.myapplication.polish.calculatePolishString
import com.example.myapplication.structs.tree.TreeNode


fun blockMain(tree: TreeNode<String>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>) {

    val localVarsIntMap: MutableMap<String, Int> = mutableMapOf()

    for (i in tree.children) {
        if (i.value == "while") {
            if (i.children.size == 2) {
                blockWhile(i, varsIntMap, errorList)
            } else
                errorList += "Empty while_block found"
        }
        if (i.value == "if_block") {
            if (i.children.size == 2 || i.children.size == 3) {
                blockIf(i, varsIntMap, errorList)
            } else
                errorList += "Empty if_block found"
        }
        if (i.value == "print") {
            if (i.children.size > 0) {
                blockPrint(i, varsIntMap, errorList)
            } else
                errorList += "Empty print_block found"
        }
        if (i.value == "assign") {
            if (i.children.size == 2) {
                blockAssign(i, varsIntMap, errorList)
            } else
                errorList += "Empty assign_block found"
        }
        if (i.value == "new") {
            if (i.children.size == 2) {
                blockNew(i, localVarsIntMap, varsIntMap, errorList)
            } else
                errorList += "Empty new_var block found"
        }
    }

    for(i in localVarsIntMap.keys)
        if(varsIntMap.containsKey(i))
            varsIntMap.remove(i)
}

private fun blockWhile(children: TreeNode<String>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>) {
    while (blockCondition(children.children[0], varsIntMap, errorList)) {
        blockMain(children.children[1], varsIntMap, errorList)
    }
}

private fun blockIf(children: TreeNode<String>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>) {
    if(children.children.size == 3) {
        if (blockCondition(children.children[0], varsIntMap, errorList)) {
            blockMain(children.children[1], varsIntMap, errorList)
        } else {
            blockMain(children.children[2], varsIntMap, errorList)
        }
    }
    else if(children.children.size == 2){
        if (blockCondition(children.children[0], varsIntMap, errorList)) {
            blockMain(children.children[1], varsIntMap, errorList)
        }
    }
}

private fun blockPrint(children: TreeNode<String>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>) {
    for(i in children.children){
        println(calculatePolishString(i.value, varsIntMap).toString())
    }
}

private fun blockAssign(children: TreeNode<String>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>) {
    if (varsIntMap.containsKey(children.children[0].value)) {
        varsIntMap[children.children[0].value] = calculatePolishString(children.children[1].value, varsIntMap)
    }
    else
        errorList.add("Unknown var name in assignment")
}

private fun blockNew(children: TreeNode<String>, localVarsIntMap: MutableMap<String, Int>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>) {
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

private fun blockCondition(children: TreeNode<String>, varsIntMap: MutableMap<String, Int>, errorList: MutableList<String>): Boolean {

    var res = false

    if (children.children.count() == 3) {
        if (children.children[0].value == "!=") {
            if (calculatePolishString(children.children[1].value, varsIntMap) != calculatePolishString(children.children[2].value, varsIntMap)) {
                res = true
            }
        }
        if (children.children[0].value == "==") {
            if (calculatePolishString(children.children[1].value, varsIntMap) == calculatePolishString(children.children[2].value, varsIntMap)) {
                res = true
            }
        }
        if (children.children[0].value == ">=") {
            if (calculatePolishString(children.children[1].value, varsIntMap) >= calculatePolishString(children.children[2].value, varsIntMap)) {
                res = true
            }
        }
        if (children.children[0].value == "<=") {
            if (calculatePolishString(children.children[1].value, varsIntMap) <= calculatePolishString(children.children[2].value, varsIntMap)) {
                res = true
            }
        }
        if (children.children[0].value == "<") {
            if (calculatePolishString(children.children[1].value, varsIntMap) < calculatePolishString(children.children[2].value, varsIntMap)) {
                res = true
            }
        }
        if (children.children[0].value == ">") {
            if (calculatePolishString(children.children[1].value, varsIntMap) > calculatePolishString(children.children[2].value, varsIntMap)) {
                res = true
            }
        }
    }
    else
        errorList.add("Invalid condition block")

    return res
}
