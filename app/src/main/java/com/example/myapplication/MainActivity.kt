package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.get
import com.example.myapplication.blocks.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import kotlin.system.exitProcess


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

        val ifBlock = IfBtn(this)
        ifBlock.y += 1575

        val ifElseBlock = IfElseBtn(this)
        ifElseBlock.y += 1950

        blockscreen.addView(createVarBlock)
        blockscreen.addView(variable1)
        blockscreen.addView(outputBlock)
        blockscreen.addView(whileBlock)
        blockscreen.addView(ifBlock)
        blockscreen.addView(ifElseBlock)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~СЛУШАТЕЛИ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        burgerOpen.setOnClickListener{
            burgerMenu.visibility = View.VISIBLE
            plus1.visibility = View.GONE
            consoleBtn.visibility = View.GONE
            zoomLayout.setVerticalPanEnabled(false)
            zoomLayout.setHorizontalPanEnabled(false)
            zoomLayout.setZoomEnabled(false)
        }

        exit.setOnClickListener{
            exitProcess(-1)
        }

        closeBurgerMenu.setOnClickListener{
            burgerMenu.visibility = View.INVISIBLE
            plus1.visibility = View.VISIBLE
            consoleBtn.visibility = View.VISIBLE
            zoomLayout.setVerticalPanEnabled(true)
            zoomLayout.setHorizontalPanEnabled(true)
            zoomLayout.setZoomEnabled(true)
        }

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
            zoomLayout.setZoomEnabled(false)
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
            zoomLayout.setZoomEnabled(true)
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
            zoomLayout.setZoomEnabled(false)

            val handler = Handler()
            var time = 0
            while (time < 10000) {
                handler.postDelayed({
                    StartConsoleMessage.text = "TTKSMT is ready to work . "
                }, time.toLong())
                time += 1000
                handler.postDelayed({
                    StartConsoleMessage.text = "TTKSMT is ready to work . ."
                }, time.toLong())
                time += 1000
                handler.postDelayed({
                    StartConsoleMessage.text = "TTKSMT is ready to work . . ."
                }, time.toLong())
                time += 1000
                if(CreateConsole.visibility == View.INVISIBLE){
                    break
                }
            }
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
            zoomLayout.setZoomEnabled(true)
        }


        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ТЕМЫ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        maxTheme.setOnClickListener{
            header.setBackgroundColor(Color.parseColor("#00EAFD"))
            startEnd.setBackgroundColor(Color.parseColor("#EF799F"))
            blockscreen.setBackgroundColor(Color.parseColor("#5DA1B1"))
            variableBlock.setBackgroundColor(Color.parseColor("#5DA1B1"))
            Workspace.setBackgroundColor(Color.parseColor("#4D5397"))
            CreateConsole.setBackgroundColor(Color.parseColor("#4D5397"))
            burgerMenu.setBackgroundColor(Color.parseColor("#9864BA"))
            maxTheme.setBackgroundColor(Color.parseColor("#00EAFD"))
            kirillTheme.setBackgroundColor(Color.parseColor("#00EAFD"))
            titTheme.setBackgroundColor(Color.parseColor("#00EAFD"))
            randomTheme.setBackgroundColor(Color.parseColor("#00EAFD"))
            blockbtn.setBackgroundColor(Color.parseColor("#00EAFD"))
            varbtn.setBackgroundColor(Color.parseColor("#00EAFD"))
            headerText.setTextColor(Color.parseColor("#000000"))
            consoleBtn.setImageResource(R.drawable.cyber)
        }
        kirillTheme.setOnClickListener{
            header.setBackgroundColor(Color.parseColor("#D1DEDE"))
            startEnd.setBackgroundColor(Color.parseColor("#C58882"))
            blockscreen.setBackgroundColor(Color.parseColor("#D1DEDE"))
            variableBlock.setBackgroundColor(Color.parseColor("#D1DEDE"))
            Workspace.setBackgroundColor(Color.parseColor("#EAD2AC"))
            CreateConsole.setBackgroundColor(Color.parseColor("#C58882"))
            burgerMenu.setBackgroundColor(Color.parseColor("#D1DEDE"))
            maxTheme.setBackgroundColor(Color.parseColor("#AE5B42"))
            kirillTheme.setBackgroundColor(Color.parseColor("#AE5B42"))
            titTheme.setBackgroundColor(Color.parseColor("#AE5B42"))
            randomTheme.setBackgroundColor(Color.parseColor("#AE5B42"))
            blockbtn.setBackgroundColor(Color.parseColor("#AE5B42"))
            varbtn.setBackgroundColor(Color.parseColor("#AE5B42"))
            headerText.setTextColor(Color.parseColor("#AE5B42"))
            consoleBtn.setImageResource(R.drawable.glaz)
        }
        titTheme.setOnClickListener{
            header.setBackgroundColor(Color.parseColor("#E0CF3E"))
            startEnd.setBackgroundColor(Color.parseColor("#872380"))
            blockscreen.setBackgroundColor(Color.parseColor("#E0CF3E"))
            variableBlock.setBackgroundColor(Color.parseColor("#E0CF3E"))
            Workspace.setBackgroundColor(Color.parseColor("#284281"))
            CreateConsole.setBackgroundColor(Color.parseColor("#284281"))
            burgerMenu.setBackgroundColor(Color.parseColor("#C5E961"))
            maxTheme.setBackgroundColor(Color.parseColor("#E0CF3E"))
            kirillTheme.setBackgroundColor(Color.parseColor("#E0CF3E"))
            titTheme.setBackgroundColor(Color.parseColor("#E0CF3E"))
            randomTheme.setBackgroundColor(Color.parseColor("#E0CF3E"))
            blockbtn.setBackgroundColor(Color.parseColor("#2CAECD"))
            varbtn.setBackgroundColor(Color.parseColor("#2CAECD"))
            headerText.setTextColor(Color.parseColor("#872380"))
            consoleBtn.setImageResource(R.drawable.spikeee)
        }
        randomTheme.setOnClickListener{

            val rnd = Random()
            header.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            startEnd.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            blockscreen.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            variableBlock.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            Workspace.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            CreateConsole.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            burgerMenu.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            maxTheme.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            kirillTheme.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            titTheme.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            randomTheme.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            blockbtn.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            varbtn.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            headerText.setTextColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            consoleBtn.setImageResource(R.drawable.command)
        }

        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------



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

            StartProgram(start).main()
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
            is IfBtn -> {
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
            is IfElseBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0)
                {
                    println("parent: INSIDE ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
                if ((node[3] as ViewGroup).children.count() != 0)
                {
                    println("parent: INSIDE ${node}\n child: ${(node[3] as ViewGroup)[0]}")
                    printNodes((node[3] as ViewGroup)[0])
                }
                if ((node[10] as ViewGroup).children.count() != 0)
                {
                    println("parent: PFD ${node}\n child: ${(node[10] as ViewGroup)[0]}")
                    printNodes((node[10] as ViewGroup)[0])
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