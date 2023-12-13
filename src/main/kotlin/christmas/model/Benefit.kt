package christmas.model

import christmas.utils.Constants.DISCOUNT_MIN_AMOUNT

class Benefit(private val visitDay: Int, private val orders: List<Order>) {
    private val totalOrderAmount = calculateTotalOrderAmount()
    private val giveAway = GiveAway(totalOrderAmount)
    private val discount = Discount(visitDay, orders)
    private val totalBenefitAmount = calculateTotalBenefitAmount()

    private fun calculateTotalOrderAmount(): Int {
        return orders.sumOf { order ->
            Menu.getMenuPriceByFood(order.getFood()) * order.getQuantity()
        }
    }

    private fun calculateTotalBenefitAmount(): Int {
        return discount.getDDayDiscount() + discount.getWeekDayDiscount() + discount.getWeekendDayDiscount() + discount.getSpecialDayDiscount() + giveAway.getPrice()
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getGiveAway() = giveAway
    fun getDiscount() = discount.takeIf { totalOrderAmount >= DISCOUNT_MIN_AMOUNT } ?: Discount(0, emptyList())
    fun getTotalBenefitAmount() = totalBenefitAmount
}