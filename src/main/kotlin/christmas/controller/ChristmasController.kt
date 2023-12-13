package christmas.controller

import christmas.model.Benefit
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
        val benefit = Benefit(visitDay, orders)
        outputView.printTotalOrderAmount(benefit.getTotalOrderAmount())
        outputView.printGiveAway(benefit.getGiveAway())
        outputView.printBenefit(benefit)
        outputView.printTotalBenefitAmount(benefit.getTotalBenefitAmount())
        outputView.printPaymentAmount(benefit.getPaymentAmount())
    }
}