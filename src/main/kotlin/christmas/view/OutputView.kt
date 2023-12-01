package christmas.view

import christmas.model.Order

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
}