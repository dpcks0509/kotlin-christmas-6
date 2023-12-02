package christmas.model

enum class Badge(private val type: String, private val minimumBenefitAmount: Int) {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    companion object {
        fun getBadgeByTotalBenefitAmount(totalBenefitAmount: Int): Badge {
            return values().first { badge ->
                badge.minimumBenefitAmount <= totalBenefitAmount
            }
        }
    }

    fun getType() = type
}