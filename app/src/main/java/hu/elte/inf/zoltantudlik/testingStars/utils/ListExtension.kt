package hu.elte.inf.zoltantudlik.testingStars.utils

import hu.elte.inf.zoltantudlik.testingStars.rest.entities.Rating


interface ListExtension {
    fun List<Rating>.avg(): Float {
        return this.map { it.value }.sumBy { it }.toFloat() / this.size
    }
}