package christmas.model

class Order(private val food: String, private val quantity: Int) {
    override fun toString(): String {
        return "$food ${quantity}개"
    }

    fun getFood() = food
    fun getQuantity() = quantity
}