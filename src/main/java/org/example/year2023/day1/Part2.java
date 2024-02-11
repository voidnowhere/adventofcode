package org.example.year2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inputs/2023/day1.txt"))) {
            String line;
            Map<String, String> digits = Map.of(
                    "one", "o1e",
                    "two", "t2o",
                    "three", "t3e",
                    "four", "f4r",
                    "five", "f5e",
                    "six", "s6x",
                    "seven", "s7n",
                    "eight", "e8t",
                    "nine", "n9e"
            );
            int result = 0;

            while ((line = reader.readLine()) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, String> e : digits.entrySet()) {
                    line = line.replaceAll(e.getKey(), e.getValue());
                }
                for (Map.Entry<String, String> e : digits.entrySet()) {
                    line = line.replaceAll("[a-z]", "");
                }

                for (char c : line.toCharArray()) {
                    stringBuilder.append(c);
                }

                if (!stringBuilder.isEmpty()) {
                    if (stringBuilder.length() < 2) {
                        stringBuilder.insert(1, stringBuilder.charAt(0));
                    } else if (stringBuilder.length() > 2) {
                        char c = stringBuilder.charAt(stringBuilder.length() - 1);
                        stringBuilder.setLength(2);
                        stringBuilder.setCharAt(1, c);
                    }
                    result += Integer.parseInt(stringBuilder.toString());
                }
            }

            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
