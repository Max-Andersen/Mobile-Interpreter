package com.example.myapplication.blocks
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.WhileBlockBinding
import kotlinx.android.synthetic.main.while_block.view.*


class WhileBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = WhileBlockBinding.inflate(LayoutInflater.from(context), this)
    //private var c = context
    /*var oneDP = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 1f,
        context.resources.displayMetrics).toInt()*/

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
                view.invalidate()
                destination.setBackgroundColor(Color.GRAY)
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

                //Toast.makeText(context, "упал на вайл", Toast.LENGTH_SHORT).show()

                dragBlock.x = destination.rootView.beginView.x //подтягиваем drag block ровно в place for drop
                dragBlock.y = destination.rootView.beginView.y

                owner.removeView(dragBlock)

                destination.addView(dragBlock)
                destination.setBackgroundColor(Color.TRANSPARENT)

                (event.localState as? StartBtn)?.onSet()
                (event.localState as? WhileBtn)?.onSet()
                (event.localState as? VariableBtn)?.onSet()
                (event.localState as? OutputBtn)?.onSet()
                view.invalidate()

                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {

                //binding.view.layoutParams = LayoutParams(oneDP * 25, oneDP* binding.insidePlace.layoutParams.height)
                view.invalidate()
                true
            }

            else -> {false}
        }
    }


    init {
        binding.root.setOnLongClickListener{
            binding.whilePlaceForDrop.setOnDragListener { _, _ -> false }
            binding.whileInsidePlace.setOnDragListener { _, _ -> false }
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
        binding.whilePlaceForDrop.setOnDragListener(dragAndDropListener)
        binding.whileInsidePlace.setOnDragListener(dragAndDropListener)
    }

}