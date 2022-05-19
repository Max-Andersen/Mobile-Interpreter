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
import com.example.myapplication.databinding.OutputBlockBinding
import com.example.myapplication.structs.BlockInterface

class OutputBtn @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):BlockInterface, ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = OutputBlockBinding.inflate(LayoutInflater.from(context), this)

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
        binding.outputPlaceForDrop.setOnDragListener(dragAndDropListener())
    }
}