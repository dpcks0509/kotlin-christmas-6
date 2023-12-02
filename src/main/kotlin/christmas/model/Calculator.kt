package christmas.model

import christmas.util.Constants.DISCOUNT_MINIMUM_AMOUNT
import christmas.util.Constants.NO_DISCOUNT

class Calculator(private val visitDate: Int, private val orders: List<Order>) {
    private val totalOrderAmount = calculateTotalOrderAmount()
    private val totalDiscount = calculateTotalDiscount()
    private fun calculateTotalOrderAmount(): Int {
        return orders.sumOf { order ->
            val orderAmount = calculateOrderAmount(order.getFood())
            orderAmount * order.getQuantity()
        }
    }

    private fun calculateOrderAmount(food: String): Int {
        return Menu.values().find { menu ->
            menu.getFood() == food
        }?.getPrice()!!
    }

    private fun calculateTotalDiscount(): Int {
        val discount = Discount(visitDate, orders)
        return (discount.getDDayDiscount() + discount.getWeekDayDiscount() + discount.getWeekendDayDiscount() + discount.getSpecialDayDiscount()).takeIf { totalOrderAmount >= DISCOUNT_MINIMUM_AMOUNT }
            ?: NO_DISCOUNT
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getTotalBenefitAmount() = totalDiscount + GiveAway(totalOrderAmount).getPrice()
    fun getPaymentAmount() = totalOrderAmount - totalDiscount
}