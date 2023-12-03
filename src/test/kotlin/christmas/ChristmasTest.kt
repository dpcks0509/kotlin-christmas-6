package christmas

import christmas.util.Validator.validateVisitDate
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChristmasTest {

    @ParameterizedTest
    @ValueSource(strings = ["1", "25", "31"])
    fun `올바른 식당 예상 방문 날짜 입력`(visitDate: String) {
        assertDoesNotThrow { validateVisitDate(visitDate) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "32", "one", " 25", "25 ", " ", ""])
    fun `식당 예상 방문 날짜 입력 예외 처리 (방문할 날짜가 1 이상 31 이하의 숫자가 아닌 경우)`(visitDate: String) {
        assertThrows<IllegalArgumentException> { validateVisitDate(visitDate) }
    }
}