package christmas.model

class Benefit(private val orders: List<Order>) {
    private var totalOrderAmount = 0

    init {
        calculateTotalOrderAmount()
    }

    private fun calculateTotalOrderAmount() {
        orders.forEach { order ->
            val orderAmount = calculateOrderAmount(order.getFood())
            totalOrderAmount += orderAmount * order.getQuantity()
        }
    }

    private fun calculateOrderAmount(food: String): Int {
        return Menu.entries.find { menu ->
            menu.getFood() == food
        }?.getPrice()!!
    }

    fun getTotalOrderAmount() = totalOrderAmount
}