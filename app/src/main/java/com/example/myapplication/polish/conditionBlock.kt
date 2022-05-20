package com.example.myapplication.polish

import com.example.myapplication.structs.tree.TreeNode

private fun helpWithRegExp(tree: TreeNode<String>, text: String, errorList: MutableList<String>){
    var matchResult = "[^<=!>]+".toRegex().find(text)

    if(matchResult != null)
        expressionBlock(tree.children[0], matchResult.value.trim(), errorList)
    matchResult = matchResult?.next()
    if(matchResult != null)
        expressionBlock(tree.children[0], matchResult.value.trim(), errorList)
}

fun conditionBlock(tree: TreeNode<String>, text: String, errorList: MutableList<String>){

    //лишние символы
    var matchResult = "^[a-zA-Z_0-9+\\-/*%()<!=>\\s]*$".toRegex().find(text)

    if(matchResult?.value == text){
        if(text.contains("^[^<=!>]*<[^<=!>]*$".toRegex())){
            tree.children[0].add(TreeNode("<"))
            helpWithRegExp(tree, text, errorList)
        }
        else if(text.contains("^[^<=!>]*>[^<=!>]*$".toRegex())){
            tree.children[0].add(TreeNode(">"))
            helpWithRegExp(tree, text, errorList)
        }
        else if(text.contains("^[^<=!>]*<=[^<=!>]*$".toRegex())){
            tree.children[0].add(TreeNode("<="))
            helpWithRegExp(tree, text, errorList)
        }
        else if(text.contains("^[^<=!>]*>=[^<=!>]*$".toRegex())){
            tree.children[0].add(TreeNode(">="))
            helpWithRegExp(tree, text, errorList)
        }
        else if(text.contains("^[^<=!>]*==[^<=!>]*$".toRegex())){
            tree.children[0].add(TreeNode("=="))
            helpWithRegExp(tree, text, errorList)
        }
        else if(text.contains("^[^<=!>]*!=[^<=!>]*$".toRegex())){
            tree.children[0].add(TreeNode("!="))
            helpWithRegExp(tree, text, errorList)
        }
        else
            errorList.add("Invalid condition block")
    }
    else
        errorList.add("Invalid condition block")
}