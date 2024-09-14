class LogTableFormatter {

    fun printTable(header: List<String>, rows: List<List<Any>>) {
        val columnWidths = calculateColumnWidths(header, rows)

        printSeparator(columnWidths)
        printRow(header, columnWidths)
        printSeparator(columnWidths)

        rows.forEach { row ->
            printRow(row, columnWidths)
            printSeparator(columnWidths)
        }
    }

    private fun calculateColumnWidths(header: List<String>, rows: List<List<Any>>): List<Int> {
        return header.indices.map { index ->
            maxOf(
                header[index].length,
                rows.maxOfOrNull { row -> row[index].toString().length } ?: 0
            )
        }
    }

    private fun printRow(row: List<Any>, columnWidths: List<Int>) {
        val wrappedRows = row.mapIndexed { index, cell -> wrapText(cell.toString(), columnWidths[index]) }
        val maxLines = wrappedRows.maxOf { it.size }

        for (i in 0 until maxLines) {
            print("|")
            row.indices.forEach { index ->
                val cellLine = wrappedRows[index].getOrElse(i) { "" }
                print(" ${cellLine.padEnd(columnWidths[index])} |")
            }
            println()
        }
    }

    private fun printSeparator(columnWidths: List<Int>) {
        print("+")
        columnWidths.forEachIndexed { index, width ->
            print("-".repeat(width + 2))
            if (index != columnWidths.lastIndex) print("+")
        }
        println("+")
    }

    private fun wrapText(text: String, maxWidth: Int): List<String> {
        if (text.length <= maxWidth) return listOf(text)
        val words = text.split(" ")
        val wrappedLines = mutableListOf<String>()
        var currentLine = StringBuilder()

        for (word in words) {
            if (currentLine.isNotEmpty() && currentLine.length + word.length + 1 > maxWidth) {
                wrappedLines.add(currentLine.toString())
                currentLine = StringBuilder(word)
            } else {
                if (currentLine.isNotEmpty()) currentLine.append(" ")
                currentLine.append(word)
            }
        }

        if (currentLine.isNotEmpty()) wrappedLines.add(currentLine.toString())
        return wrappedLines
    }
}
