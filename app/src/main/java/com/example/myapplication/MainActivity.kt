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
import kotlinx.android.synthetic.main.output_block.view.*
import kotlinx.android.synthetic.main.start_block.view.*
import kotlinx.android.synthetic.main.variable_block.view.*
import kotlinx.android.synthetic.main.while_block.view.*
import android.os.Handler;
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {

    private fun blockInit(){
        val whileBlock = WhileBtn(this)
        whileBlock.y += 450

        val variable1 = VariableBtn(this)
        variable1.y += 700

        val outputBlock = OutputBtn(this)
        outputBlock.y += 950

        blockscreen.addView(whileBlock)
        blockscreen.addView(outputBlock)
        blockscreen.addView(variable1)
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
            plus1.visibility = View.GONE
        }
        closeBlockScreen.setOnClickListener{
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

        blockbtn.setOnClickListener{
            ScrollVar.visibility = View.GONE
            blockbtn.visibility = View.GONE
            blockscreen.visibility = View.VISIBLE
            varbtn.visibility = View.VISIBLE
        }

        varbtn.setOnClickListener{
            blockscreen.visibility = View.GONE
            ScrollVar.visibility = View.VISIBLE
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
            blockbtn.visibility = View.INVISIBLE
            consoleBtn.visibility = View.INVISIBLE
            CreateConsole.visibility = View.VISIBLE
            plus1.visibility = View.INVISIBLE
            blockAndVariable.visibility = View.GONE
            val handler = Handler()
            var time = 0;
            while (time < 10000) {
                handler.postDelayed({
                    StartConsoleMessage.text = "TTKSMT is ready to work . "
                }, time.toLong())
                time = time + 1000
                handler.postDelayed({
                    StartConsoleMessage.text = "TTKSMT is ready to work . ."
                }, time.toLong())
                time = time + 1000
                handler.postDelayed({
                    StartConsoleMessage.text = "TTKSMT is ready to work . . ."
                }, time.toLong())
                time = time + 1000
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
            plus1.visibility = View.VISIBLE
            CreateConsole.visibility = View.GONE
            blockAndVariable.visibility = View.GONE
            blockbtn.visibility = View.VISIBLE
            consoleBtn.visibility = View.VISIBLE
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
            val anotherVariable = VariableConsole(this)
//            anotherVariable.y += 270 * n
            variableBlock.addView(anotherVariable)
            n += 1
        }

//        variableBlock.addView(variable)
        blockscreen.addView(start)


        Workspace.x = 0F
        Workspace.y = 0F
        Workspace.setOnDragListener(dragAndDropListener)

        //----------------------------------------
        //----------------------------------------
        //ЗАПУСК!!!!!!!!!!!!!ИУУУУУУУУУУУУ!!!!!!!!
        imageButton4.setOnClickListener {

            printNodes(start)

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
                if (node.whileInsidePlace.children.count() != 0)
                {
                    println("parent: INSIDE ${node}\n child: ${node.whileInsidePlace[0]}")
                    printNodes(node.whileInsidePlace[0])
                }
                if (node.whilePlaceForDrop.children.count() != 0)
                {
                    println("parent: PFD ${node}\n child: ${node.whilePlaceForDrop[0]}")
                    printNodes(node.whilePlaceForDrop[0])
                }
            }
            is OutputBtn -> {
                if (node.outputPlaceForDrop.children.count() != 0)
                {
                    println("parent: ${node}\n child: ${node.outputPlaceForDrop[0]}")
                    printNodes(node.outputPlaceForDrop[0])
                }
            }
            is VariableBtn -> {
                if (node.varPlaceForDrop.children.count() != 0)
                {
                    println("parent: ${node}\n child: ${node.varPlaceForDrop[0]}")
                    printNodes(node.varPlaceForDrop[0])
                }
            }
            is StartBtn -> {
                if (node.startPlaceForDrop.children.count() != 0)
                {
                    println("parent: ${node}\n child: ${node.startPlaceForDrop[0]}")
                    printNodes(node.startPlaceForDrop[0])
                }
            }
        }
    }

    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DRAG_AND_DROP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private val dragAndDropListener = View.OnDragListener{ view, event ->
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