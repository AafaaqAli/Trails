package com.aafaq.trails.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.MutableLiveData
import com.aafaq.trails.data.deligates.ITrailableHelper
import com.aafaq.trails.data.models.ResetTrailing
import com.aafaq.trails.data.models.StartTrailing
import com.aafaq.trails.data.models.TrailConfig
import com.aafaq.trails.utils.delayedAsyncTask
import com.aafaq.trails.utils.isViewVisible


class TrailedNestedScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : NestedScrollView(context, attrs, defStyleAttr) {
    private var config: TrailConfig? = null
    private var trailedItems: MutableLiveData<ArrayList<TrailedView>> = MutableLiveData()
    fun initTrailing(trailConfig: TrailConfig){
        this.config = trailConfig

        when(trailConfig.startTrailingOn){
            is StartTrailing.ON_START -> {
                postViewingTrails()
            }

            is StartTrailing.ON_SCROLL -> {
                postViewingTrailOnScroll()
            }

            is StartTrailing.AFTER -> {
                delayedAsyncTask (trailConfig.startTrailingOn.mills){
                    postViewingTrails()
                }
            }
        }
    }

    private fun postViewingTrailOnScroll(){
        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            config?.listeners?.let { listeners ->
                listeners.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY)
                listeners.onTrailStarted()

                if(v.isViewVisible(this) && (v is ITrailableHelper)){
                    //add view to trailedItems
                    listeners.onViewTrailed()
                }
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        if(config?.resetTrailingOn == ResetTrailing.ON_PAUSE) trailedItems.value?.clear()

        config?.listeners?.onTrailReset()
    }

    private fun postViewingTrails(){
        recursivelyFindChildren(this)
        postViewingTrailOnScroll()
    }

    fun resetTrailing(){
        when(config?.resetTrailingOn){
            is ResetTrailing.AFTER -> {
                //todo
            }
            else -> {}
        }
    }

    private fun recursivelyFindChildren(view: View?) {
        if (view is ViewGroup) {
            view.forEach {
                recursivelyFindChildren(it)
            }
        } else {
            if(view is ITrailableHelper){
                if(view.isViewVisible(this)){
                    config?.listeners?.onViewTrailed()
                }
            }
        }
    }
}