package christmas.view

import christmas.model.Order
import java.text.DecimalFormat

class OutputView {
    fun printEventPlanner() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printEventBenefits(visitDate: Int) {
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

    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }
}