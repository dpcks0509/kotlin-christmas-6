package christmas.utils

enum class Exception(private val message: String) {
    INVALID_VISIT_DAY("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않은 주문입니다.");

    fun getMessage() = "[ERROR] $message 다시 입력해 주세요."
}