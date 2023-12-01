package christmas.model

import christmas.util.Constants.DISCOUNT_PER_MENU
import christmas.util.Constants.DESSERT
import christmas.util.Constants.D_DAY_END
import christmas.util.Constants.MAIN

class Discount(private val visitDate: Int, private val orders: List<Order>) {
    private val calendar = Calendar()

    fun calculateDDayDiscount(): Int {
        var dDayDiscount = 0
        if (visitDate <= D_DAY_END) dDayDiscount = 900 + (100 * visitDate)
        return dDayDiscount
    }

    fun calculateWeekDayDiscount(): Int {
        var weekDayDiscount = 0
        if (calendar.isWeekDay(visitDate)) {
            var numberOfDessert = countNumberOfDessert()
            weekDayDiscount = DISCOUNT_PER_MENU * numberOfDessert
        }
        return weekDayDiscount
    }

    private fun countNumberOfDessert(): Int {
        var numberOfDessert = 0
        orders.forEach { order ->
            val category = Menu.entries.find { menu ->
                menu.getFood() == order.getFood()
            }?.getCategory()
            if (category == DESSERT) numberOfDessert += order.getQuantity()
        }
        return numberOfDessert
    }

    fun calculateWeekendDayDiscount(): Int {
        var weekendDayDiscount = 0
        if (calendar.isWeekendDay(visitDate)) {
            var numberOfMain = calculateNumberOfMain()
            weekendDayDiscount = DISCOUNT_PER_MENU * numberOfMain
        }
        return weekendDayDiscount
    }

    private fun calculateNumberOfMain(): Int {
        var numberOfMain = 0
        orders.forEach { order ->
            val category = Menu.entries.find { menu ->
                menu.getFood() == order.getFood()
            }?.getCategory()
            if (category == MAIN) numberOfMain += order.getQuantity()
        }
        return numberOfMain
    }

    fun calculateSpecialDayDiscount(): Int {
        var specialDayDiscount = 0
        if (calendar.isSpecialDay(visitDate)) specialDayDiscount = 1000
        return specialDayDiscount
    }
}