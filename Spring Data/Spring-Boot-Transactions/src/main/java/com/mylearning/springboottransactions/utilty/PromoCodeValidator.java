package com.mylearning.springboottransactions.utilty;

import java.util.Arrays;
import java.util.List;

public class PromoCodeValidator {

    public static boolean validatePromoCode(String promoCode) {
        List<String> promoCodes = Arrays.asList("HappyFestival2024", "HappyDiwali2024", "HappyChatPuja2024","HappyNewYearWeek2025");
        if (promoCodes.contains(promoCode)) {
            return true;
        }
        return false;
    }
}
