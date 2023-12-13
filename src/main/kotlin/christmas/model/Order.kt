package christmas.model

class Order(private val food: String, private val quantity: Int) {
    fun getFood() = food
    fun getQuantity() = quantity
}