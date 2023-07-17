package com.aafaq.trails.data.models

import com.aafaq.trails.utils.Constants

data class Trail(
    val id: Int? = null,
    val tag: String? = null,
)

data class TrailLogDetails(
    /**
     * for debug
     * */
    var debugTag: String = Constants.DEFAULT_TRAIL_TAG,
    val debugMessage: String? = null,

    var title: String? = null,
    var description: String? = null
){
    fun asString(): String{
        return "$debugTag --> $title: $description\n"
    }
}