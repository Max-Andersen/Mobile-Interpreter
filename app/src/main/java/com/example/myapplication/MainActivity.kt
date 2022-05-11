package com.example.myapplication

import com.example.myapplication.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.blocks.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //-------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~СЛУШАТЕЛИ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        plus1.setOnClickListener() {
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.INVISIBLE
                }
            }

            blockAndVariable.visibility = View.VISIBLE
            plus1.visibility = View.INVISIBLE
        }

        closeBlockScreen.setOnClickListener(){
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }
            CreateConsole.visibility = View.INVISIBLE
            blockAndVariable.visibility = View.INVISIBLE
            plus1.visibility = View.VISIBLE
        }

        blockbtn.setOnClickListener(){
            variableBlock.visibility = View.INVISIBLE
            blockbtn.visibility = View.INVISIBLE
            blockscreen.visibility = View.VISIBLE
            varbtn.visibility = View.VISIBLE
        }

        varbtn.setOnClickListener(){
            blockscreen.visibility = View.INVISIBLE
            variableBlock.visibility = View.VISIBLE
            varbtn.visibility = View.INVISIBLE
            blockbtn.visibility = View.VISIBLE
        }

        consoleBtn.setOnClickListener(){
            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.INVISIBLE
                }
            }
            CreateConsole.visibility = View.VISIBLE
            blockAndVariable.visibility = View.INVISIBLE
        }

        consoleCloseBtn.setOnClickListener(){

            for (i in 0..Workspace.childCount){
                val child = Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }
            CreateConsole.visibility = View.INVISIBLE
            blockAndVariable.visibility = View.INVISIBLE
        }

        imageButton4.setOnClickListener(){

        }



        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~БЛОКИ_В_ПАНЕЛЬ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        val start = StartBtn(this)
        start.y += 200

        val whilee = WhileBtn(this)
        whilee.y += 450

        val variable = VariableConsole(this)
        variable.y += 200

        var n = 2;
        plusvar.setOnClickListener(){
            plusvar.y += 270
            val anotherVariable = VariableConsole(this)
            anotherVariable.y += 270 * n
            variableBlock.addView(anotherVariable)
            n += 1
        }

        val variable1 = VariableBtn(this)
        variable1.y += 700

        val outputblock = OutputBtn(this)
        outputblock.y += 950

        blockscreen.addView(outputblock)
        blockscreen.addView(variable1)
        variableBlock.addView(variable)
        blockscreen.addView(start)
        blockscreen.addView(whilee)

        Workspace.setOnDragListener(dragAndDropListener)
    }



    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DRAG_AND_DROP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    val dragAndDropListener = View.OnDragListener{ view, event ->
        when (event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
                for (i in 0..Workspace.childCount){
                    val child = Workspace.getChildAt(i)
                    if (child is View){
                        child.visibility = View.VISIBLE
                    }
                }
                CreateConsole.visibility = View.INVISIBLE
                blockAndVariable.visibility = View.INVISIBLE
                plus1.visibility = View.VISIBLE

//                val x = event.x
//                val y = event.y
//
//                val block = (view as? StartBtn)?.copy()

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

                // ставим обработчик на вложенный place for drop:
                (event.localState as? StartBtn)?.onSet()
                (event.localState as? WhileBtn)?.onSet()
                (event.localState as? VariableBtn)?.onSet()
                (event.localState as? OutputBtn)?.onSet()

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