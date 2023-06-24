package com.aafaq.trails.utils

import android.graphics.Rect
import android.view.View
import androidx.core.widget.NestedScrollView
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

fun <T> delayedAsyncTask(delayMs: Long = 0, task: () -> T): ScheduledFuture<*>? {
    return Executors
        .newSingleThreadScheduledExecutor()
        .schedule(Callable<T> { task() }, delayMs, TimeUnit.MILLISECONDS)
}

fun <T> asyncTask(task: () -> T) = delayedAsyncTask(0, task)


fun View.isViewVisible(nestedScrollView: NestedScrollView): Boolean{
    val visibleRect = Rect()
    nestedScrollView.getHitRect(visibleRect)

    return this.getLocalVisibleRect(visibleRect)
}

