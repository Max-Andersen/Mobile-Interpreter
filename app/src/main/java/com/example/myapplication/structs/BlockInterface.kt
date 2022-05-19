package com.example.myapplication.structs

import android.graphics.Color
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import com.example.myapplication.blocks.*
import kotlinx.android.synthetic.main.start_block.view.*

interface BlockInterface {
    fun dragAndDropListener(): View.OnDragListener {
        return View.OnDragListener{ view, event ->
            val dragBlock = event.localState as View
            val destination = view as ConstraintLayout
            val owner = dragBlock.parent as ViewGroup

            when (event.action){
                DragEvent.ACTION_DRAG_STARTED -> {
                    view.invalidate()
                    true
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    if(checkForLink(destination, dragBlock) && checkForChildren(destination))
                        destination.setBackgroundColor(Color.GRAY)
                    view.invalidate()
                    true
                }

                DragEvent.ACTION_DRAG_LOCATION -> {
                    true
                }

                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    destination.setBackgroundColor(Color.TRANSPARENT)
                    true
                }

                DragEvent.ACTION_DROP -> {

                    if(checkForLink(destination, dragBlock) && checkForChildren(destination)) {

                        increaseLineHeight(destination, dragBlock)

                        decreaseLineHeight(owner, dragBlock)

                        //---------------------------------------------
                        //подтягиваем drag block ровно в place for drop
                        dragBlock.x = (destination.rootView as ViewGroup)[0].x
                        dragBlock.y = (destination.rootView as ViewGroup)[0].y

                        //-------------------------
                        //устанавливаем новые связи
                        owner.removeView(dragBlock)
                        destination.addView(dragBlock)
                    }


                    destination.setBackgroundColor(Color.TRANSPARENT)

                    (event.localState as? StartBtn)?.onSet()
                    (event.localState as? WhileBtn)?.onSet()
                    (event.localState as? VariableBtn)?.onSet()
                    (event.localState as? OutputBtn)?.onSet()
                    (event.localState as? CreateVarBtn)?.onSet()

                    view.invalidate()
                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    view.invalidate()
                    true
                }

                else -> {false}
            }
        }
    }

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
}