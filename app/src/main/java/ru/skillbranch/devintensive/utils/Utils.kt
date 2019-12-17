package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName:String?):Pair<String?, String?>{
//        TODO FIX THAT
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transLiteration(payload: String, divedier: String = " "): String {
        TODO("not implemented")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented")
    }
}