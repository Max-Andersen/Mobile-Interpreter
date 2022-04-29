package com.example.myapplication

import android.content.ClipData
import android.content.ClipDescription
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plus1.setOnClickListener() {
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){    // TODO: поменять на customView
                    child.visibility = View.INVISIBLE
                }
            }

            blockscreen.visibility = View.VISIBLE
            plus1.visibility = View.INVISIBLE
        }

        closeBlockScreen.setOnClickListener(){
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){   // TODO: поменять на customView
                    child.visibility = View.VISIBLE
                }
            }

            blockscreen.visibility = View.INVISIBLE
            plus1.visibility = View.VISIBLE
        }

        Workspace.setOnDragListener(dragAndDropListener)


        startNode.setOnLongClickListener {
            val textOnBoard = "This is Start Node"
            val item = ClipData.Item(textOnBoard)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(textOnBoard, mimeTypes, item)

            val dragAndDropBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragAndDropBuilder, it, 0)

            true
        }

        startNode1.setOnLongClickListener {
            val textOnBoard = "This is Start Node"
            val item = ClipData.Item(textOnBoard)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(textOnBoard, mimeTypes, item)

            val dragAndDropBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragAndDropBuilder, it, 0)
            true
        }

        startNode2.setOnLongClickListener {
            val textOnBoard = "This is Start Node"
            val item = ClipData.Item(textOnBoard)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(textOnBoard, mimeTypes, item)

            val dragAndDropBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragAndDropBuilder, it, 0)
            true
        }


    }

    val dragAndDropListener = View.OnDragListener{ view, event ->
        when (event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
                for (i in 0..Workspace.childCount){
                    val child = Workspace.getChildAt(i)
                    if (child is View){   // TODO: поменять на customView
                        child.visibility = View.VISIBLE
                    }
                }
                blockscreen.visibility = View.INVISIBLE
                plus1.visibility = View.VISIBLE
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
                val v = event.localState as View
                v.x = event.x - (v.width / 2)
                v.y = event.y - (v.height / 2)

                val owner = v.parent as ViewGroup
                owner.removeView(v)

                Workspace.addView(v)
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