package christmas.controller

import christmas.model.Badge.Companion.getBadgeTypeByTotalBenefitAmount
import christmas.model.Benefit
import christmas.model.Discount
import christmas.model.GiveAway
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
        val giveAway = GiveAway(benefit.getTotalOrderAmount())
        val discount = Discount(visitDate, orders)
        outputView.printTotalOrderAmount(benefit.getTotalOrderAmount())
        outputView.printGiveAway(giveAway.getGiveAway())
        outputView.printBenefits(discount, benefit,giveAway)
        outputView.printTotalBenefitAmount(benefit.getTotalBenefitAmount())
        outputView.printPaymentAmount(benefit.getPaymentAmount())
        outputView.printBadge(getBadgeTypeByTotalBenefitAmount(benefit.getTotalBenefitAmount()))
    }
}