package christmas.model

class Calendar(private val visitDate: Int) {
    private val weekDays = listOf(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28)
    private val weekendDays = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    private val specialDays = listOf(3, 10, 17, 24, 25, 31)

    fun isWeekDay(): Boolean {
        return weekDays.contains(visitDate)
    }

    fun isWeekendDay(): Boolean {
        return weekendDays.contains(visitDate)
    }

    fun isSpecialDay(): Boolean {
        return specialDays.contains(visitDate)
    }
}