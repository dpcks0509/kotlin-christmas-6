package christmas.model

import christmas.util.Constants.DISCOUNT_MINIMUM_AMOUNT
import christmas.util.Constants.GIVE_AWAY_MINIMUM_AMOUNT

class Benefit(private val visitDate: Int, private val orders: List<Order>) {
    private var totalOrderAmount = 0
    private var giveAway = "없음"
    private var dDayDiscount = 0
    private var weekDayDiscount = 0
    private var weekendDayDiscount = 0
    private var specialDayDiscount = 0

    init {
        calculateTotalOrderAmount()
        judgeGiveAway()
        if (totalOrderAmount >= DISCOUNT_MINIMUM_AMOUNT) {
            val discount = Discount(visitDate, orders)
            dDayDiscount = discount.calculateDDayDiscount()
            weekDayDiscount = discount.calculateWeekDayDiscount()
            weekendDayDiscount = discount.calculateWeekendDayDiscount()
            specialDayDiscount = discount.calculateSpecialDayDiscount()
        }
    }

    private fun calculateTotalOrderAmount() {
        orders.forEach { order ->
            val orderAmount = calculateOrderAmount(order.getFood())
            totalOrderAmount += orderAmount * order.getQuantity()
        }
    }

    private fun calculateOrderAmount(food: String): Int {
        return Menu.entries.find { menu ->
            menu.getFood() == food
        }?.getPrice()!!
    }

    private fun judgeGiveAway() {
        if (totalOrderAmount >= GIVE_AWAY_MINIMUM_AMOUNT)
            giveAway = "샴페인 1개"
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getGiveAway() = giveAway
}