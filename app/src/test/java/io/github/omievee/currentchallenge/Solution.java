package io.github.omievee.currentchallenge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
/*
 INSTRUCTION:
 ===========

 This is a smelly class, point out the things that smell.

 Additionally, implement the empty test - modify classes as necessary.



## --- First: what is sut? change variable name..
       -- Declare tax service outside of method to be used anywhere...
       -- move helper functions outside of classes.
       --
 */

public class Solution {
    private final TaxUtil taxUtil = new TaxUtil();
    private static final double DELTA = 0.0002;
    private static final String STATE = "NJ";
    private static final double SUB_TOTAL = 10.0;
    private static final double EXPECTED = 8.25;


//    public static void main(String[] args) {
//        JUnitCore.main("Solution");
//    }

    @Test
    public void testCalculateNyTaxAmount() {
        Assert.assertEquals(EXPECTED, taxUtil.calculateTaxAmount(STATE, SUB_TOTAL), DELTA);
    }

    @Test
    public void testCalculateWithDefaultTaxAmount() {
        //implement me
        //this should test calculateTaxAmount(...)
        //when it applies the DEFAULT_STATE_TAX
        Assert.assertEquals(EXPECTED, taxUtil.calculateTaxAmount(STATE, SUB_TOTAL), DELTA);
    }

    ////////////////////// START CODE HERE //////////////////
    public class TaxUtil {
        static final double DEFAULT_STATE_TAX = 0.375;
//        TaxUtil() {
//        }

        double calculateTaxAmount(String state, double subtotal) {
            try {
                if (isTaxFreeState(state)) {
                    return 0d;
                } else {
                    double rate = getRateByState(state);
                    return rate * subtotal;
                }
            } catch (Exception e) {
                return DEFAULT_STATE_TAX * subtotal;
            }
        }

        public double calculateBreakEvenPoint(int unitsSold, double costPerUnit, double fixedCost, double variableCost) {
            double sales = unitsSold * costPerUnit;
            return sales - fixedCost - variableCost;
        }

        public String getTaxOfficeLocation(String state) {
            return getLocation(state);
        }

        boolean isTaxFreeState(String state) {
            return false;
        }

        double getRateByState(String state) {
            return 0.825;
        }

        String getLocation(String state) {
            return "123 Whisky Way, NY 10065";
        }
    }

    //////////////////////// HELPER CLASSES //////////////////////

//    public static class TaxRateService {
//        static final double DEFAULT_STATE_TAX = 0.375;
//
//        public double getRateByState(String state) {
//            return 0.825;
//        }
//    }
//
//    public class TaxFreeStateUtil {
//        boolean isTaxFreeState(String state) {
//            return false;
//        }
//    }
//
//    public static class TaxOfficeLocator {
//        public static String getLocation(String state) {
//            return "123 Whisky Way, NY 10065";
//        }
//    }
}

//////////////////////// HELPER CLASSES //////////////////////

//    public class TaxRateService {
//        public static final double DEFAULT_STATE_TAX = 0.375;
//
//        public double getRateByState(String state) {
//            return 0.825;
//        }
//    }
//
//    public class TaxFreeStateUtil {
//        public boolean isTaxFreeState(String state) {
//            return false;
//        }
//    }
//
//    public static class TaxOfficeLocator {
//        static String getLocation(String state) {
//            return "123 Whisky Way, NY 10065";
//        }
//    }

