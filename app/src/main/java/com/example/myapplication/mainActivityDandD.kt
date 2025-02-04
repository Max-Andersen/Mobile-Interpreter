package com.example.myapplication

import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.example.myapplication.blocks.*
import com.otaliastudios.zoom.ZoomLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

//-----------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DRAG_AND_DROP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

fun mainActivityDandD(
    zoomLayout: ZoomLayout,
    consoleScroll: ScrollView,
    blockAndVariable: ConstraintLayout,
    plus1: ImageButton,
    consoleBtn: ImageButton,
    Workspace: ConstraintLayout,
    burgerOpen: ImageButton
): View.OnDragListener {
    return View.OnDragListener { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                for (i in 0..zoomLayout.Workspace.childCount) {
                    val child = zoomLayout.Workspace.getChildAt(i)
                    if (child is View) {
                        child.visibility = View.VISIBLE
                    }
                }
                consoleScroll.visibility = View.GONE
                blockAndVariable.visibility = View.GONE
                plus1.visibility = View.VISIBLE
                consoleBtn.visibility = View.VISIBLE
                burgerOpen.visibility = View.VISIBLE
                zoomLayout.setVerticalPanEnabled(true)
                zoomLayout.setHorizontalPanEnabled(true)
                zoomLayout.setZoomEnabled(true)
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }

            DragEvent.ACTION_DROP -> {

                val v = event.localState as ConstraintLayout
                v.x = event.x - (v.width / 2)
                v.y = event.y - (v.height / 2)

                val owner = v.parent as ViewGroup
                owner.removeView(v)
                Workspace.addView(v)


                //---------------------------------
                //---------------------------------
                //УМЕНЬШЕНИЕ ПОЛОСКИ ВЛОЖЕННОСТИ!!!

                var x = owner.parent as View
                var oldX = owner as ConstraintLayout

                while (true) {
                    if (x is WhileBtn) {
                        x[4].layoutParams.height -= v.height - x[0].layoutParams.height / 2
                        oldX = oldX.parent.parent as ConstraintLayout
                        x = x.parent.parent as View
                    } else if (x is IfBtn) {
                        x[4].layoutParams.height -= v.height - x[0].layoutParams.height / 2
                        oldX = oldX.parent.parent as ConstraintLayout
                        x = x.parent.parent as View
                    } else if (x is IfElseBtn) {
                        if (oldX == x[2] as ConstraintLayout)
                            x[5].layoutParams.height -= v.height - x[0].layoutParams.height / 2
                        else
                            x[7].layoutParams.height -= v.height - x[0].layoutParams.height / 2
                        oldX = oldX.parent.parent as ConstraintLayout
                        x = x.parent.parent as View
                    } else if (x is VariableBtn || x is OutputBtn || x is StartBtn || x is CreateVarBtn) {
                        oldX = oldX.parent.parent as ConstraintLayout
                        x = x.parent.parent as View
                    } else {
                        break
                    }
                }

                // ставим обработчик на вложенный place for drop:
                (event.localState as? StartBtn)?.onSet()
                (event.localState as? WhileBtn)?.onSet()
                (event.localState as? VariableBtn)?.onSet()
                (event.localState as? OutputBtn)?.onSet()
                (event.localState as? CreateVarBtn)?.onSet()
                (event.localState as? IfBtn)?.onSet()
                (event.localState as? IfElseBtn)?.onSet()

                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }

            else -> {
                false
            }
        }
    }
}