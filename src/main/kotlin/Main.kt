fun main() {
    val boxFormatter = LogBoxFormatter()
    val boxedText = boxFormatter.formatBoxed("This is a long message that will be wrapped and placed inside a box with a border.")
    println(boxedText)

    val tableFormatter = LogTableFormatter()
    val header = listOf("Name", "Description", "Age")
    val data = listOf(
        listOf("Alice", "This description is long and should wrap around in the table.", 30),
        listOf("Bob", "Short desc", 25),
        listOf("Charlie", "Another long description that spans multiple lines in the table.", 35)
    )
    val table = tableFormatter.formatTable(header, data)
    println(table)

    val successMessage = LogColorFormatter.format(LogColorFormatter.LogLevel.SUCCESS, "This is a success message")
    val errorMessage = LogColorFormatter.format(LogColorFormatter.LogLevel.ERROR, "This is an error message")

    println(successMessage)
    println(errorMessage)
}

