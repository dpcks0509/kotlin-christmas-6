package christmas

import christmas.utils.Validator.validateVisitDay
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChristmasTest {
    @ParameterizedTest
    @ValueSource(strings = ["1", "3", "25","31"])
    fun `올바른 식당 예상 방문 날짜 입력`(visitDay: String) {
        assertDoesNotThrow { validateVisitDay(visitDay) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "32","one", " 1", "1 ", " ", ""])
    fun `식당 예상 방문 날짜 입력 예외 처리 (1 이상 31 이하의 숫자가 아닌 경우)`(visitDay: String) {
        assertThrows<IllegalArgumentException> { validateVisitDay(visitDay) }
    }
}