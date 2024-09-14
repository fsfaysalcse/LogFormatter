package old

object ColorLogger {

    fun log(message: String, level: LogLevel, color: LogColor = LogColor.RESET) {
        kotlin.io.println("${color.code}${level.label} $message${LogColor.RESET.code}")
    }

    fun logBoxed(message: String, color: LogColor = LogColor.RESET) {
        val wrappedMessage = wrapText(message, 50)
        val boxWidth = wrappedMessage.maxOf { it.length } + 4
        kotlin.io.println("${color.code}+${"-".repeat(boxWidth)}+${LogColor.RESET.code}")
        wrappedMessage.forEach { line ->
            kotlin.io.println("${color.code}|  ${line.padEnd(boxWidth - 4)}  |${LogColor.RESET.code}")
        }
        kotlin.io.println("${color.code}+${"-".repeat(boxWidth)}+${LogColor.RESET.code}")
    }

    fun printTable(header: List<String>, rows: List<List<Any>>) {
        val columnWidths = header.mapIndexed { index, _ ->
            maxOf(header[index].length, rows.maxOfOrNull { row -> row[index].toString().length } ?: 0)
        }

        printFullSeparator(columnWidths, "+", "+", "+")
        printWrappedRow(header, columnWidths)
        printFullSeparator(columnWidths, "+", "+", "+")

        rows.forEach { row ->
            printWrappedRow(row, columnWidths)
            printFullSeparator(columnWidths, "+", "+", "+")
        }
    }

    private fun printWrappedRow(row: List<Any>, columnWidths: List<Int>) {
        val wrappedRows = row.mapIndexed { index, cell -> wrapText(cell.toString(), columnWidths[index]) }
        val maxLines = wrappedRows.maxOf { it.size }

        for (i in 0 until maxLines) {
            kotlin.io.print("|")
            row.forEachIndexed { index, _ ->
                val cellLine = if (i < wrappedRows[index].size) wrappedRows[index][i] else ""
                kotlin.io.print(" ${cellLine.padEnd(columnWidths[index])} |")
            }
            kotlin.io.println()
        }
    }

    private fun printFullSeparator(columnWidths: List<Int>, start: String, middle: String, end: String) {
        kotlin.io.print(start)
        columnWidths.forEachIndexed { index, width ->
            kotlin.io.print("-".repeat(width + 2))
            if (index != columnWidths.lastIndex) kotlin.io.print(middle)
        }
        kotlin.io.println(end)
    }

    private fun wrapText(text: String, maxWidth: Int): List<String> {
        if (text.length <= maxWidth) return listOf(text)
        val words = text.split(" ")
        val wrappedLines = mutableListOf<String>()
        var currentLine = StringBuilder()

        for (word in words) {
            if (currentLine.length + word.length + 1 > maxWidth) {
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

    fun println(message: String, color: LogColor = LogColor.RESET) {
        kotlin.io.println("${color.code}$message${LogColor.RESET.code}")
    }
}
