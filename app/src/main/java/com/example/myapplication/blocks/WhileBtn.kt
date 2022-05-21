package com.example.myapplication.blocks

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.WhileBlockBinding
import com.example.myapplication.structs.BlockInterface

class WhileBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BlockInterface, ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding = WhileBlockBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.root.setOnLongClickListener {
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

    fun onSet() {
        binding.whilePlaceForDrop.setOnDragListener(dragAndDropListener())
        binding.whileInsidePlace.setOnDragListener(dragAndDropListener())
    }

}