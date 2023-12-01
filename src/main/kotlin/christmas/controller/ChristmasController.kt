package christmas.controller

import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        outputView.printEventPlanner()
        val visitDate = inputView.readVisitDate()
        val orders = inputView.readOrders()
        outputView.printEventBenefits(visitDate)
    }
}