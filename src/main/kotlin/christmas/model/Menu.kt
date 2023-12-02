package christmas.model

import christmas.util.Constants.APPETIZER
import christmas.util.Constants.BEVERAGE
import christmas.util.Constants.DESSERT
import christmas.util.Constants.MAIN

enum class Menu(private val category: String, private val food: String, private val price: Int) {
    BUTTON_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6000),
    TAPAS(APPETIZER, "타파스", 5500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8000),

    T_BONE_STEAK(MAIN, "티본스테이크", 55000),
    BBQ_RIB(MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25000),

    CHOCO_CAKE(DESSERT, "초코케이크", 15000),
    ICE_CREAM(DESSERT, "아이스크림", 5000),

    ZERO_COLA(BEVERAGE, "제로콜라", 3000),
    RED_WINE(BEVERAGE, "레드와인", 60000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25000);

    companion object {
        fun getMenuCategoryByFood(food: String): String {
            return values().find { menu ->
                menu.getFood() == food
            }?.category!!
        }
    }

    fun getFood() = food
    fun getPrice() = price
}