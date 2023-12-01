package christmas.controller

import christmas.model.Benefit
import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController(
    private val inputView: InputView, private val outputView: OutputView
) {
    fun run() {
        outputView.printEventPlanner()
        val visitDate = inputView.readVisitDate()
        val orders = inputView.readOrders()
        outputView.printEventBenefits(visitDate)
        outputView.printOrders(orders)
        val benefit = Benefit(visitDate, orders)
        outputView.printTotalOrderAmount(benefit.getTotalOrderAmount())
        outputView.printGiveAway(benefit.getGiveAway())
    }
}