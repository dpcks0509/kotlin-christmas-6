package christmas.model

import christmas.util.Constants.D_DAY_END

class Discount(private val visitDate: Int, private val orders: List<Order>) {
    private var dDayDiscount = 0

    init {
        calculateDDayDiscount()
    }

    private fun calculateDDayDiscount() {
        if (visitDate <= D_DAY_END)
            dDayDiscount = 900 + (100 * visitDate)
    }
}