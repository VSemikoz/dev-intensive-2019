package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint

object Utils {

    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if (firstName == "" ||  firstName == null)
            firstName = null

        if (lastName == "" ||  lastName == null)
            lastName = null

        return firstName to lastName
    }

    fun transLiteration(payload: String, divedier: String = " "): String {
        TODO("not implemented")
    }

    @SuppressLint("DefaultLocale")
    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstChar = when(firstName){
            "", null, " " -> ""
            else -> firstName[0].toString()
        }
        val secondChar = when(lastName){
            "", null, " " -> ""
            else -> lastName[0].toString()
        }
        if (firstChar + secondChar == "")
            return null
        return (firstChar + secondChar).toUpperCase()
    }
}