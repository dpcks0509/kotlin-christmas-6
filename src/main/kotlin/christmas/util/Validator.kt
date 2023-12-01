package christmas.util

import christmas.model.Menu
import christmas.model.Order
import christmas.util.Constants.DATE_END
import christmas.util.Constants.DATE_START

object Validator {
    fun validateVisitDate(visitDate: String): Int {
        require(visitDate.toIntOrNull() in DATE_START..DATE_END) { Exception.INVALID_VISIT_DATE.getMessage() }
        return visitDate.toInt()
    }

    fun validateOrders(orders: String): List<Order> {
        val validOrders = orders.split(",").map { order -> validateOrder(order) }
        validateOrdersDuplicate(validOrders)
        validateOrdersCategory(validOrders)
        validateOrdersTotalQuantity(validOrders)
        return validOrders
    }

    private fun validateOrder(order: String): Order {
        val validOrder = order.split("-")
        validateOrderFormat(validOrder)
        val validOrderFood = validateOrderFood(validOrder[0])
        val validOrderQuantity = validateOrderQuantity(validOrder[1])
        return Order(validOrderFood, validOrderQuantity)
    }

    private fun validateOrderFormat(order: List<String>) {
        require(order.size == 2) { Exception.INVALID_ORDER.getMessage() }
    }

    private fun validateOrderFood(food: String): String {
        val menuFoods = Menu.entries.map { menu -> menu.getFood() }
        require(menuFoods.contains(food)) { Exception.INVALID_ORDER.getMessage() }
        return food
    }

    private fun validateOrderQuantity(quantity: String): Int {
        val validQuantity = quantity.toIntOrNull()
        requireNotNull(validQuantity) { Exception.INVALID_ORDER.getMessage() }
        require(validQuantity >= 1) { Exception.INVALID_ORDER.getMessage() }
        return validQuantity
    }

    private fun validateOrdersDuplicate(orders: List<Order>) {
        val orderFoods = orders.map { order -> order.getFood() }
        require(orders.size == orderFoods.size) { Exception.INVALID_ORDER.getMessage() }
    }

    private fun validateOrdersCategory(orders: List<Order>) {
        val orderCategories = orders.map { order ->
            Menu.values().find { menu ->
                menu.getFood() == order.getFood()
            }?.getCategory()
        }.distinct()
        require(!(orderCategories.size == 1 && orderCategories.contains("음료"))) { Exception.INVALID_ORDER.getMessage() }
    }

    private fun validateOrdersTotalQuantity(orders: List<Order>) {
        val totalQuantity = orders.sumOf { order -> order.getQuantity() }
        require(totalQuantity <= 20) { Exception.INVALID_ORDER.getMessage() }
    }
}