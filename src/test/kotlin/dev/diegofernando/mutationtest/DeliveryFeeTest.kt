package dev.diegofernando.mutationtest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeliveryFeeTest {

    companion object {
        private const val FEE_FOR_MINOR_PURCHASES: Double = 0.99
    }

    @Test
    fun `Delivery Fee increase when the order value is minor` () {
        val fee = DeliveryFee(70.0, FEE_FOR_MINOR_PURCHASES, false, 0.0)
        fee.applyAmount()
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals( 70.99, actual)
    }

    @Test
    fun `Delivery Fee dont increase when the order value is high` () {
        val fee = DeliveryFee(100.0, FEE_FOR_MINOR_PURCHASES, false, 0.0)
        fee.applyAmount()
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(100.0, actual)
    }

    @Test
    fun `Delivery Fee zero when the order is of the withdrawal type` () {
        val fee = DeliveryFee(95.0, FEE_FOR_MINOR_PURCHASES, true, 0.0)
        fee.applyAmount()
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(0.0, actual)
    }

    @Test
    fun `Delivery Fee with discount when the user promotion weight is high` () {
        val fee = DeliveryFee(95.0, FEE_FOR_MINOR_PURCHASES, false, 42.0)
        fee.applyAmount()
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(90.0, actual)
    }

    //////
    @Test
    fun `Delivery Fee increase when the order value is equal`() {
        val fee = DeliveryFee(80.0, FEE_FOR_MINOR_PURCHASES, false, 0.0)
        fee.applyAmount()
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(80.99, actual)
    }

    @Test
    fun `Delivery Fee with discount when the user promotion weight is equal` () {
        val fee = DeliveryFee(95.0, FEE_FOR_MINOR_PURCHASES, false, 25.0)
        fee.applyAmount()
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(90.00, actual)
    }

    @Test
    fun `Delivery Fee less than zero` () {
        val fee = DeliveryFee(-30.0, FEE_FOR_MINOR_PURCHASES, false, 0.0)
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(0.0, actual)
    }

    @Test
    fun `Delivery Fee equal zero` () {
        val fee = DeliveryFee(0.0, FEE_FOR_MINOR_PURCHASES, false, 0.0)
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(0.0, actual)
    }

    @Test
    fun `Delivery greater than zero` () {
        val fee = DeliveryFee(1.0, FEE_FOR_MINOR_PURCHASES, false, 0.0)
        val actual = fee.getFinalFeeWithIncrease()
        Assertions.assertEquals(1.0, actual)
    }

}