package christmas.view

import camp.nextstep.edu.missionutils.Console
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
}