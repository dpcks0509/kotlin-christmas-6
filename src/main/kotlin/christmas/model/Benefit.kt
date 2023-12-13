package christmas.model

class Benefit(private val visitDay: Int, private val orders: List<Order>) {
    private val totalOrderAmount = calculateTotalOrderAmount()
    private val giveAway = GiveAway(totalOrderAmount)
    private val discount = Discount(visitDay, orders)

    private fun calculateTotalOrderAmount(): Int {
        return orders.sumOf { order ->
            Menu.getMenuPriceByFood(order.getFood()) * order.getQuantity()
        }
    }

    fun getTotalOrderAmount() = totalOrderAmount
    fun getGiveAway() = giveAway
    fun getDiscount() = discount
}