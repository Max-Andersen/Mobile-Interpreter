package com.example.myapplication.blocks
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.IfElseBlockBinding
import com.example.myapplication.structs.BlockInterface

class IfElseBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):BlockInterface, ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = IfElseBlockBinding.inflate(LayoutInflater.from(context), this)
    private var c = context

    init {
        binding.root.setOnLongClickListener{
            binding.ifElseInsidePlace.setOnDragListener { _, _ -> false }
            binding.ifElseInsideSecondPlace.setOnDragListener { _, _ -> false }
            binding.ifElsePlaceForDrop.setOnDragListener { _, _ -> false }
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
        binding.ifElseInsidePlace.setOnDragListener(dragAndDropListener())
        binding.ifElseInsideSecondPlace.setOnDragListener(dragAndDropListener())
        binding.ifElsePlaceForDrop.setOnDragListener(dragAndDropListener())
    }

}