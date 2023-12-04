package christmas.model

import christmas.model.Menu.Companion.getMenuCategoryByFood
import christmas.util.Constants.DISCOUNT_PER_MENU
import christmas.util.Constants.DESSERT
import christmas.util.Constants.D_DAY_END
import christmas.util.Constants.MAIN
import christmas.util.Constants.NO_DISCOUNT

class Discount(private val visitDate: Int, private val orders: List<Order>) {
    private val calendar = Calendar(visitDate)

    private fun countNumberOfDessert(): Int {
        return orders.sumOf { order ->
            val category = getMenuCategoryByFood(order.getFood())
            order.getQuantity().takeIf { category == DESSERT } ?: 0
        }
    }

    private fun countNumberOfMain(): Int {
        return orders.sumOf { order ->
            val category = getMenuCategoryByFood(order.getFood())
            order.getQuantity().takeIf { category == MAIN } ?: 0
        }
    }

    fun getDDayDiscount() = (900 + (100 * visitDate)).takeIf { visitDate <= D_DAY_END } ?: NO_DISCOUNT
    fun getWeekDayDiscount() =
        (DISCOUNT_PER_MENU * countNumberOfDessert()).takeIf { calendar.isWeekDay() } ?: NO_DISCOUNT
    fun getWeekendDayDiscount() =
        (DISCOUNT_PER_MENU * countNumberOfMain()).takeIf { calendar.isWeekendDay() } ?: NO_DISCOUNT
    fun getSpecialDayDiscount() = (1000).takeIf { calendar.isSpecialDay() } ?: NO_DISCOUNT
}