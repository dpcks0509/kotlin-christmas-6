package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Order
import christmas.utils.Validator.validateOrders
import christmas.utils.Validator.validateVisitDay

class InputView {
    fun readVisitDay(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val visitDay = Console.readLine()
        return try {
            validateVisitDay(visitDay)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            readVisitDay()
        }
    }

    fun readOrders(): List<Order> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val orders = Console.readLine()
        return try {
            validateOrders(orders)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            readOrders()
        }
    }
}