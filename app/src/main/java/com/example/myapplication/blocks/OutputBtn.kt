package com.example.myapplication.blocks
import android.content.Context
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.example.myapplication.checkForChildren
import com.example.myapplication.checkForLink
import com.example.myapplication.databinding.OutputBlockBinding

class OutputBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = OutputBlockBinding.inflate(LayoutInflater.from(context), this)

    private val dragAndDropListener = OnDragListener{ view, event ->
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

                //-------------------------------------------
                //ПРОВЕРКА: НЕ ЛЕЖИТ ЛИ destination под owner

                if(checkForLink(destination, dragBlock) && checkForChildren(destination)) {

                    //---------------------------------
                    //УВЕЛИЧЕНИЕ ПОЛОСКИ ВЛОЖЕННОСТИ!!!

                    var x = destination.parent as View

                    while (true) {
                        if (x is WhileBtn) {
                            x[4].layoutParams.height += dragBlock.height - x[0].layoutParams.height / 2
                            x = x.parent.parent as View
                        } else if (x is VariableBtn || x is OutputBtn || x is StartBtn) {
                            x = x.parent.parent as View
                        } else {
                            break
                        }
                    }

                    //---------------------------------
                    //УМЕНЬШЕНИЕ ПОЛОСКИ ВЛОЖЕННОСТИ!!!

                    x = owner.parent as View

                    while (true) {
                        if (x is WhileBtn) {
                            x[4].layoutParams.height -= dragBlock.height - x[0].layoutParams.height / 2
                            x = x.parent.parent as View
                        } else if (x is VariableBtn || x is OutputBtn || x is StartBtn) {
                            x = x.parent.parent as View
                        } else {
                            break
                        }
                    }

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

    init {
        binding.root.setOnLongClickListener{
            binding.outputPlaceForDrop.setOnDragListener { _, _ -> false }
            val textOnBoard = ""
            val item = ClipData.Item(textOnBoard)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(textOnBoard, mimeTypes, item)

            val dragAndDropBuilder = DragShadowBuilder(it)
            it.startDragAndDrop(data, dragAndDropBuilder, it, 0)

            true
        }

    }

    fun onSet(){
        binding.outputPlaceForDrop.setOnDragListener(dragAndDropListener)
    }
}