package christmas.model

import christmas.model.Badge.Companion.getBadgeByTotalBenefitAmount

class Benefit(visitDate: Int, orders: List<Order>) {
    private val calculator = Calculator(visitDate, orders)
    private val giveAway = GiveAway(calculator.getTotalOrderAmount())
    private val discount = Discount(visitDate, orders)
    private val badge = getBadgeByTotalBenefitAmount(calculator.getTotalBenefitAmount())

    fun getCalculator() = calculator
    fun getGiveAway() = giveAway
    fun getDiscount() = discount
    fun getBadge() = badge
}