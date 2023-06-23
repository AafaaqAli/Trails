package com.aafaq.trails.data.deligates

import com.aafaq.trails.data.models.TrailedViewState

interface ITrailableHelper {
    fun onTrailedItemStateChange(trailedViewState: TrailedViewState)
}