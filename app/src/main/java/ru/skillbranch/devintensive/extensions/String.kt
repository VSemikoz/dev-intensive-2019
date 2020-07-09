package ru.skillbranch.devintensive.extensions

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
        val replace = subString.replace("  ", " ")
        subString = replace
    }
    return subString

}
