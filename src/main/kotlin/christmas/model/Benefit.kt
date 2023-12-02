package christmas.model

import christmas.util.Constants.DISCOUNT_MINIMUM_AMOUNT

class Benefit(private val visitDate: Int, private val orders: List<Order>) {
    private var totalOrderAmount = 0
    private var totalDiscount = 0
    private var totalBenefitAmount = 0
    private var paymentAmount = 0

    init {
        calculateTotalOrderAmount()
        if (totalOrderAmount >= DISCOUNT_MINIMUM_AMOUNT) {
            calculateTotalDiscount()
            calculateTotalBenefitAmount()
        }
        calculatePaymentAmount()
    }

    private fun calculateTotalOrderAmount() {
        orders.forEach { order ->
            val orderAmount = calculateOrderAmount(order.getFood())
            totalOrderAmount += orderAmount * order.getQuantity()
        }
    }

    private fun calculateOrderAmount(food: String): Int {
        return Menu.values().find { menu ->
            menu.getFood() == food
        }?.getPrice()!!
    }

    private fun calculateTotalDiscount() {
        val discount = Discount(visitDate, orders)
        totalDiscount =
            discount.getDDayDiscount() + discount.getWeekDayDiscount() + discount.getWeekendDayDiscount() + discount.getSpecialDayDiscount()
    }

    private fun calculateTotalBenefitAmount() {
        totalBenefitAmount =
            totalDiscount + GiveAway(totalOrderAmount).getGiveAwayPrice()
    }

    private fun calculatePaymentAmount() {
        paymentAmount = totalOrderAmount - totalDiscount
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getTotalDiscount() = totalDiscount
    fun getTotalBenefitAmount() = totalBenefitAmount
    fun getPaymentAmount() = paymentAmount
}