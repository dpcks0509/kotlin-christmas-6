package christmas.model

import christmas.util.Constants.GIVE_AWAY_MINIMUM_AMOUNT

class Benefit(private val orders: List<Order>) {
    private var totalOrderAmount = 0
    private var giveAway = "없음"

    init {
        calculateTotalOrderAmount()
        judgeGiveAway()
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