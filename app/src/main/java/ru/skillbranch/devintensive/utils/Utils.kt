package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint

val TRANSLITERATION_DICT = mapOf(
    "а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh",
    "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o",
    "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c",
    "ч" to "ch", "ш" to "sh", "щ" to "sh", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu",
    "я" to "ya"
)

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0) != "") parts?.getOrNull(0) else null
        val lastName = if (parts?.getOrNull(1) != "") parts?.getOrNull(1) else null
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
