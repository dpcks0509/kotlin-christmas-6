package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.util.Validator.validateVisitDate

class InputView {
    fun readVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val visitDate = Console.readLine()
        return try {
            validateVisitDate(visitDate)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            readVisitDate()
        }
    }
}