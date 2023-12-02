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
        val benefit = Benefit(visitDate, orders)

        outputView.printBenefitPreview(visitDate)
        outputView.printOrders(orders)
        outputView.printTotalOrderAmount(benefit.getCalculator().getTotalOrderAmount())
        outputView.printGiveAwayMenu(benefit.getGiveAway().getMenu())
        outputView.printBenefitDetails(benefit)
        outputView.printTotalBenefitAmount(benefit.getCalculator().getTotalBenefitAmount())
        outputView.printPaymentAmount(benefit.getCalculator().getPaymentAmount())
        outputView.printBadgeType(benefit.getBadge().getType())
    }
}