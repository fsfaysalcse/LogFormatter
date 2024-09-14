class LogTableFormatter {

    fun formatTable(header: List<String>, rows: List<List<Any>>): String {
        val columnWidths = calculateColumnWidths(header, rows)
        val stringBuilder = StringBuilder()

        stringBuilder.append(formatSeparator(columnWidths))
        stringBuilder.append(formatRow(header, columnWidths))
        stringBuilder.append(formatSeparator(columnWidths))

        rows.forEach { row ->
            stringBuilder.append(formatRow(row, columnWidths))
            stringBuilder.append(formatSeparator(columnWidths))
        }

        return stringBuilder.toString()
    }

    private fun calculateColumnWidths(header: List<String>, rows: List<List<Any>>): List<Int> {
        return header.indices.map { index ->
            maxOf(
                header[index].length,
                rows.maxOfOrNull { row -> wrapText(row[index].toString(), Int.MAX_VALUE).maxOf { it.length } } ?: 0
            )
        }
    }

    private fun formatRow(row: List<Any>, columnWidths: List<Int>): String {
        val wrappedRows = row.mapIndexed { index, cell -> wrapText(cell.toString(), columnWidths[index]) }
        val maxLines = wrappedRows.maxOf { it.size }
        val stringBuilder = StringBuilder()

        for (i in 0 until maxLines) {
            stringBuilder.append("|")
            row.indices.forEach { index ->
                val cellLines = wrappedRows[index]
                val cellLine = cellLines.getOrElse(i) { "" }
                stringBuilder.append(" ${cellLine.padEnd(columnWidths[index])} |")
            }
            stringBuilder.appendLine()
        }

        return stringBuilder.toString()
    }

    private fun formatSeparator(columnWidths: List<Int>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("+")
        columnWidths.forEachIndexed { index, width ->
            stringBuilder.append("-".repeat(width + 2))
            if (index != columnWidths.lastIndex) stringBuilder.append("+")
        }
        stringBuilder.append("+")
        stringBuilder.appendLine()

        return stringBuilder.toString()
    }

    private fun wrapText(text: String, maxWidth: Int): List<String> {
        val lines = text.split("\n")
        val wrappedLines = mutableListOf<String>()

        for (line in lines) {
            if (line.length <= maxWidth) {
                wrappedLines.add(line)
            } else {
                val words = line.split(" ")
                val lineBuilder = StringBuilder()

                for (word in words) {
                    if (lineBuilder.isNotEmpty() && lineBuilder.length + word.length + 1 > maxWidth) {
                        wrappedLines.add(lineBuilder.toString())
                        lineBuilder.clear()
                    }

                    if (lineBuilder.isNotEmpty()) lineBuilder.append(" ")
                    lineBuilder.append(word)
                }

                if (lineBuilder.isNotEmpty()) wrappedLines.add(lineBuilder.toString())
            }
        }

        return wrappedLines
    }
}
