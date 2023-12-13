package christmas.model

import christmas.utils.Constants.D_DAY_END
import christmas.utils.Constants.NO_DISCOUNT

class Discount(private val visitDay: Int, private val orders: List<Order>) {
    private val calendar = Calendar(visitDay)

    private val dDayDiscount = calculateDDayDiscount()

    private fun calculateDDayDiscount(): Int {
        return (900 + visitDay * 100).takeIf { visitDay <= D_DAY_END } ?: NO_DISCOUNT
    }

    fun getDDayDiscount() = dDayDiscount
}