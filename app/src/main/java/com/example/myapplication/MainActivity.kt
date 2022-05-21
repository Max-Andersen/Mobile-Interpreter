package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.children
import androidx.core.view.get
import com.example.myapplication.blocks.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.io.*
import java.util.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private var flagFirstOutInConsole = false

    private fun blockInit1() {

        val createVarBlock = CreateVarBtn(this)
        createVarBlock.y += 500

        val variable1 = VariableBtn(this)
        variable1.y += 800

        val outputBlock = OutputBtn(this)
        outputBlock.y += 1100

        blockscreen.addView(createVarBlock)
        blockscreen.addView(variable1)
        blockscreen.addView(outputBlock)
    }

    private fun blockInit2() {

        val whileBlock = WhileBtn(this)
        whileBlock.y += 500

        val ifBlock = IfBtn(this)
        ifBlock.y += 900

        val ifElseBlock = IfElseBtn(this)
        ifElseBlock.y += 1300

        variableBlock.addView(whileBlock)
        variableBlock.addView(ifBlock)
        variableBlock.addView(ifElseBlock)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~СЛУШАТЕЛИ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        burgerOpen.setOnClickListener {
            burgerMenu.visibility = View.VISIBLE
            plus1.visibility = View.GONE
            consoleBtn.visibility = View.GONE
            burgerOpen.visibility = View.GONE
            zoomLayout.setVerticalPanEnabled(false)
            zoomLayout.setHorizontalPanEnabled(false)
            zoomLayout.setZoomEnabled(false)
        }

        exit.setOnClickListener {
            exitProcess(-1)
        }

        closeBurgerMenu.setOnClickListener {
            burgerMenu.visibility = View.GONE
            plus1.visibility = View.VISIBLE
            consoleBtn.visibility = View.VISIBLE
            burgerOpen.visibility = View.VISIBLE
            zoomLayout.setVerticalPanEnabled(true)
            zoomLayout.setHorizontalPanEnabled(true)
            zoomLayout.setZoomEnabled(true)
        }

        plus1.setOnClickListener {
            for (i in 0..zoomLayout.Workspace.childCount) {
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View) {
                    child.visibility = View.GONE
                }
            }
            blockInit1()
            blockInit2()
            blockAndVariable.visibility = View.VISIBLE
            closeBlockScreen.visibility = View.VISIBLE
            plus1.visibility = View.GONE
            consoleBtn.visibility = View.GONE
            burgerOpen.visibility = View.GONE
            zoomLayout.setVerticalPanEnabled(false)
            zoomLayout.setHorizontalPanEnabled(false)
            zoomLayout.setZoomEnabled(false)
        }

        closeBlockScreen.setOnClickListener {
            for (i in 0..zoomLayout.Workspace.childCount) {
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View) {
                    child.visibility = View.VISIBLE
                }
            }
            blockAndVariable.visibility = View.GONE
            closeBlockScreen.visibility = View.GONE
            consoleBtn.visibility = View.VISIBLE
            plus1.visibility = View.VISIBLE
            burgerOpen.visibility = View.VISIBLE
            zoomLayout.setVerticalPanEnabled(true)
            zoomLayout.setHorizontalPanEnabled(true)
            zoomLayout.setZoomEnabled(true)
        }

        blockbtn.setOnClickListener {
            blockInit1()
            variableBlock.visibility = View.GONE
            blockbtn.visibility = View.GONE
            blockscreen.visibility = View.VISIBLE
            varbtn.visibility = View.VISIBLE
        }

        varbtn.setOnClickListener {
            blockInit2()
            blockscreen.visibility = View.GONE
            variableBlock.visibility = View.VISIBLE
            varbtn.visibility = View.GONE
            blockbtn.visibility = View.VISIBLE
        }

        consoleBtn.setOnClickListener {
            for (i in 0..zoomLayout.Workspace.childCount) {
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View) {
                    child.visibility = View.GONE
                }
            }

            consoleScroll.visibility = View.VISIBLE
            consoleCloseBtn.visibility = View.VISIBLE
            plus1.visibility = View.GONE
            consoleBtn.visibility = View.GONE
            startEnd.visibility = View.GONE
            burgerOpen.visibility = View.GONE
            zoomLayout.setVerticalPanEnabled(false)
            zoomLayout.setHorizontalPanEnabled(false)
            zoomLayout.setZoomEnabled(false)

            if (!flagFirstOutInConsole) {
                val handler = Handler()
                var time = 0
                while (time < 100000) {
                    handler.postDelayed({
                        StartConsoleMessage?.text = "TTKSMT is ready to work . "
                    }, time.toLong())
                    time += 1000
                    handler.postDelayed({
                        StartConsoleMessage?.text = "TTKSMT is ready to work . ."
                    }, time.toLong())
                    time += 1000
                    handler.postDelayed({
                        StartConsoleMessage?.text = "TTKSMT is ready to work . . ."
                    }, time.toLong())
                    time += 1000
                    if (consoleScroll.visibility == View.INVISIBLE) {
                        break
                    }
                }
            } else {
                CreateConsole?.removeView(StartConsoleMessage)
            }


        }

        consoleCloseBtn.setOnClickListener {
            for (i in 0..zoomLayout.Workspace.childCount) {
                val child = zoomLayout.Workspace.getChildAt(i)
                if (child is View) {
                    child.visibility = View.VISIBLE
                }
            }
            consoleScroll.visibility = View.GONE
            consoleCloseBtn.visibility = View.GONE
            plus1.visibility = View.VISIBLE
            consoleBtn.visibility = View.VISIBLE
            startEnd.visibility = View.VISIBLE
            burgerOpen.visibility = View.VISIBLE
            zoomLayout.setVerticalPanEnabled(true)
            zoomLayout.setHorizontalPanEnabled(true)
            zoomLayout.setZoomEnabled(true)
        }


        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ТЕМЫ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        maxTheme.setOnClickListener {
            header.setBackgroundColor(getColor(R.color.maxheader))
            startEnd.setBackgroundColor(getColor(R.color.maxstart))
            blockscreen.setBackgroundColor(getColor(R.color.maxblock))
            variableBlock.setBackgroundColor(getColor(R.color.maxblock))
            Workspace.setBackgroundColor(getColor(R.color.maxwork))
            CreateConsole.setBackgroundColor(getColor(R.color.maxblock))
            burgerMenu.setBackgroundColor(getColor(R.color.maxburger))
            maxTheme.setBackgroundColor(getColor(R.color.maxbtn))
            kirillTheme.setBackgroundColor(getColor(R.color.maxbtn))
            titTheme.setBackgroundColor(getColor(R.color.maxbtn))
            randomTheme.setBackgroundColor(getColor(R.color.maxbtn))
            blockbtn.setBackgroundColor(getColor(R.color.maxbtn))
            varbtn.setBackgroundColor(getColor(R.color.maxbtn))
            headerText.setTextColor(getColor(R.color.black))
            consoleBtn.setImageResource(R.drawable.cyber)
        }
        kirillTheme.setOnClickListener {
            header.setBackgroundColor(getColor(R.color.kirillheader))
            startEnd.setBackgroundColor(getColor(R.color.kirillstart))
            blockscreen.setBackgroundColor(getColor(R.color.kirillheader))
            variableBlock.setBackgroundColor(getColor(R.color.kirillheader))
            Workspace.setBackgroundColor(getColor(R.color.kirillwork))
            CreateConsole.setBackgroundColor(getColor(R.color.kirillstart))
            burgerMenu.setBackgroundColor(getColor(R.color.kirillheader))
            maxTheme.setBackgroundColor(getColor(R.color.kirillbtn))
            kirillTheme.setBackgroundColor(getColor(R.color.kirillbtn))
            titTheme.setBackgroundColor(getColor(R.color.kirillbtn))
            randomTheme.setBackgroundColor(getColor(R.color.kirillbtn))
            blockbtn.setBackgroundColor(getColor(R.color.kirillbtn))
            varbtn.setBackgroundColor(getColor(R.color.kirillbtn))
            headerText.setTextColor(getColor(R.color.kirillbtn))
            consoleBtn.setImageResource(R.drawable.glaz)
        }
        titTheme.setOnClickListener {
            header.setBackgroundColor(getColor(R.color.titheader))
            startEnd.setBackgroundColor(getColor(R.color.titstart))
            blockscreen.setBackgroundColor(getColor(R.color.titheader))
            variableBlock.setBackgroundColor(getColor(R.color.titheader))
            Workspace.setBackgroundColor(getColor(R.color.titwork))
            CreateConsole.setBackgroundColor(getColor(R.color.titwork))
            burgerMenu.setBackgroundColor(getColor(R.color.titburger))
            maxTheme.setBackgroundColor(getColor(R.color.titheader))
            kirillTheme.setBackgroundColor(getColor(R.color.titheader))
            titTheme.setBackgroundColor(getColor(R.color.titheader))
            randomTheme.setBackgroundColor(getColor(R.color.titheader))
            blockbtn.setBackgroundColor(getColor(R.color.titbtn))
            varbtn.setBackgroundColor(getColor(R.color.titbtn))
            headerText.setTextColor(getColor(R.color.titstart))
            consoleBtn.setImageResource(R.drawable.spikeee)
        }
        randomTheme.setOnClickListener {

            val rnd = Random()
            header.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            startEnd.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            blockscreen.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            variableBlock.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            Workspace.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            CreateConsole.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            burgerMenu.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            maxTheme.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            kirillTheme.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            titTheme.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            randomTheme.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            blockbtn.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            varbtn.setBackgroundColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            headerText.setTextColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )
            consoleBtn.setImageResource(R.drawable.command)
        }

        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------


        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~БЛОКИ_В_ПАНЕЛЬ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        val start = StartBtn(this)
        start.y += 200

        blockscreen.addView(start)

        Workspace.x = 0F
        Workspace.y = 0F
        Workspace.setOnDragListener(
            mainActivityDandD(
                zoomLayout,
                consoleScroll,
                blockAndVariable,
                plus1,
                consoleBtn,
                Workspace
            )
        )

        //----------------------------------------
        //----------------------------------------
        //ЗАПУСК
        imageButton4.setOnClickListener {

            //printNodes(start)

            flagFirstOutInConsole = true

            StartProgram(start).main(CreateConsole, this)
        }
    }


    private fun isStoragePermissionGranted(): Boolean {
        return if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
            false
        }
    }

    private fun save(fileContent: String) {
        Toast.makeText(this, "зашёл", Toast.LENGTH_SHORT).show()
        if (isStoragePermissionGranted()) {
            if (fileContent != "") {
                val filename = "Prikol.txt"
                val filepath = "DirForSaves"
                val myExternalFile = File(getExternalFilesDir(filepath), filename)
                try {
                    val fos = FileOutputStream(myExternalFile)
                    fos.write(fileContent.toByteArray())
                    fos.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Не сохранился :(", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(this, "Сохранился", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Не сохранился :(", Toast.LENGTH_SHORT).show()
            }
        }
        Toast.makeText(this, "вышел", Toast.LENGTH_SHORT).show()
    }


    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //~~~~~~~~~~~~~~~~~~~~~~~ФУНКЦИЯ ДЛЯ ПРОСМОТРА ДЕРЕВА~~~~~~~~~~~~~~~~~~~~~~~

    private fun printNodes(node: View) {

        when (node) {
            is WhileBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0) {
                    println("parent: INSIDE ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
                if ((node[5] as ViewGroup).children.count() != 0) {
                    println("parent: PFD ${node}\n child: ${(node[5] as ViewGroup)[0]}")
                    printNodes((node[5] as ViewGroup)[0])
                }
            }
            is IfBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0) {
                    println("parent: INSIDE ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
                if ((node[5] as ViewGroup).children.count() != 0) {
                    println("parent: PFD ${node}\n child: ${(node[5] as ViewGroup)[0]}")
                    printNodes((node[5] as ViewGroup)[0])
                }
            }
            is IfElseBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0) {
                    println("parent: INSIDE ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
                if ((node[3] as ViewGroup).children.count() != 0) {
                    println("parent: INSIDE ${node}\n child: ${(node[3] as ViewGroup)[0]}")
                    printNodes((node[3] as ViewGroup)[0])
                }
                if ((node[10] as ViewGroup).children.count() != 0) {
                    println("parent: PFD ${node}\n child: ${(node[10] as ViewGroup)[0]}")
                    printNodes((node[10] as ViewGroup)[0])
                }
            }
            is OutputBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0) {
                    println("parent: ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
            }
            is VariableBtn -> {
                if ((node[1] as ViewGroup).children.count() != 0) {
                    println("parent: ${node}\n child: ${(node[1] as ViewGroup)[0]}")
                    printNodes((node[1] as ViewGroup)[0])
                }
            }
            is StartBtn -> {
                if ((node[2] as ViewGroup).children.count() != 0) {
                    println("parent: ${node}\n child: ${(node[2] as ViewGroup)[0]}")
                    printNodes((node[2] as ViewGroup)[0])
                }
            }
        }
    }
}