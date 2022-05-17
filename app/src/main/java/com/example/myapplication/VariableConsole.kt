package com.example.myapplication
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.InputVariableBinding

class VariableConsole @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):ConstraintLayout(context, attrs, defStyleAttr){
    private var binding = InputVariableBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.imageButton5.setOnClickListener(){
            removeAllViews()
        }
    }
}