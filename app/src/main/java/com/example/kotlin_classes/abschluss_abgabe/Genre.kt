package com.example.kotlin_classes.abschluss_abgabe

enum class Genre(val description : String) {
    FICTION("Fictional stories and novels"),
    NON_FICTION("Real events and stories"),
    SCIENCE("Scientific facts"),
    HISTORY("Historical events and timelines"),
    CHILDREN("Values and norms explained through simple stories");

    fun printDescription(){
        println(description)
    }
}
