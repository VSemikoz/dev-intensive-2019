package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import ru.skillbranch.devintensive.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
): ImageView(context, attrs, defStyleAttr) {

    companion object{
        private const val DEFAULT_COLOR = R.color.color_white
        private const val DEFAULT_BORDER = 2f
    }
    private var aspectColor = DEFAULT_COLOR
    private var aspectBorder = DEFAULT_BORDER


    init {
        if(attrs!=null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            aspectColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_COLOR)

            val b = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            aspectBorder = b.getDimension(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER)

            a.recycle()
            b.recycle()
        }
    }

    fun setBorderColor(){
        val avatarViewId = resources.getIdentifier("iv_avatar", "id", null)
        val avatarView = findViewById<CircleImageView>(avatarViewId)
    }

    fun setBorderWidth(){

    }

    fun getBorderWidth(){

    }

    fun getBorderColor(){

    }

}