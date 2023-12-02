package christmas.view

import christmas.model.*
import christmas.util.Constants.NONE
import java.text.DecimalFormat

class OutputView {
    fun printEventPlanner() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printBenefitPreview(visitDate: Int) {
        println("12월 ${visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrders(orders: List<Order>) {
        println()
        println("<주문 메뉴>")
        orders.forEach { order ->
            println("${order.getFood()} ${order.getQuantity()}개")
        }
    }

    fun printTotalOrderAmount(totalOrderAmount: Int) {
        println()
        println("<할인 전 총주문 금액>")
        println("${decimalFormat.format(totalOrderAmount)}원")
    }

    fun printGiveAwayMenu(giveAwayMenu: String) {
        println()
        println("<증정 메뉴>")
        println(giveAwayMenu)
    }

    fun printBenefitDetails(benefit: Benefit) {
        println()
        println("<혜택 내역>")
        printDiscounts(benefit.getDiscount())
        printGiveAwayPrice(benefit.getGiveAway().getPrice())
        printNoBenefit(benefit.getCalculator().getTotalBenefitAmount())
    }

    private fun printDiscounts(discount: Discount) {
        printDDayDiscount(discount.getDDayDiscount())
        printWeekDayDiscount(discount.getWeekDayDiscount())
        printWeekendDayDiscount(discount.getWeekendDayDiscount())
        printSpecialDayDiscount(discount.getSpecialDayDiscount())
    }

    private fun printDDayDiscount(dDayDiscount: Int) {
        if (dDayDiscount != 0) println("크리스마스 디데이 할인: -${decimalFormat.format(dDayDiscount)}원")
    }

    private fun printWeekDayDiscount(weekDayDiscount: Int) {
        if (weekDayDiscount != 0) println("평일 할인: -${decimalFormat.format(weekDayDiscount)}원")
    }

    private fun printWeekendDayDiscount(weekendDayDiscount: Int) {
        if (weekendDayDiscount != 0) println("주말 할인: -${decimalFormat.format(weekendDayDiscount)}원")
    }

    private fun printSpecialDayDiscount(specialDayDiscount: Int) {
        if (specialDayDiscount != 0) println("특별 할인: -${decimalFormat.format(specialDayDiscount)}원")
    }

    private fun printGiveAwayPrice(giveAwayPrice: Int) {
        if (giveAwayPrice != 0) println("증정 이벤트: -${decimalFormat.format(giveAwayPrice)}원")
    }

    private fun printNoBenefit(totalBenefitAmount: Int) {
        if (totalBenefitAmount == 0) println(NONE)
    }

    fun printTotalBenefitAmount(totalBenefitAmount: Int) {
        println()
        println("<총혜택 금액>")
        if (totalBenefitAmount == 0) println("${decimalFormat.format(totalBenefitAmount)}원")
        if (totalBenefitAmount != 0) println("-${decimalFormat.format(totalBenefitAmount)}원")
    }

    fun printPaymentAmount(paymentAmount: Int) {
        println()
        println("<할인 후 예상 결제 금액>")
        println("${decimalFormat.format(paymentAmount)}원")
    }

    fun printBadgeType(badgeType: String) {
        println()
        println("<12월 이벤트 배지>")
        println(badgeType)
    }

    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }
}