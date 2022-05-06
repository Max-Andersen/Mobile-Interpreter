package com.example.myapplication

import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.ActivityMainBinding
//import com.example.myapplication.databinding.StartBlockBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plus1.setOnClickListener() {
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.INVISIBLE
                }
            }

            blockscreen.visibility = View.VISIBLE
            plus1.visibility = View.INVISIBLE
        }

        closeBlockScreen.setOnClickListener(){
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }

            blockscreen.visibility = View.INVISIBLE
            plus1.visibility = View.VISIBLE
        }

        val start = StartBtn(this)

        val whilee = WhileBtn(this)
        whilee.y += 300

        val anotherWhilee = WhileBtn(this)
        anotherWhilee.y += 600

        blockscreen.addView(start)
        blockscreen.addView(whilee)
        blockscreen.addView(anotherWhilee)

        Workspace.setOnDragListener(dragAndDropListener)
    }

    val dragAndDropListener = View.OnDragListener{ view, event ->
        when (event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
                for (i in 0..Workspace.childCount){
                    val child = Workspace.getChildAt(i)
                    if (child is View){
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
                val v = event.localState as ConstraintLayout
                v.x = event.x - (v.width / 2)
                v.y = event.y - (v.height / 2)

                val owner = v.parent as ViewGroup
                owner.removeView(v)
                Workspace.addView(v)

                (event.localState as? StartBtn)?.onSet() // ставят обработчик на вложенный в них place for drop
                (event.localState as? WhileBtn)?.onSet()

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