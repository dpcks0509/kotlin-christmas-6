package christmas.utils

import christmas.utils.Constants.DAY_END
import christmas.utils.Constants.DAY_START

object Validator {
    fun validateVisitDay(visitDay: String): Int {
        val validVisitDay = visitDay.toIntOrNull() ?: 0
        require(validVisitDay in DAY_START..DAY_END) { Exception.INVALID_VISIT_DAY.getMessage() }
        return validVisitDay
    }
}