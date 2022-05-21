package com.example.myapplication.interpreter

import android.content.Context
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.core.view.children
import androidx.core.view.get
import com.example.myapplication.blocks.*
import com.example.myapplication.polish.conditionBlock
import com.example.myapplication.polish.expressionBlock
import com.example.myapplication.structs.tree.TreeNode
import com.example.myapplication.printInConsole


private fun workWithWhile(
    tree: TreeNode<String>,
    view: WhileBtn,
    console: LinearLayout,
    ctx: Context,
    variables: MutableMap<String, Int>
) {

    tree.add(TreeNode("condition"))
    tree.add(TreeNode("body"))

    val text = (view[3] as EditText).text.toString()
    conditionBlock(tree, text, console, ctx, variables)

    if ((view[2] as ViewGroup).children.count() != 0) {
        when ((view[2] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.children[1].add(TreeNode("while"))
                workWithWhile(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.children[1].add(TreeNode("print"))
                workWithPrint(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.children[1].add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.children[1].add(TreeNode("new"))
                workWithNewVar(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.children[1].add(TreeNode("if_block"))
                workWithIf(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.children[1].add(TreeNode("if_block"))
                workWithIfElse(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
    if ((view[5] as ViewGroup).children.count() != 0) {

        when ((view[5] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.parent?.add(TreeNode("while"))
                workWithWhile(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.parent?.add(TreeNode("print"))
                workWithPrint(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.parent?.add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.parent?.add(TreeNode("new"))
                workWithNewVar(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIf(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIfElse(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}

private fun workWithPrint(
    tree: TreeNode<String>,
    view: OutputBtn,
    console: LinearLayout,
    ctx: Context,
    variables: MutableMap<String, Int>
) {

    val expression = (view[3] as EditText).text.toString()

    //лишние символы
    var matchResult = "^[a-zA-Z_0-9+\\-/*%(),\\s]*$".toRegex().find(expression)

    if (matchResult?.value.toString() == expression
        && !expression.contains(",\\s*,".toRegex())
        && !expression.contains(",\\s*$".toRegex())
        && !expression.contains("^\\s*,".toRegex())
    ) {
        matchResult = "[^,]+".toRegex().find(expression)

        while (matchResult != null) {

            if(matchResult.value.trim()!="")
                expressionBlock(tree, matchResult.value.trim(), console, ctx,variables)

            matchResult = matchResult.next()
        }

    }

    if ((view[2] as ViewGroup).children.count() != 0) {
        when ((view[2] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.parent?.add(TreeNode("while"))
                workWithWhile(
                    tree.parent?.children?.last()!!,
                    (view[2] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.parent?.add(TreeNode("print"))
                workWithPrint(
                    tree.parent?.children?.last()!!,
                    (view[2] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.parent?.add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.parent?.children?.last()!!,
                    (view[2] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.parent?.add(TreeNode("new"))
                workWithNewVar(
                    tree.parent?.children?.last()!!,
                    (view[2] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIf(
                    tree.parent?.children?.last()!!,
                    (view[2] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIfElse(
                    tree.parent?.children?.last()!!,
                    (view[2] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}


private fun workWithVarAssignment(
    tree: TreeNode<String>,
    view: VariableBtn,
    console: LinearLayout,
    ctx: Context,
    variables: MutableMap<String, Int>
) {

    val textVar = (view[3] as EditText).text.toString()
    val textExpression = (view[2] as EditText).text.toString()

    tree.add(TreeNode(textVar.trim()))
    if(textExpression.trim()!="")
        expressionBlock(tree, textExpression, console, ctx,variables)


    if ((view[1] as ViewGroup).children.count() != 0) {

        when ((view[1] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.parent?.add(TreeNode("while"))
                workWithWhile(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.parent?.add(TreeNode("print"))
                workWithPrint(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.parent?.add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.parent?.add(TreeNode("new"))
                workWithNewVar(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIf(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIfElse(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}

private fun workWithNewVar(
    tree: TreeNode<String>,
    view: CreateVarBtn,
    console: LinearLayout,
    ctx: Context,
    variables: MutableMap<String, Int>
) {

    if ((view[3] as Spinner).selectedItem.toString() == "Int") {
        tree.add(TreeNode("int"))
        tree.add(TreeNode("children"))

        var matchResult = "[a-zA-Z_0-9\\s,]*".toRegex().find((view[4] as EditText).text.toString())

        if (matchResult?.value.toString() == (view[4] as EditText).text.toString()
            && !(view[4] as EditText).text.toString().contains(",\\s*,".toRegex())
            && !(view[4] as EditText).text.toString().contains("^\\s*[,0-9]".toRegex())
            && !(view[4] as EditText).text.toString().contains(",\\s*$".toRegex())
            && !(view[4] as EditText).text.toString().contains(",\\s*[0-9]".toRegex())
            && !(view[4] as EditText).text.toString()
                .contains("[a-zA-Z0-9_]\\s+[a-zA-Z0-9_]".toRegex())
        ) {
            val str = (view[4] as EditText).text.toString().replace("\\s\\s".toRegex(), " ")

            matchResult = "[a-zA-Z_][a-zA-Z0-9_]*".toRegex().find(str)

            while (matchResult != null) {

                if(matchResult.value.trim()!="")
                    tree.children[1].add(TreeNode(matchResult.value))
                else
                    printInConsole("#Invalid variable name", console, ctx)

                matchResult = matchResult.next()
            }
        } else
            printInConsole("#Invalid variable name", console, ctx)
    }

    if ((view[1] as ViewGroup).children.count() != 0) {

        when ((view[1] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.parent?.add(TreeNode("while"))
                workWithWhile(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.parent?.add(TreeNode("print"))
                workWithPrint(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.parent?.add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.parent?.add(TreeNode("new"))
                workWithNewVar(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIf(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIfElse(
                    tree.parent?.children?.last()!!,
                    (view[1] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}

private fun workWithIf(tree: TreeNode<String>, view: IfBtn, console: LinearLayout, ctx: Context, variables: MutableMap<String, Int>) {

    tree.add(TreeNode("condition"))
    tree.add(TreeNode("true_body"))


    val text = (view[3] as EditText).text.toString()
    conditionBlock(tree, text, console, ctx,variables)


    if ((view[2] as ViewGroup).children.count() != 0) {
        when ((view[2] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.children[1].add(TreeNode("while"))
                workWithWhile(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.children[1].add(TreeNode("print"))
                workWithPrint(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.children[1].add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.children[1].add(TreeNode("new"))
                workWithNewVar(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.children[1].add(TreeNode("if_block"))
                workWithIf(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.children[1].add(TreeNode("if_block"))
                workWithIfElse(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
    if ((view[5] as ViewGroup).children.count() != 0) {

        when ((view[5] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.parent?.add(TreeNode("while"))
                workWithWhile(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.parent?.add(TreeNode("print"))
                workWithPrint(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.parent?.add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.parent?.add(TreeNode("new"))
                workWithNewVar(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIf(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIfElse(
                    tree.parent?.children?.last()!!,
                    (view[5] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}

private fun workWithIfElse(
    tree: TreeNode<String>,
    view: IfElseBtn,
    console: LinearLayout,
    ctx: Context,
    variables: MutableMap<String, Int>
) {

    tree.add(TreeNode("condition"))
    tree.add(TreeNode("true_body"))
    tree.add(TreeNode("false_body"))

    val text = (view[4] as EditText).text.toString()
    conditionBlock(tree, text, console, ctx,variables)


    if ((view[2] as ViewGroup).children.count() != 0) {
        when ((view[2] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.children[1].add(TreeNode("while"))
                workWithWhile(
                    tree.children.last(),
                    (view[2] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.children[1].add(TreeNode("print"))
                workWithPrint(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.children[1].add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.children[1].add(TreeNode("new"))
                workWithNewVar(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.children[1].add(TreeNode("if_block"))
                workWithIf(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.children[1].add(TreeNode("if_block"))
                workWithIfElse(
                    tree.children[1].children.last(),
                    (view[2] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
    if ((view[3] as ViewGroup).children.count() != 0) {
        when ((view[3] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.children[2].add(TreeNode("while"))
                workWithWhile(
                    tree.children.last(),
                    (view[3] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.children[2].add(TreeNode("print"))
                workWithPrint(
                    tree.children[2].children.last(),
                    (view[3] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.children[2].add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.children[2].children.last(),
                    (view[3] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.children[2].add(TreeNode("new"))
                workWithNewVar(
                    tree.children[2].children.last(),
                    (view[3] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.children[2].add(TreeNode("if_block"))
                workWithIf(
                    tree.children[2].children.last(),
                    (view[3] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.children[2].add(TreeNode("if_block"))
                workWithIfElse(
                    tree.children[2].children.last(),
                    (view[3] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
    if ((view[10] as ViewGroup).children.count() != 0) {

        when ((view[10] as ViewGroup)[0]) {
            is WhileBtn -> {
                tree.parent?.add(TreeNode("while"))
                workWithWhile(
                    tree.parent?.children?.last()!!,
                    (view[10] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                tree.parent?.add(TreeNode("print"))
                workWithPrint(
                    tree.parent?.children?.last()!!,
                    (view[10] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                tree.parent?.add(TreeNode("assign"))
                workWithVarAssignment(
                    tree.parent?.children?.last()!!,
                    (view[10] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                tree.parent?.add(TreeNode("new"))
                workWithNewVar(
                    tree.parent?.children?.last()!!,
                    (view[10] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIf(
                    tree.parent?.children?.last()!!,
                    (view[10] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                tree.parent?.add(TreeNode("if_block"))
                workWithIfElse(
                    tree.parent?.children?.last()!!,
                    (view[10] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}

fun fillTree(start: StartBtn, myTree: TreeNode<String>, console: LinearLayout, ctx: Context, variables: MutableMap<String, Int>) {

    if ((start[2] as ViewGroup).children.count() != 0) {
        when ((start[2] as ViewGroup)[0]) {
            is WhileBtn -> {
                myTree.add(TreeNode("while"))
                workWithWhile(
                    myTree.children.last(),
                    (start[2] as ViewGroup)[0] as WhileBtn,
                    console,
                    ctx,variables
                )
            }
            is OutputBtn -> {
                myTree.add(TreeNode("print"))
                workWithPrint(
                    myTree.children.last(),
                    (start[2] as ViewGroup)[0] as OutputBtn,
                    console,
                    ctx,variables
                )
            }
            is VariableBtn -> {
                myTree.add(TreeNode("assign"))
                workWithVarAssignment(
                    myTree.children.last(),
                    (start[2] as ViewGroup)[0] as VariableBtn,
                    console,
                    ctx,variables
                )
            }
            is CreateVarBtn -> {
                myTree.add(TreeNode("new"))
                workWithNewVar(
                    myTree.children.last(),
                    (start[2] as ViewGroup)[0] as CreateVarBtn,
                    console,
                    ctx,variables
                )
            }
            is IfBtn -> {
                myTree.add(TreeNode("if_block"))
                workWithIf(
                    myTree.children.last(),
                    (start[2] as ViewGroup)[0] as IfBtn,
                    console,
                    ctx,variables
                )
            }
            is IfElseBtn -> {
                myTree.add(TreeNode("if_block"))
                workWithIfElse(
                    myTree.children.last(),
                    (start[2] as ViewGroup)[0] as IfElseBtn,
                    console,
                    ctx,variables
                )
            }
        }
    }
}