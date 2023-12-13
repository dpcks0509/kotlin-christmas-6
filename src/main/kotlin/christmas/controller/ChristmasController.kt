package christmas.controller

import christmas.model.GiveAway
import christmas.model.Menu.Companion.getMenuPriceByFood
import christmas.model.Order
import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        outputView.printWelcomeMessage()
        val visitDay = inputView.readVisitDay()
        val orders = inputView.readOrders()
        outputView.printVisitDay(visitDay)
        outputView.printOrders(orders)
        val totalOrderAmount = calculateTotalOrderAmount(orders)
        outputView.printTotalOrderAmount(totalOrderAmount)
        outputView.printGiveAway(GiveAway(totalOrderAmount))
    }

    private fun calculateTotalOrderAmount(orders: List<Order>): Int {
        return orders.sumOf { order ->
            getMenuPriceByFood(order.getFood()) * order.getQuantity()
        }
    }
}