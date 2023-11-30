package christmas.util

import christmas.util.Constants.VISIT_DATE_END
import christmas.util.Constants.VISIT_DATE_START

object Validator {
    fun validateVisitDate(visitDate: String): Int {
        require(visitDate.toIntOrNull() in VISIT_DATE_START..VISIT_DATE_END) { Exception.INVALID_VISIT_DATE.getMessage() }
        return visitDate.toInt()
    }
}