package christmas.model

import christmas.model.Menu.Companion.getMenuCategoryByFood
import christmas.utils.Constants.DAY_START
import christmas.utils.Constants.DESSERT
import christmas.utils.Constants.DISCOUNT_PER_MENU
import christmas.utils.Constants.D_DAY_DISCOUNT_BASE
import christmas.utils.Constants.D_DAY_DISCOUNT_UNIT
import christmas.utils.Constants.D_DAY_END
import christmas.utils.Constants.MAIN
import christmas.utils.Constants.NO_DISCOUNT

class Discount(private val visitDay: Int, private val orders: List<Order>) {
    private val calendar = Calendar(visitDay)

    private val dDayDiscount = calculateDDayDiscount()
    private val weekDayDiscount = calculateWeekDayDiscount()
    private val weekendDayDiscount = calculateWeekendDayDiscount()

    private fun calculateDDayDiscount(): Int {
        return (D_DAY_DISCOUNT_BASE + (visitDay - 1) * D_DAY_DISCOUNT_UNIT).takeIf { visitDay in DAY_START..D_DAY_END }
            ?: NO_DISCOUNT
    }

    private fun calculateWeekDayDiscount(): Int {
        val numberOfDessert = orders.sumOf { order ->
            order.getQuantity().takeIf { getMenuCategoryByFood(order.getFood()) == DESSERT } ?: 0
        }
        return (numberOfDessert * DISCOUNT_PER_MENU).takeIf { calendar.isWeekDay() } ?: NO_DISCOUNT
    }

    private fun calculateWeekendDayDiscount(): Int {
        val numberOfMain = orders.sumOf { order ->
            order.getQuantity().takeIf { getMenuCategoryByFood(order.getFood()) == MAIN } ?: 0
        }
        return (numberOfMain * DISCOUNT_PER_MENU).takeIf { calendar.isWeekendDay() } ?: NO_DISCOUNT
    }

    fun getDDayDiscount() = dDayDiscount
    fun getWeekDayDiscount() = weekDayDiscount
    fun getWeekendDayDiscount() = weekendDayDiscount
}