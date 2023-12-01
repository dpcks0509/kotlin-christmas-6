package christmas.model

import christmas.util.Constants.DISCOUNT_MINIMUM_AMOUNT
import christmas.util.Constants.GIVE_AWAY_MINIMUM_AMOUNT
import christmas.util.Constants.NO_BENEFIT

class Benefit(private val visitDate: Int, private val orders: List<Order>) {
    private var totalOrderAmount = 0
    private var giveAway = NO_BENEFIT
    private var dDayDiscount = 0
    private var weekDayDiscount = 0
    private var weekendDayDiscount = 0
    private var specialDayDiscount = 0
    private var totalDiscount = 0
    private var giveAwayPrice = 0
    private var totalBenefitAmount = 0
    private var paymentAmount = 0

    init {
        calculateTotalOrderAmount()
        judgeGiveAway()
        if (totalOrderAmount >= DISCOUNT_MINIMUM_AMOUNT) {
            val discount = Discount(visitDate, orders)
            dDayDiscount = discount.calculateDDayDiscount()
            weekDayDiscount = discount.calculateWeekDayDiscount()
            weekendDayDiscount = discount.calculateWeekendDayDiscount()
            specialDayDiscount = discount.calculateSpecialDayDiscount()
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
        return Menu.entries.find { menu ->
            menu.getFood() == food
        }?.getPrice()!!
    }

    private fun judgeGiveAway() {
        if (totalOrderAmount >= GIVE_AWAY_MINIMUM_AMOUNT) {
            giveAway = "샴페인 1개"
            giveAwayPrice = Menu.CHAMPAGNE.getPrice()
        }
    }

    private fun calculateTotalDiscount() {
        totalDiscount = dDayDiscount + weekDayDiscount + weekendDayDiscount + specialDayDiscount
    }

    private fun calculateTotalBenefitAmount() {
        totalBenefitAmount = dDayDiscount + weekDayDiscount + weekendDayDiscount + specialDayDiscount + giveAwayPrice
    }

    private fun calculatePaymentAmount() {
        paymentAmount = totalOrderAmount - totalDiscount
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getGiveAway() = giveAway
    fun getDDayDiscount() = dDayDiscount
    fun getWeekDayDiscount() = weekDayDiscount
    fun getWeekendDayDiscount() = weekendDayDiscount
    fun getSpecialDayDiscount() = specialDayDiscount
    fun getTotalDiscount() = totalDiscount
    fun getGiveAwayPrice() = giveAwayPrice
    fun getTotalBenefitAmount() = totalBenefitAmount
    fun getPaymentAmount() = paymentAmount
}