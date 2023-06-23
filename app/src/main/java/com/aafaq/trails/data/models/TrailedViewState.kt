package com.aafaq.trails.data.models

sealed class TrailedViewState() {
    object UN_KNOWN: TrailedViewState()
    object PARTIALLY_VIEWED: TrailedViewState()
    object VIEWING: TrailedViewState()
    object VIEWED: TrailedViewState()
}