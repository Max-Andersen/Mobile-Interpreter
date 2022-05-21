package com.example.myapplication.polish

import android.content.Context
import android.widget.LinearLayout
import com.example.myapplication.printInConsole
import com.example.myapplication.structs.tree.TreeNode

private fun helpWithRegExp(
    tree: TreeNode<String>,
    text: String,
    console: LinearLayout,
    ctx: Context,
    variables: MutableMap<String, Int>
) {
    var matchResult = "[^<=!>]+".toRegex().find(text)

    if (matchResult != null)
        expressionBlock(tree.children[0], matchResult.value.trim(), console, ctx, variables)
    matchResult = matchResult?.next()
    if (matchResult != null)
        expressionBlock(tree.children[0], matchResult.value.trim(), console, ctx, variables)
}

fun conditionBlock(tree: TreeNode<String>, text: String, console: LinearLayout, ctx: Context, variables: MutableMap<String, Int>) {

    //лишние символы
    val matchResult = "^[a-zA-Z_0-9+\\-/*%()<!=>\\s]*$".toRegex().find(text)

    if (matchResult?.value == text) {
        if (text.contains("^[^<=!>]*<[^<=!>]*$".toRegex())) {
            tree.children[0].add(TreeNode("<"))
            helpWithRegExp(tree, text, console, ctx, variables)
        } else if (text.contains("^[^<=!>]*>[^<=!>]*$".toRegex())) {
            tree.children[0].add(TreeNode(">"))
            helpWithRegExp(tree, text, console, ctx, variables)
        } else if (text.contains("^[^<=!>]*<=[^<=!>]*$".toRegex())) {
            tree.children[0].add(TreeNode("<="))
            helpWithRegExp(tree, text, console, ctx, variables)
        } else if (text.contains("^[^<=!>]*>=[^<=!>]*$".toRegex())) {
            tree.children[0].add(TreeNode(">="))
            helpWithRegExp(tree, text, console, ctx, variables)
        } else if (text.contains("^[^<=!>]*==[^<=!>]*$".toRegex())) {
            tree.children[0].add(TreeNode("=="))
            helpWithRegExp(tree, text, console, ctx, variables)
        } else if (text.contains("^[^<=!>]*!=[^<=!>]*$".toRegex())) {
            tree.children[0].add(TreeNode("!="))
            helpWithRegExp(tree, text, console, ctx, variables)
        } else
            printInConsole("#Invalid condition block", console, ctx)
    } else
        printInConsole("#Invalid condition block", console, ctx)
}