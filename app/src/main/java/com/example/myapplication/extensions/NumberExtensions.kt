package com.example.myapplication.extensions

import java.text.Normalizer

fun String.slug(): String {
    return this
        .trim()
        .uppercase()
        .replace("Ü", "U")
        .replace("İ", "I")
        .replace("Ç", "C")
        .replace("Ş", "S")
        .replace("Ö", "O")
        .replace("Ğ", "G")
}

fun String.numberFormat(): String {
    var number = this
    if (number.substring(0, 2) == "+9") {
        number = number.substring(2, number.length)
    }
    if (number.substring(0, 1) == "0") {
        number = number.substring(1, number.length)
    }
    number = Normalizer.normalize(number, Normalizer.Form.NFD).replace("\\p{Mn}", "")
    return number.replace("\\s+".toRegex(), "")
}
