package dev.diegofernando.mutationtest

class DeliveryFee (
    private var fee: Double,
    private var additionalFee: Double,
    private var isWithdrawal: Boolean,
    private var userPromotionalWeight: Double
) {

    private var finalFee : Double = this.fee

    private companion object {
        const val LIMIT_VALUE_FOR_DELIVERY_WITHOUT_ADDITIONAL_FEE: Double = 80.0
        const val MINIMUM_WEIGHT_TO_APPLY_PROMOTION_DISCOUNT: Double = 25.0
        const val PROMOTION_DISCOUNT_VALUE: Double = 5.0
    }

    fun applyAmount() {
        if (isWithdrawal){
            this.finalFee = 0.0
            return
        }

        if (this.fee <= LIMIT_VALUE_FOR_DELIVERY_WITHOUT_ADDITIONAL_FEE) {
            this.finalFee = this.fee + this.additionalFee
        }

        if (this.userPromotionalWeight >= MINIMUM_WEIGHT_TO_APPLY_PROMOTION_DISCOUNT) {
            this.finalFee = this.finalFee - PROMOTION_DISCOUNT_VALUE
        }
    }

    fun getFinalFeeWithIncrease(): Double {
        if (this.finalFee >= 1.0) return this.finalFee
        return 0.0
    }

}