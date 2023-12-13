package christmas.model

import christmas.model.Badge.Companion.getBadgeByTotalBenefitAmount
import christmas.utils.Constants.BENEFIT_MIN_AMOUNT

class Benefit(private val visitDay: Int, private val orders: List<Order>) {
    private val totalOrderAmount = calculateTotalOrderAmount()
    private val giveAway = GiveAway(totalOrderAmount)
    private val discount = Discount(visitDay, orders)
    private val totalBenefitAmount = calculateTotalBenefitAmount()
    private val paymentAmount = calculatePaymentAmount()
    private val badge = getBadgeByTotalBenefitAmount(totalBenefitAmount)

    private fun calculateTotalOrderAmount(): Int {
        return orders.sumOf { order ->
            Menu.getMenuPriceByFood(order.getFood()) * order.getQuantity()
        }
    }

    private fun calculateTotalBenefitAmount(): Int {
        return discount.getTotalDiscount() + giveAway.getPrice()
    }

    private fun calculatePaymentAmount(): Int {
        return totalOrderAmount - discount.getTotalDiscount()
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getGiveAway() = giveAway
    fun getDiscount() = discount.takeIf { totalOrderAmount >= BENEFIT_MIN_AMOUNT } ?: Discount(0, emptyList())
    fun getTotalBenefitAmount() = totalBenefitAmount
    fun getPaymentAmount() = paymentAmount
    fun getBadge() = badge.takeIf { totalOrderAmount >= BENEFIT_MIN_AMOUNT } ?: getBadgeByTotalBenefitAmount(0)
}