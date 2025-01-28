package com.school.Springshigh.config;

import java.util.HashMap;
import java.util.Map;

public class CodeValidator {
    private static final Map<String, String> codeStore = new HashMap<>();

    public static boolean validate(String email, String code) {
        return codeStore.getOrDefault(email, "").equals(code);
    }

    public static void storeCode(String email, String code) {
        codeStore.put(email, code);
    }
}