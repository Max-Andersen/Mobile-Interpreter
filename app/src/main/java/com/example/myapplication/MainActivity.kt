package com.example.myapplication

import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import com.example.myapplication.blocks.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    private fun blockInit(){

        val createVarBlock = CreateVarBtn(this)
        createVarBlock.y += 450

        val variable1 = VariableBtn(this)
        variable1.y += 700

        val outputBlock = OutputBtn(this)
        outputBlock.y += 950

        val whileBlock = WhileBtn(this)
        whileBlock.y += 1200

        blockscreen.addView(createVarBlock)
        blockscreen.addView(variable1)
        blockscreen.addView(outputBlock)
        blockscreen.addView(whileBlock)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~СЛУШАТЕЛИ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        plus1.setOnClickListener {
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.GONE
                }
            }
            blockInit()
            blockAndVariable.visibility = View.VISIBLE
            closeBlockScreen.visibility = View.VISIBLE
            plus1.visibility = View.GONE
            consoleBtn.visibility = View.GONE
            zoomLayout.setVerticalPanEnabled(false)
            zoomLayout.setHorizontalPanEnabled(false)
        }

        closeBlockScreen.setOnClickListener{
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }
            blockAndVariable.visibility = View.GONE
            closeBlockScreen.visibility = View.GONE
            consoleBtn.visibility = View.VISIBLE
            plus1.visibility = View.VISIBLE
            zoomLayout.setVerticalPanEnabled(true)
            zoomLayout.setHorizontalPanEnabled(true)
        }

        blockbtn.setOnClickListener{
            variableBlock.visibility = View.GONE
            blockbtn.visibility = View.GONE
            blockscreen.visibility = View.VISIBLE
            varbtn.visibility = View.VISIBLE
        }

        varbtn.setOnClickListener{
            blockscreen.visibility = View.GONE
            variableBlock.visibility = View.VISIBLE
            varbtn.visibility = View.GONE
            blockbtn.visibility = View.VISIBLE
        }

        consoleBtn.setOnClickListener{
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.GONE
                }
            }
            CreateConsole.visibility = View.VISIBLE
            consoleCloseBtn.visibility = View.VISIBLE
            plus1.visibility = View.GONE
            consoleBtn.visibility = View.GONE
            zoomLayout.setVerticalPanEnabled(false)
            zoomLayout.setHorizontalPanEnabled(false)
        }

        consoleCloseBtn.setOnClickListener{
            for (i in 0..zoomLayout.Workspace.childCount){
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View){
                    child.visibility = View.VISIBLE
                }
            }
            CreateConsole.visibility = View.GONE
            consoleCloseBtn.visibility = View.GONE
            plus1.visibility = View.VISIBLE
            consoleBtn.visibility = View.VISIBLE
            zoomLayout.setVerticalPanEnabled(true)
            zoomLayout.setHorizontalPanEnabled(true)
        }



        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~БЛОКИ_В_ПАНЕЛЬ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        val start = StartBtn(this)
        start.y += 200

        val variable = VariableConsole(this)
        variable.y += 200

        var n = 2
        plusvar.setOnClickListener{
            plusvar.y += 270
            val anotherVariable = VariableConsole(this)
            anotherVariable.y += 270 * n
            variableBlock.addView(anotherVariable)
            n += 1
        }

        variableBlock.addView(variable)
        blockscreen.addView(start)


        Workspace.x = 0F
        Workspace.y = 0F
        Workspace.setOnDragListener(mainActivityDandD(zoomLayout, CreateConsole, blockAndVariable, plus1, consoleBtn, Workspace))

        //----------------------------------------
        //----------------------------------------
        //ЗАПУСК!!!!!!!!!!!!!ИУУУУУУУУУУУУ!!!!!!!!
        imageButton4.setOnClickListener {

            //printNodes(start)

            val program = StartProgram(this, start)
            program.main()
        }

    }


    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //~~~~~~~~~~~~~~~~~~~~~~~ФУНКЦИЯ ДЛЯ ПРОСМОТРА ДЕРЕВА~~~~~~~~~~~~~~~~~~~~~~~

    private fun printNodes(node: View) {

        when(node){
            is WhileBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0)
                {
                    println("parent: INSIDE ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
                if ((node[5] as ViewGroup).children.count() != 0)
                {
                    println("parent: PFD ${node}\n child: ${(node[5] as ViewGroup)[0]}")
                    printNodes((node[5] as ViewGroup)[0])
                }
            }
            is OutputBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0)
                {
                    println("parent: ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
            }
            is VariableBtn -> {
                if ((node[1] as ViewGroup).children.count() != 0)
                {
                    println("parent: ${node}\n child: ${(node[1] as ViewGroup)[0]}")
                    printNodes((node[1] as ViewGroup)[0])
                }
            }
            is StartBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0)
                {
                    println("parent: ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
            }
        }
    }
}