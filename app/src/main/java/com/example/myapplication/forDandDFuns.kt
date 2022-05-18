package com.example.myapplication

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import com.example.myapplication.blocks.*

fun checkForLink(destination: ConstraintLayout, dragBlock: View): Boolean{

    //-------------------------------------------
    //ПРОВЕРКА: НЕ ЛЕЖИТ ЛИ destination под owner

    var checkView = destination.parent as View
    var flag = true

    //println(dragBlock)

    while(true){
        if(checkView is WhileBtn || checkView is VariableBtn || checkView is OutputBtn || checkView is StartBtn || checkView is CreateVarBtn){
            if(checkView == dragBlock){
                flag = false
                break
            }
            //println(checkView)
            checkView = checkView.parent.parent as View
        }
        else{
            break
        }
    }

    return flag
}

fun checkForChildren(destination: ConstraintLayout): Boolean{
    if(destination.children.count() == 0)
        return true
    return false
}

fun increaseLineHeight(destination: ConstraintLayout, dragBlock: View){

    //---------------------------------
    //УВЕЛИЧЕНИЕ ПОЛОСКИ ВЛОЖЕННОСТИ!!!

    var x = destination.parent as View

    while (true) {
        if (x is WhileBtn) {
            x[4].layoutParams.height += dragBlock.height - x[0].layoutParams.height / 2
            x = x.parent.parent as View
        } else if (x is VariableBtn || x is OutputBtn || x is StartBtn || x is CreateVarBtn) {
            x = x.parent.parent as View
        } else {
            break
        }
    }

}

fun decreaseLineHeight(owner: ViewGroup, dragBlock: View){

    //---------------------------------
    //УМЕНЬШЕНИЕ ПОЛОСКИ ВЛОЖЕННОСТИ!!!

    var x = owner.parent as View

    while (true) {
        if (x is WhileBtn) {
            x[4].layoutParams.height -= dragBlock.height - x[0].layoutParams.height / 2
            x = x.parent.parent as View
        } else if (x is VariableBtn || x is OutputBtn || x is StartBtn || x is CreateVarBtn) {
            x = x.parent.parent as View
        } else {
            break
        }
    }
}
