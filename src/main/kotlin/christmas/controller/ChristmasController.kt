package christmas.controller

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
    }
}