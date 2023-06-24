package com.aafaq.trails.views

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.FrameLayout
import com.aafaq.trails.data.deligates.ITrailableHelper
import com.aafaq.trails.data.deligates.TrailableImp

class TrailedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,

    private val trailAbleImp: TrailableImp = TrailableImp()
) : FrameLayout(context, attrs, defStyleAttr), ITrailableHelper by trailAbleImp{
    fun getPositionOnScreen() = Rect().apply {
        getLocalVisibleRect(this)
    }
}