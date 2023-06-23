package com.aafaq.trails.data.models

import android.view.View

private const val UNDEFINED = -1


data class TrailConfig(
    val startTrailingOn: StartTrailing = StartTrailing.ON_START,
    val resetTrailingOn: ResetTrailing = ResetTrailing.ON_PAUSE,
    val listeners: TrailedScrollViewListeners? = null,
)

interface TrailedScrollViewListeners {
    /**
     * Listener for getting the scroll in activity/fragment without
     * disturbing the actual nestedScrollView's onScroll Listener
     * */
    fun onScrollChange(
        view: View? = null,
        x: Int = UNDEFINED,
        y: Int = UNDEFINED,
        oldX: Int = UNDEFINED,
        oldY: Int = UNDEFINED
    )

    /**
     * When ever a view's state has changed this method will be invoked
     * */
    fun onViewTrailed(trail: Trail? = null, state: TrailedViewState? = null)

    /**
     * lifeCycle of traling will start/reset(stop/)Pause in general
     * depends on the TrailConfig
     * */
    fun onTrailReset()
    fun onTrailStarted()
    fun onTrailPaused()
}

sealed class StartTrailing {
    object ON_START : StartTrailing()
    object ON_SCROLL : StartTrailing()
    data class AFTER(val mills: Long) : StartTrailing()
}

sealed class ResetTrailing {
    object ON_PAUSE : ResetTrailing()
    data class AFTER(val mills: Long) : ResetTrailing()
}