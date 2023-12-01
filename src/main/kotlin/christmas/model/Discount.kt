package christmas.model

import christmas.util.Constants.DISCOUNT_PER_MENU
import christmas.util.Constants.DESSERT
import christmas.util.Constants.D_DAY_END

class Discount(private val visitDate: Int, private val orders: List<Order>) {
    private val calendar = Calendar()

    fun calculateDDayDiscount(): Int {
        if (visitDate <= D_DAY_END)
            return 900 + (100 * visitDate)
        return 0
    }

    fun calculateWeekDayDiscount(): Int {
        if (calendar.isWeekDay(visitDate)) {
            var numberOfDessert = countNumberOfDessert()
            return DISCOUNT_PER_MENU * numberOfDessert
        }
        return 0
    }

    private fun countNumberOfDessert(): Int {
        return orders.count { order ->
            val category = Menu.entries.find { menu ->
                menu.getFood() == order.getFood()
            }?.getCategory()
            category == DESSERT
        }
    }
}