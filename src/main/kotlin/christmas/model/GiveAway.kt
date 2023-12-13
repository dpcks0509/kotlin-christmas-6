package christmas.model

import christmas.utils.Constants.GIVEAWAY_MIN_AMOUNT
import christmas.utils.Constants.NO_BEFIT
import christmas.utils.Constants.ONE_CHAMPAGNE

class GiveAway(private val totalOrderAmount: Int) {
    override fun toString(): String {
        return ONE_CHAMPAGNE.takeIf { totalOrderAmount >= GIVEAWAY_MIN_AMOUNT } ?: NO_BEFIT
    }
}