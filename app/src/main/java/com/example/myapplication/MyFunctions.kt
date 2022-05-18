package com.example.myapplication

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.myapplication.blocks.OutputBtn
import com.example.myapplication.blocks.StartBtn
import com.example.myapplication.blocks.VariableBtn
import com.example.myapplication.blocks.WhileBtn

fun checkForLink(destination: ConstraintLayout, dragBlock: View): Boolean{

    //-------------------------------------------
    //ПРОВЕРКА: НЕ ЛЕЖИТ ЛИ destination под owner

    var checkView = destination.parent as View
    var flag = true

    //println(dragBlock)

    while(true){
        if(checkView is WhileBtn || checkView is VariableBtn || checkView is OutputBtn || checkView is StartBtn){
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