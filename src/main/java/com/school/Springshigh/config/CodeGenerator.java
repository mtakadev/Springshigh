package com.school.Springshigh.config;

import java.util.Random;

public class CodeGenerator {
    public static String generateCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }
}
