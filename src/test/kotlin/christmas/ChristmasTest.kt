package christmas

import christmas.model.Badge.Companion.getBadgeByTotalBenefitAmount
import christmas.model.Calculator
import christmas.model.Discount
import christmas.model.GiveAway
import christmas.util.Validator.validateOrders
import christmas.util.Validator.validateVisitDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:8500", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000", delimiter = ':')
    fun `할인 전 총주문 금액 계산`(visitDate: Int, ordersString: String, expectTotalOrderAmount: Int) {
        val orders = validateOrders(ordersString)

        val actualTotalOrderAmount = Calculator(visitDate, orders).getTotalOrderAmount()

        assertThat(actualTotalOrderAmount).isEqualTo(expectTotalOrderAmount)
    }

    @ParameterizedTest
    @CsvSource("120000:샴페인 1개", "119999:없음", "142000:샴페인 1개", "8500:없음", delimiter = ':')
    fun `증정 메뉴 증정`(totalOrderAmount: Int, expectGiveAwayMenu: String) {
        val giveAway = GiveAway(totalOrderAmount)

        val actualGiveAwayMenu = giveAway.getMenu()

        assertThat(actualGiveAwayMenu).isEqualTo(expectGiveAwayMenu)
    }

    @ParameterizedTest
    @CsvSource(
        "26:타파스-1,제로콜라-1:0",
        "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:1200",
        "25:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:3400",
        delimiter = ':'
    )
    fun `크리스마스 디데이 할인`(visitDate: Int, ordersString: String, expectDDayDiscount: Int) {
        val orders = validateOrders(ordersString)

        val actualDDayDiscount = Discount(visitDate, orders).getDDayDiscount()

        assertThat(actualDDayDiscount).isEqualTo(expectDDayDiscount)
    }

    @ParameterizedTest
    @CsvSource(
        "26:타파스-1,제로콜라-1:0",
        "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:4046",
        "25:티본스테이크-1,바비큐립-1,초코케이크-1,제로콜라-1:2023",
        delimiter = ':'
    )
    fun `평일 할인 (일요일~목요일)`(visitDate: Int, ordersString: String, expectWeekDayDiscount: Int) {
        val orders = validateOrders(ordersString)

        val actualWeekDayDiscount = Discount(visitDate, orders).getWeekDayDiscount()

        assertThat(actualWeekDayDiscount).isEqualTo(expectWeekDayDiscount)
    }

    @ParameterizedTest
    @CsvSource(
        "26:타파스-1,제로콜라-1:0",
        "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:0",
        "22:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:4046",
        delimiter = ':'
    )
    fun `주말 할인 (금요일, 토요일)`(visitDate: Int, ordersString: String, expectWeekendDayDiscount: Int) {
        val orders = validateOrders(ordersString)

        val actualWeekendDayDiscount = Discount(visitDate, orders).getWeekendDayDiscount()

        assertThat(actualWeekendDayDiscount).isEqualTo(expectWeekendDayDiscount)
    }

    @ParameterizedTest
    @CsvSource(
        "26:타파스-1,제로콜라-1:0",
        "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:1000",
        "25:티본스테이크-1,바비큐립-1,초코케이크-1,제로콜라-1:1000",
        delimiter = ':'
    )
    fun `특별 할인`(visitDate: Int, ordersString: String, expectSpecialDayDiscount: Int) {
        val orders = validateOrders(ordersString)

        val actualSpecialDayDiscount = Discount(visitDate, orders).getSpecialDayDiscount()

        assertThat(actualSpecialDayDiscount).isEqualTo(expectSpecialDayDiscount)
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:0", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:31246", delimiter = ':')
    fun `총혜택 금액 계산`(visitDate: Int, ordersString: String, expectTotalBenefitAmount: Int) {
        val orders = validateOrders(ordersString)

        val actualTotalBenefitAmount = Calculator(visitDate, orders).getTotalBenefitAmount()

        assertThat(actualTotalBenefitAmount).isEqualTo(expectTotalBenefitAmount)
    }

    @ParameterizedTest
    @CsvSource("26:타파스-1,제로콜라-1:8500", "3:티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:135754", delimiter = ':')
    fun `할인 후 예상 결제 금액 계산`(visitDate: Int, ordersString: String, expectPaymentAmount: Int) {
        val orders = validateOrders(ordersString)

        val actualPaymentAmount = Calculator(visitDate, orders).getPaymentAmount()

        assertThat(actualPaymentAmount).isEqualTo(expectPaymentAmount)
    }

    @ParameterizedTest
    @CsvSource("20000:산타", "10000:트리", "5000:별", "3000:없음", delimiter = ':')
    fun `12월 이벤트 배지 부여`(totalBenefitAmount: Int, expectBadgeType: String) {
        val badge = getBadgeByTotalBenefitAmount(totalBenefitAmount)

        val actualBadgeType = badge.getType()

        assertThat(actualBadgeType).isEqualTo(expectBadgeType)
    }
}