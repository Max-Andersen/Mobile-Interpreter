package com.example.myapplication.blocks
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.IfBlockBinding
import com.example.myapplication.structs.BlockInterface

class IfBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):BlockInterface, ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = IfBlockBinding.inflate(LayoutInflater.from(context), this)
    private var c = context

    init {
        binding.root.setOnLongClickListener{
            binding.ifInsidePlace.setOnDragListener { _, _ -> false }
            binding.ifPlaceForDrop.setOnDragListener { _, _ -> false }
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
        binding.ifInsidePlace.setOnDragListener(dragAndDropListener())
        binding.ifPlaceForDrop.setOnDragListener(dragAndDropListener())
    }

}