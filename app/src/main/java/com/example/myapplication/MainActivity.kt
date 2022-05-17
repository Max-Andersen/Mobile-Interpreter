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
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    fun blockInit(){
        val whilee = WhileBtn(this)
        whilee.y += 450

        val variable1 = VariableBtn(this)
        variable1.y += 700

        val outputblock = OutputBtn(this)
        outputblock.y += 950

        blockscreen.addView(whilee)
        blockscreen.addView(outputblock)
        blockscreen.addView(variable1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //-------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~СЛУШАТЕЛИ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        plus1.setOnClickListener() {
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.GONE
                }
            }
            blockInit()
            blockAndVariable.visibility = View.VISIBLE
            plus1.visibility = View.GONE
        }

        closeBlockScreen.setOnClickListener(){
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }
            CreateConsole.visibility = View.GONE
            blockAndVariable.visibility = View.GONE
            plus1.visibility = View.VISIBLE
        }

        blockbtn.setOnClickListener(){
            variableBlock.visibility = View.GONE
            blockbtn.visibility = View.GONE
            blockscreen.visibility = View.VISIBLE
            varbtn.visibility = View.VISIBLE
        }

        varbtn.setOnClickListener(){
            blockscreen.visibility = View.GONE
            variableBlock.visibility = View.VISIBLE
            varbtn.visibility = View.GONE
            blockbtn.visibility = View.VISIBLE
        }

        consoleBtn.setOnClickListener(){
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.GONE
                }
            }
            CreateConsole.visibility = View.VISIBLE
            blockAndVariable.visibility = View.GONE
        }

        consoleCloseBtn.setOnClickListener(){
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }
            CreateConsole.visibility = View.GONE
            blockAndVariable.visibility = View.GONE
        }



        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~БЛОКИ_В_ПАНЕЛЬ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        val start = StartBtn(this)
        start.y += 200

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

        variableBlock.addView(variable)
        blockscreen.addView(start)


        Workspace.setOnDragListener(dragAndDropListener)


        //ЗАПУСК!!!!!!!!!!!!!ИУУУУУУУУУУУУ!!!!!!!!
        imageButton4.setOnClickListener(){
            val program = StartProgram(this, start)
            program.main()
        }

    }

    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DRAG_AND_DROP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    val dragAndDropListener = View.OnDragListener{ view, event ->
        when (event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
                for (i in 0..zoomLayout.Workspace.childCount){
                    val child = zoomLayout.Workspace.getChildAt(i)
                    if (child is View){
                        child.visibility = View.VISIBLE
                    }
                }
                CreateConsole.visibility = View.GONE
                blockAndVariable.visibility = View.GONE
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