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
        var numberOfDessert = 0
        orders.forEach { order ->
            val category = getMenuCategoryByFood(order.getFood())
            if (category == DESSERT) numberOfDessert += order.getQuantity()
        }
        return numberOfDessert
    }

    private fun countNumberOfMain(): Int {
        var numberOfMain = 0
        orders.sumOf { it.getQuantity() }
        orders.forEach { order ->
            val category = getMenuCategoryByFood(order.getFood())
            if (category == MAIN) numberOfMain += order.getQuantity()
        }
        return numberOfMain
    }

    fun getDDayDiscount() = (900 + (100 * visitDate)).takeIf { visitDate <= D_DAY_END } ?: NO_DISCOUNT
    fun getWeekDayDiscount() =
        (DISCOUNT_PER_MENU * countNumberOfDessert()).takeIf { calendar.isWeekDay() } ?: NO_DISCOUNT
    fun getWeekendDayDiscount() =
        (DISCOUNT_PER_MENU * countNumberOfMain()).takeIf { calendar.isWeekendDay() } ?: NO_DISCOUNT
    fun getSpecialDayDiscount() = (1000).takeIf { calendar.isSpecialDay() } ?: NO_DISCOUNT
}