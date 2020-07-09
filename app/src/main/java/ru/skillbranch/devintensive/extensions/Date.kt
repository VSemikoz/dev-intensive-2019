package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
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
    var resultString = ""
    var diffInMillisec: Long = date.time - this.time

    if (diffInMillisec > 0) {
        resultString += when (diffInMillisec) {
            in 0..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE -> "${TimeUnits.MINUTE.plural((diffInMillisec / MINUTE).toInt())} назад"
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> "${TimeUnits.HOUR.plural((diffInMillisec / HOUR).toInt())} назад"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> "${TimeUnits.DAY.plural((diffInMillisec / DAY).toInt())} назад"
            else -> "более года назад"
        }
    } else {
        diffInMillisec = abs(diffInMillisec)
        resultString += when (diffInMillisec) {
            in 0..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> "через ${TimeUnits.MINUTE.plural((diffInMillisec / MINUTE).toInt())}"
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> "через ${TimeUnits.HOUR.plural((diffInMillisec / HOUR).toInt())}"
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> "через ${TimeUnits.DAY.plural((diffInMillisec / DAY).toInt())}"
            else -> "более чем через год"
        }


    }
    return resultString
}

fun TimeUnits.plural(countOfUnits: Int): String {
    val absCountOfUnits: Int = if (countOfUnits < 0) {
        countOfUnits * (-1)
    }else{
        countOfUnits
    }

    val n = absCountOfUnits % 100
    val n1 = n % 10
    return "$absCountOfUnits " + when (this) {
        TimeUnits.SECOND -> when {
            n in 11..19 -> "секунд"
            n1 in 2..4 -> "секунды"
            n1 == 1 -> "секунду"
            else -> "секунд"
        }
        TimeUnits.MINUTE -> when {
            n in 11..19 -> "минут"
            n1 in 2..4 -> "минуты"
            n1 == 1 -> "минуту"
            else -> "минут"
        }
        TimeUnits.HOUR -> when {
            n in 11..19 -> "часов"
            n1 in 2..4 -> "часа"
            n1 == 1 -> "час"
            else -> "часов"
        }
        TimeUnits.DAY -> when {
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
