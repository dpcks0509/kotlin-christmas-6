package christmas.view

import christmas.model.GiveAway
import christmas.model.Order
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

    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }
}