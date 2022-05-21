package com.example.myapplication.interpreter

import android.content.Context
import android.widget.LinearLayout
import com.example.myapplication.polish.calculatePolishString
import com.example.myapplication.printInConsole
import com.example.myapplication.structs.tree.TreeNode


fun blockMain(
    tree: TreeNode<String>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
) {

    val localVarsIntMap: MutableMap<String, Int> = mutableMapOf()

    for (i in tree.children) {
        if (i.value == "while") {
            if (i.children.size == 2) {
                blockWhile(i, varsIntMap, console, ctx)
            } else
                printInConsole("#Empty while_block found", console, ctx)
        }
        if (i.value == "if_block") {
            if (i.children.size == 2 || i.children.size == 3) {
                blockIf(i, varsIntMap, console, ctx)
            } else
                printInConsole("#Empty if_block found", console, ctx)
        }
        if (i.value == "print") {
            if (i.children.size > 0) {
                blockPrint(i, varsIntMap, console, ctx)
            } else
                printInConsole("#Empty print_block found", console, ctx)
        }
        if (i.value == "assign") {
            if (i.children.size == 2) {
                blockAssign(i, varsIntMap, console, ctx)
            } else
                printInConsole("#Empty assign_block found", console, ctx)
        }
        if (i.value == "new") {
            if (i.children.size == 2) {
                blockNew(i, localVarsIntMap, varsIntMap, console, ctx)
            } else
                printInConsole("#Empty new_var block found", console, ctx)
        }
    }

    for (i in localVarsIntMap.keys)
        if (varsIntMap.containsKey(i))
            varsIntMap.remove(i)
}

private fun blockWhile(
    children: TreeNode<String>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
) {
    while (blockCondition(children.children[0], varsIntMap, console, ctx)) {
        blockMain(children.children[1], varsIntMap, console, ctx)
    }
}

private fun blockIf(
    children: TreeNode<String>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
) {
    if (children.children.size == 3) {
        if (blockCondition(children.children[0], varsIntMap, console, ctx)) {
            blockMain(children.children[1], varsIntMap, console, ctx)
        } else {
            blockMain(children.children[2], varsIntMap, console, ctx)
        }
    } else if (children.children.size == 2) {
        if (blockCondition(children.children[0], varsIntMap, console, ctx)) {
            blockMain(children.children[1], varsIntMap, console, ctx)
        }
    }
}

private fun blockPrint(
    children: TreeNode<String>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
) {
    for (i in children.children) {
        printInConsole(calculatePolishString(i.value, varsIntMap, console, ctx).toString(), console, ctx)
    }
}

private fun blockAssign(
    children: TreeNode<String>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
) {
    if (varsIntMap.containsKey(children.children[0].value)) {
        varsIntMap[children.children[0].value] =
            calculatePolishString(children.children[1].value, varsIntMap, console, ctx)
    } else
        printInConsole("#Unknown var name in assignment", console, ctx)
}

private fun blockNew(
    children: TreeNode<String>,
    localVarsIntMap: MutableMap<String, Int>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
) {
    if (children.children[0].value == "int") {
        for (i in children.children[1].children) {
            if (varsIntMap.containsKey(i.value)) {
                printInConsole("#Var with name \"${i.value}\" already exists", console, ctx)
            } else {
                varsIntMap[i.value] = 0
                localVarsIntMap[i.value] = 0
            }
        }
    }
}

private fun blockCondition(
    children: TreeNode<String>,
    varsIntMap: MutableMap<String, Int>,
    console: LinearLayout,
    ctx: Context
): Boolean {

    var res = false

    if (children.children.count() == 3) {
        if (children.children[0].value == "!=") {
            if (calculatePolishString(
                    children.children[1].value,
                    varsIntMap,
                    console,
                    ctx
                ) != calculatePolishString(children.children[2].value, varsIntMap, console, ctx)
            ) {
                res = true
            }
        }
        if (children.children[0].value == "==") {
            if (calculatePolishString(
                    children.children[1].value,
                    varsIntMap,
                    console,
                    ctx
                ) == calculatePolishString(children.children[2].value, varsIntMap, console, ctx)
            ) {
                res = true
            }
        }
        if (children.children[0].value == ">=") {
            if (calculatePolishString(
                    children.children[1].value,
                    varsIntMap,
                    console,
                    ctx
                ) >= calculatePolishString(children.children[2].value, varsIntMap, console, ctx)
            ) {
                res = true
            }
        }
        if (children.children[0].value == "<=") {
            if (calculatePolishString(
                    children.children[1].value,
                    varsIntMap,
                    console,
                    ctx
                ) <= calculatePolishString(children.children[2].value, varsIntMap, console, ctx)
            ) {
                res = true
            }
        }
        if (children.children[0].value == "<") {
            if (calculatePolishString(
                    children.children[1].value,
                    varsIntMap,
                    console,
                    ctx
                ) < calculatePolishString(children.children[2].value, varsIntMap, console, ctx)
            ) {
                res = true
            }
        }
        if (children.children[0].value == ">") {
            if (calculatePolishString(
                    children.children[1].value,
                    varsIntMap,
                    console,
                    ctx
                ) > calculatePolishString(children.children[2].value, varsIntMap, console, ctx)
            ) {
                res = true
            }
        }
    } else
        printInConsole("#Invalid condition block", console, ctx)

    return res
}
