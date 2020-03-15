package io.github.omievee.currentchallenge

import org.junit.Assert
import org.junit.Test

/*
 INSTRUCTION:
 ===========

 This is a smelly class, point out the things that smell.

 Additionally, implement the empty test - modify classes as necessary.

## --- First: what is sut? change variable name..
       -- Declare tax service outside of method to be used anywhere...
       -- move helper functions inside of classes.
       -- unnecessary try/catch
       -- simple check for State based on default rate (no need for two functions)
 */
class SolutionKT {
    private val taxUtil = TaxUtil("NY")

    @Test
    fun testCalculateTaxAmount() {
        Assert.assertEquals(
            EXPECTED,
            taxUtil.calculateTaxAmount(
                STATE,
                SUB_TOTAL
            ),
            DELTA
        )
    }
    ////////////////////// START CODE HERE //////////////////
    inner class TaxUtil(state: String) {
        private var DEFAULT_STATE_TAX: Double = 0.0

        init {
            DEFAULT_STATE_TAX = when (state == STATE) {
                true -> NY_STATE_TAX
                false -> OTHER_STATE_TAX
            }
        }

        fun calculateTaxAmount(state: String, subtotal: Double): Double {
            return if (isTaxFreeState(state)) {
                0.0
            } else {
                DEFAULT_STATE_TAX * subtotal
            }
        }

        fun calculateBreakEvenPoint(
            unitsSold: Int,
            costPerUnit: Double,
            fixedCost: Double,
            variableCost: Double
        ): Double {
            val sales = unitsSold * costPerUnit
            return sales - fixedCost - variableCost
        }

        fun getTaxOfficeLocation(state: String): String {
            return getLocation(state)
        }

        private fun isTaxFreeState(state: String): Boolean {
            return false
        }

        private fun getLocation(state: String): String {
            return "123 Whisky Way, NY 10065"
        }

    }

    companion object {
        private const val DELTA = 1e-3
        private const val STATE = "NY"
        private const val SUB_TOTAL = 10.0
        private const val EXPECTED = 3.75
        private const val NY_STATE_TAX = 0.875
        private const val OTHER_STATE_TAX = 0.375

    }
}