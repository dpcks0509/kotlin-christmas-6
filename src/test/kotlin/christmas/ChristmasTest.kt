package christmas

import christmas.util.Validator.validateOrders
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

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"])
    fun `올바른 주문할 메뉴와 개수 입력`(orders: String) {
        assertDoesNotThrow { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["퀘사디아-1,제로콜라-1", "부리또-1,바비큐립-1,초코케이크-2,제로콜라-1"])
    fun `주문할 메뉴와 개수 입력 예외 처리 (메뉴판에 없는 메뉴를 입력한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-0,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-0,제로콜라-1"])
    fun `주문할 메뉴와 개수 입력 예외 처리 (메뉴의 개수가 1 이상의 숫자가 아닌 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스--1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크~2,제로콜라-1"])
    fun `주문할 메뉴와 개수 입력 예외 처리 (메뉴 형식이 예시와 다른 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,타파스-2", "티본스테이크-1,바비큐립-1,초코케이크-2,초코케이크-1"])
    fun `주문할 메뉴와 개수 입력 예외 처리 (중복 메뉴를 입력한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["제로콜라-1,제로콜라-2", "제로콜라-1,샴폐인-1"])
    fun `주문할 메뉴와 개수 입력 예외 처리 (음료만 주문한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-10,제로콜라-11", "티본스테이크-4,바비큐립-5,초코케이크-6,제로콜라-7"])
    fun `주문할 메뉴와 개수 입력 예외 처리 (메뉴의 총개수가 20개를 초과한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }
}