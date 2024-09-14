object LogColorFormatter {

    private const val RESET = "\u001B[0m"
    private const val RED = "\u001B[31m"
    private const val GREEN = "\u001B[32m"
    private const val YELLOW = "\u001B[33m"
    private const val BLUE = "\u001B[34m"
    private const val MAGENTA = "\u001B[35m"
    private const val CYAN = "\u001B[36m"
    private const val WHITE = "\u001B[37m"

    enum class LogLevel(val color: String) {
        SUCCESS(GREEN),
        DEBUG(BLUE),
        INFO(WHITE),
        WARN(YELLOW),
        ERROR(RED)
    }

    fun format(level: LogLevel, message: String): String {
        return "${level.color}${message}$RESET"
    }
}
