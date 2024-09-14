object LogFormatter {

    fun format(message: String, vararg args: Any): String {
        return String.format(message, *args)
    }

    fun wrapText(text: String, maxWidth: Int): List<String> {
        if (text.length <= maxWidth) return listOf(text)
        val words = text.split(" ")
        val wrappedLines = mutableListOf<String>()
        val currentLine = StringBuilder()

        words.forEach { word ->
            if (currentLine.length + word.length + 1 > maxWidth) {
                wrappedLines.add(currentLine.toString())
                currentLine.clear().append(word)
            } else {
                if (currentLine.isNotEmpty()) currentLine.append(" ")
                currentLine.append(word)
            }
        }

        if (currentLine.isNotEmpty()) wrappedLines.add(currentLine.toString())
        return wrappedLines
    }
}
