package christmas.model

import christmas.util.Constants

class GiveAway(private val totalOrderAmount: Int) {
    fun getGiveAway() =
        "${Menu.CHAMPAGNE.getFood()} 1개".takeIf { totalOrderAmount >= Constants.GIVE_AWAY_MINIMUM_AMOUNT } ?: Constants.NO_BENEFIT

    fun getGiveAwayPrice() =
        Menu.CHAMPAGNE.getPrice().takeIf { totalOrderAmount >= Constants.GIVE_AWAY_MINIMUM_AMOUNT } ?: Constants.NO_DISCOUNT
}