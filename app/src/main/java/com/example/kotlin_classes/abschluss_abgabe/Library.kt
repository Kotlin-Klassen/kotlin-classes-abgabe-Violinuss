package com.example.kotlin_classes.abschluss_abgabe

class Library {

    private val sortiment = mutableListOf<Book>()

    fun addBook(book: Book){
        sortiment.add(book)
    }

    fun searchBook(item: String) : List<Book> {
        return sortiment.filter{it.title.equals(item, ignoreCase = true) || it.author.equals(item, ignoreCase = true)}
    }

    fun printStatus(){
        sortiment.forEach{ book ->
            print("Book Title: ${book.title}, Author: ${book.author}, Genre: ${book.genre}, Status: ")
            printBookStatus(book.status)
        }
    }

    class LibraryAdress(val street : String, val city : String, val zipCode : Int){

        fun printAdress(){
            println("Adress of the Library: $street, $zipCode $city")
        }
    }

    inner class LibraryMember(val name : String, val memberID : Int){

        fun checkoutBook(book: Book, dueDate: String) {
            when(book.status){
                is Available -> book.status = CheckedOut(dueDate)
                is Reserved -> {
                    val reservedBy = (book.status as Reserved).reservedBy
                    if (reservedBy == this.name) {

                        book.status = CheckedOut(dueDate)
                    } else {
                        println("The book is reserved and therefore it cannot be checked out.")
                    }
                }
                else -> {
                    println("The book is already checked out.")}
            }
        }

        // Note: Ich war mir nicht sicher, ob wenn ein Buch weg es man es immernoch reservieren kann,
        // aber in der Bibliothek ist es möglich ein Buch zu reservieren, welches nicht da ist.
        // Dann darf man es als Nächstes ausleihen, sobald es da ist. Also falls es nicht so gewollt war...
        // Tut es mir leid
        fun  reserveBook(book: Book){
            when(book.status){
                is Available -> book.status = Reserved(this.name)
                is CheckedOut -> book.status = Reserved(this.name)
                else -> {
                    printBookStatus(book.status)
                }
            }
        }
    }
}

fun main() {
    val myLibrary = Library()
    val Book1 = Book("Gone without a trace", "Berki Car", Genre.NON_FICTION, Reserved("Berki"))
    val Book2 = Book("How to Vio", "Vio Car", Genre.SCIENCE, CheckedOut("30.6.2025"))
    val Book3 = Book("Der Heimweg", "Sebastian Fitzek", Genre.FICTION, Available)
    val Book4 = Book("Conni lernt laufen", "Linus Minus", Genre.CHILDREN, CheckedOut("28.12.2024"))
    val Book5 = Book("Russische Revolution", "Juju Daisy", Genre.HISTORY, Available)

    myLibrary.addBook(Book1)
    myLibrary.addBook(Book2)
    myLibrary.addBook(Book3)
    myLibrary.addBook(Book4)
    myLibrary.addBook(Book5)

    val libraryDestination = Library.LibraryAdress("Linostraße", "Berlino", 89564)
    libraryDestination.printAdress()

    val member = myLibrary.LibraryMember("Loni", 25687)
    member.reserveBook(Book3)
    member.checkoutBook(Book5, "15.01.2025")

    myLibrary.printStatus()

    member.checkoutBook(Book3, "10.02.2025")

    myLibrary.printStatus()

}
