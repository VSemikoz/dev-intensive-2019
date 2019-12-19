package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(patttern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(patttern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY

    }
    this.time = time
    return this
}


fun Date.humanizeDiff(date: Date = Date()): String {
    TODO("not implemented")
}

fun TimeUnits.plural(countOfUnits: Int): String {
    val n = Math.abs(countOfUnits) % 100
    val n1 = n % 10
    return "$countOfUnits " + when(this){
        TimeUnits.SECOND -> when{
            n in 11..19 -> "секунд"
            n1 in 2..4 -> "секунды"
            n1 == 1 -> "секунду"
            else -> "секунд"
        }
        TimeUnits.MINUTE -> when{
            n in 11..19 -> "минут"
            n1 in 2..4 -> "минуты"
            n1 == 1 -> "минуту"
            else -> "минут"
        }
        TimeUnits.HOUR -> when{
            n in 11..19 -> "часов"
            n1 in 2..4 -> "часа"
            n1 == 1 -> "час"
            else -> "часов"
        }
        TimeUnits.DAY -> when{
            n in 11..19 -> "дней"
            n1 in 2..4 -> "дня"
            n1 == 1 -> "день"
            else -> "дней"
        }
    }
}
enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}