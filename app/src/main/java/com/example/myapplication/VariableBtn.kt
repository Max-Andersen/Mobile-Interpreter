package com.example.myapplication
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
import com.example.myapplication.databinding.VariableBlockBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.start_block.view.*

class VariableBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = VariableBlockBinding.inflate(LayoutInflater.from(context), this)

    val dragAndDropListener = View.OnDragListener{ view, event ->
        val dragBlock = event.localState as View
        val destination = view as ConstraintLayout
        val owner = dragBlock.parent as ViewGroup

        when (event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
                //               if (owner.id != blockscreen.id && owner.id != Workspace.id){ // вот тут надо как-то сделать, чтобы
                //                   owner.layoutParams.height -= 200                         // род. блок уменьшался и place for drop бесконечно не рос
                //               }
                view.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                destination.placeForDrop.setBackgroundColor(Color.GRAY)
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                destination.placeForDrop.setBackgroundColor(Color.TRANSPARENT)
                true
            }

            DragEvent.ACTION_DROP -> {
                dragBlock.x = destination.rootView.beginView.x //подтягиваем drag block ровно в place for drop
                dragBlock.y = destination.rootView.beginView.y


                owner.removeView(dragBlock)

                destination.addView(dragBlock)
                destination.setBackgroundColor(Color.TRANSPARENT)
                destination.layoutParams.height += 400
                view.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                //Workspace.setOnDragListener(View.OnDragListener{ view, event -> false})
                view.invalidate()
                true
            }

            else -> {false}
        }
    }


    init {
        binding.root.setOnLongClickListener(){
            val textOnBoard = ""
            val item = ClipData.Item(textOnBoard)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(textOnBoard, mimeTypes, item)

            val dragAndDropBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragAndDropBuilder, it, 0)
            true
        }

    }

    fun onSet(){
        binding.placeForDrop.setOnDragListener(dragAndDropListener)
    }

}