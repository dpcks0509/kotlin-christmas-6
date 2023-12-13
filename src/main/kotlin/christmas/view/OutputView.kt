package christmas.view

import christmas.model.Benefit
import christmas.model.Discount
import christmas.model.GiveAway
import christmas.model.Order
import christmas.utils.Constants.NO_BEFIT
import java.text.DecimalFormat

class OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printVisitDay(visitDay: Int) {
        println("12음월 ${visitDay}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrders(orders: List<Order>) {
        println()
        println("<주문 메뉴>")
        orders.forEach { order -> println(order) }
    }

    fun printTotalOrderAmount(totalOrderAmount: Int) {
        println()
        println("<할인 전 총주문 금액>")
        println("${decimalFormat.format(totalOrderAmount)}원")
    }

    fun printGiveAway(giveAway: GiveAway) {
        println()
        println("<증정 메뉴>")
        println(giveAway)
    }

    fun printBenefit(benefit: Benefit) {
        println()
        println("<혜택 내역>")
        printDiscount(benefit.getDiscount())
        printGiveAwayPrice(benefit.getGiveAway().getPrice())
        printNoBenefit(benefit.getTotalBenefitAmount())
    }

    private fun printDiscount(discount: Discount) {
        if (discount.getDDayDiscount() != 0) println("크리스마스 디데이 할인: -${decimalFormat.format(discount.getDDayDiscount())}원")
        if (discount.getWeekDayDiscount() != 0) println("평일 할인: -${decimalFormat.format(discount.getWeekDayDiscount())}원")
        if (discount.getWeekendDayDiscount() != 0) println("주말 할인: -${decimalFormat.format(discount.getWeekendDayDiscount())}원")
        if (discount.getSpecialDayDiscount() != 0) println("특별 할인: -${decimalFormat.format(discount.getSpecialDayDiscount())}원")
    }

    private fun printGiveAwayPrice(giveAwayPrice: Int) {
        if (giveAwayPrice != 0) println("증정 이벤트: -${decimalFormat.format(giveAwayPrice)}원")
    }

    private fun printNoBenefit(totalBenefitAmount: Int) {
        if (totalBenefitAmount == 0) println(NO_BEFIT)
    }

    fun printTotalBenefitAmount(totalBenefitAmount: Int) {
        println()
        println("<총혜택 금액>")
        if (totalBenefitAmount == 0) println("${totalBenefitAmount}원")
        if (totalBenefitAmount != 0) println("-${decimalFormat.format(totalBenefitAmount)}원")
    }

    fun printPaymentAmount(paymentAmount: Int) {
        println()
        println("<할인 후 예상 결제 금액>")
        println("${decimalFormat.format(paymentAmount)}원")
    }

    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }
}