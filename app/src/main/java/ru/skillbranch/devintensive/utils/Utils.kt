package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint

val TRANSLITERATION_DICT = mapOf(
    "а" to "a", "б" to "b", "в" to "v", "г" to "g",
    "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z",
    "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m",
    "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s",
    "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c",
    "ч" to "ch", "ш" to "sh", "щ" to "sh", "ъ" to "", "ы" to "i",
    "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya"
)

fun String.truncate(maxSymbols: Int = 16): String{
    val trimString = this.trim()
    if (trimString.length > maxSymbols){
        return "${trimString.slice(0 until maxSymbols).trim()}..."
    }
    return trimString
}

fun String.stripHtml(): String{
    val index1 = this.indexOfFirst { it == '>'}
    val index2 = this.indexOfLast { it == '<'}
    var subString = this.substring(index1 + 1, index2)

    while(subString.contains("  ")) {
        var replace = subString.replace("  ", " ")
        subString = replace
    }
    return subString

}

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if (firstName == "" || firstName == null)
            firstName = null

        if (lastName == "" || lastName == null)
            lastName = null

        return firstName to lastName
    }

    @SuppressLint("DefaultLocale")
    fun transliteration(payload: String, divedier: String = " "): String {
        var resultString = ""

        for (symbol in payload) {
            val searchSymbol = symbol.toString().toLowerCase()
            resultString += when (searchSymbol) {
                in TRANSLITERATION_DICT.keys -> if (symbol.isUpperCase())
                    TRANSLITERATION_DICT[searchSymbol]?.toUpperCase()
                else TRANSLITERATION_DICT[searchSymbol]
                " " -> divedier
                else -> symbol
            }
        }
        return resultString
    }

    @SuppressLint("DefaultLocale")
    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstChar = when (firstName) {
            "", null, " " -> ""
            else -> firstName[0].toString()
        }
        val secondChar = when (lastName) {
            "", null, " " -> ""
            else -> lastName[0].toString()
        }
        if (firstChar + secondChar == "")
            return null
        return (firstChar + secondChar).toUpperCase()
    }
}