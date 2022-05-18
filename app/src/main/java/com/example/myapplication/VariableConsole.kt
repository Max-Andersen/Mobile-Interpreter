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
import com.example.myapplication.databinding.InputVariableBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.start_block.view.*

class VariableConsole @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = InputVariableBinding.inflate(LayoutInflater.from(context), this)


}