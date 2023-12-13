package christmas

import christmas.model.Benefit
import christmas.model.Discount
import christmas.utils.Validator.validateOrders
import christmas.utils.Validator.validateVisitDay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ChristmasTest {
    @ParameterizedTest
    @ValueSource(strings = ["1", "3", "25", "31"])
    fun `올바른 식당 예상 방문 날짜 입력`(visitDay: String) {
        assertDoesNotThrow { validateVisitDay(visitDay) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "32", "one", " 1", "1 ", " ", ""])
    fun `식당 예상 방문 날짜 입력 예외 처리 (1 이상 31 이하의 숫자가 아닌 경우)`(visitDay: String) {
        assertThrows<IllegalArgumentException> { validateVisitDay(visitDay) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"])
    fun `올바른 주문 메뉴와 개수 입력`(orders: String) {
        assertDoesNotThrow { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파사-1,제로콜라-1", "타파스-1,제로코크-1"])
    fun `주문 메뉴와 개수 입력 예외 처리 (메뉴판에 없는 메뉴를 입력한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-0,제로콜라-1", "타파스-1,제로콜라-one"])
    fun `주문 메뉴와 개수 입력 예외 처리 (메뉴의 개수가 1 이상의 숫자가 아닌 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스--1,제로콜라-1", "타파스-1,제로콜라1"])
    fun `주문 메뉴와 개수 입력 예외 처리 (메뉴 형식이 예시와 다른 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,타파스-1", "제로콜라-1,제로콜라-1"])
    fun `주문 메뉴와 개수 입력 예외 처리 (중복 메뉴를 입력한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["레드와인-1", "제로콜라-1,제로콜라-1"])
    fun `주문 메뉴와 개수 입력 예외 처리 (음료만 주문한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-10,제로콜라-11", "티본스테이크-5,바비큐립-6,초코케이크-7,제로콜라-8"])
    fun `주문 메뉴와 개수 입력 예외 처리 (메뉴의 총개수가 20개를 초과한 경우)`(orders: String) {
        assertThrows<IllegalArgumentException> { validateOrders(orders) }
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:8500", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000" ,delimiter = ':')
    fun `할인 전 총주문 금액 계산`(visitDay: Int, inputOrders: String, expectTotalOrderAmount: Int) {
        val orders = validateOrders(inputOrders)
        val benefit = Benefit(visitDay, orders)

        val actualTotalOrderAmount = benefit.getTotalOrderAmount()

        assertThat(actualTotalOrderAmount).isEqualTo(expectTotalOrderAmount)
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:없음", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:샴페인 1개" ,delimiter = ':')
    fun `증정 메뉴 증정 판단`(visitDay: Int, inputOrders: String, expectGiveAway: String) {
        val orders = validateOrders(inputOrders)
        val benefit = Benefit(visitDay, orders)

        val actualGiveAway = benefit.getGiveAway().toString()

        assertThat(actualGiveAway).isEqualTo(expectGiveAway)
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:0", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:1200" ,delimiter = ':')
    fun `크리스마스 디데이 할인 계산`(visitDay: Int, inputOrders: String, expectDDayDiscount: Int) {
        val orders = validateOrders(inputOrders)
        val discount = Discount(visitDay, orders)

        val actualDDayDiscount = discount.getDDayDiscount()

        assertThat(actualDDayDiscount).isEqualTo(expectDDayDiscount)
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:0", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:4046" ,delimiter = ':')
    fun `평일 할인 계산`(visitDay: Int, inputOrders: String, expectWeekDayDiscount: Int) {
        val orders = validateOrders(inputOrders)
        val discount = Discount(visitDay, orders)

        val actualWeekDayDiscount = discount.getWeekDayDiscount()

        assertThat(actualWeekDayDiscount).isEqualTo(expectWeekDayDiscount)
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:0", "2:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:4046" ,delimiter = ':')
    fun `주말 할인 계산`(visitDay: Int, inputOrders: String, expectWeekendDayDiscount: Int) {
        val orders = validateOrders(inputOrders)
        val discount = Discount(visitDay, orders)

        val actualWeekendDayDiscount = discount.getWeekendDayDiscount()

        assertThat(actualWeekendDayDiscount).isEqualTo(expectWeekendDayDiscount)
    }
}