package com.aafaq.trails.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.widget.NestedScrollView
import com.aafaq.trails.data.models.TrailConfig

class TrailedNestedScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : NestedScrollView(context, attrs, defStyleAttr){
    init {
        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

        }
    }
}