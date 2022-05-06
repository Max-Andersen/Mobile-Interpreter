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
import com.example.myapplication.databinding.WhileBlockBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.start_block.view.*

class WhileBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = WhileBlockBinding.inflate(LayoutInflater.from(context), this)

    val dragAndDropListener = View.OnDragListener{ view, event ->
        val dragBlock = event.localState as View
        val destination = view as ConstraintLayout
        val owner = dragBlock.parent as ViewGroup
        //destination.beginView

        when (event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
//                if (owner.id != blockscreen.id && owner.id != Workspace.id){
//                    owner.layoutParams.height -= 200
//                }
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
                dragBlock.x = destination.rootView.beginView.x   //event.x - (v1.width / 2)
                dragBlock.y = destination.rootView.beginView.y   //event.y - (v1.height / 2)

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
            val textOnBoard = "This is While Node"
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