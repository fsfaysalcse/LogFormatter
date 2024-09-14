class LogBoxFormatter(private val borderChar: Char = '-', private val padding: Int = 2) {

    fun formatBoxed(message: String): String {
        val wrappedMessage = LogFormatter.wrapText(message, 50)
        val boxWidth = wrappedMessage.maxOf { it.length } + (2 * padding) + 2
        val borderLine = "+${borderChar.toString().repeat(boxWidth - 2)}+"

        return buildString {
            appendLine(borderLine)
            wrappedMessage.forEach { line ->
                appendLine("|${" ".repeat(padding)}${line.padEnd(boxWidth - (2 * padding) - 2)}${" ".repeat(padding)}|")
            }
            appendLine(borderLine)
        }
    }
}
