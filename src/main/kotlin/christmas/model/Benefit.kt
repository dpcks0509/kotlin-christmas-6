package christmas.model

import christmas.model.Badge.Companion.getBadgeByTotalBenefitAmount
import christmas.model.Menu.Companion.getMenuPriceByFood
import christmas.utils.Constants.BENEFIT_MIN_AMOUNT

class Benefit(visitDay: Int, private val orders: List<Order>) {
    private val totalOrderAmount = calculateTotalOrderAmount()
    private val giveAway = GiveAway(totalOrderAmount)
    private val discount = Discount(visitDay, orders).takeIf { totalOrderAmount >= BENEFIT_MIN_AMOUNT }
        ?: Discount(0, emptyList())
    private val totalBenefitAmount = calculateTotalBenefitAmount()
    private val paymentAmount = calculatePaymentAmount()
    private val badge = getBadgeByTotalBenefitAmount(totalBenefitAmount).takeIf { totalOrderAmount >= BENEFIT_MIN_AMOUNT }
        ?: getBadgeByTotalBenefitAmount(0)

    private fun calculateTotalOrderAmount(): Int {
        return orders.sumOf { order -> getMenuPriceByFood(order.getFood()) * order.getQuantity() }
    }

    private fun calculateTotalBenefitAmount(): Int {
        return discount.getTotalDiscount() + giveAway.getPrice()
    }

    private fun calculatePaymentAmount(): Int {
        return totalOrderAmount - discount.getTotalDiscount()
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getGiveAway() = giveAway
    fun getDiscount() = discount
    fun getTotalBenefitAmount() = totalBenefitAmount
    fun getPaymentAmount() = paymentAmount
    fun getBadge() = badge
}