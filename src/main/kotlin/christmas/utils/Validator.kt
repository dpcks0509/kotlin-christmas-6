package christmas.utils

import christmas.model.Menu.Companion.getMenuCategoryByFood
import christmas.model.Menu.Companion.menuHasFood
import christmas.model.Order
import christmas.utils.Constants.BEVERAGE
import christmas.utils.Constants.DAY_END
import christmas.utils.Constants.DAY_START

object Validator {
    fun validateVisitDay(visitDay: String): Int {
        val validVisitDay = visitDay.toIntOrNull() ?: 0
        require(validVisitDay in DAY_START..DAY_END) { Exception.INVALID_VISIT_DAY.getMessage() }
        return validVisitDay
    }

    fun validateOrders(orders: String): List<Order> {
        val validOrders = orders.split(",").map { order -> validateOrder(order) }
        validateOrdersDuplicate(validOrders)
        validateOrdersOnlyBeverage(validOrders)
        validateOrdersTotalQuantity(validOrders)
        return validOrders
    }

    private fun validateOrder(order: String): Order {
        val validOrder = order.split("-")
        validateOrderFormat(validOrder)
        val validFood = validateOrderFood(validOrder[0])
        val validQuantity = validateOrderQuantity(validOrder[1])
        return Order(validFood, validQuantity)
    }

    private fun validateOrderFormat(order: List<String>) {
        require(order.size == 2) { Exception.INVALID_ORDER.getMessage() }
    }

    private fun validateOrderFood(food: String): String {
        require(menuHasFood(food)) { Exception.INVALID_ORDER.getMessage() }
        return food
    }

    private fun validateOrderQuantity(quantity: String): Int {
        val validQuantity = quantity.toIntOrNull() ?: 0
        require(validQuantity >= 1) { Exception.INVALID_ORDER.getMessage() }
        return validQuantity
    }

    private fun validateOrdersDuplicate(orders: List<Order>) {
        val orderFoods = orders.map { order -> order.getFood() }
        require(orders.size == orderFoods.toSet().size) { Exception.INVALID_ORDER.getMessage() }
    }

    private fun validateOrdersOnlyBeverage(orders: List<Order>) {
        val categories = orders.map { order -> getMenuCategoryByFood(order.getFood()) }.toSet()
        require(!(categories.size == 1 && categories.contains(BEVERAGE))) { Exception.INVALID_ORDER.getMessage() }
    }

    private fun validateOrdersTotalQuantity(orders: List<Order>) {
        val totalQuantity = orders.sumOf { order -> order.getQuantity() }
        require(totalQuantity <= 20) { Exception.INVALID_ORDER.getMessage() }
    }
}