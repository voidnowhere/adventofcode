package org.example.year2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inputs/2023/day1.txt"))) {
            String line;
            List<Integer> numbers = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                StringBuilder stringBuilder = new StringBuilder();

                for (char c : line.toCharArray()) {
                    if (Character.isDigit(c)) {
                        stringBuilder.append(c);
                    }
                }

                if (!stringBuilder.isEmpty()) {
                    if (stringBuilder.length() < 2) {
                        stringBuilder.insert(1, stringBuilder.charAt(0));
                    } else if (stringBuilder.length() > 2) {
                        char c = stringBuilder.charAt(stringBuilder.length() - 1);
                        stringBuilder.setLength(2);
                        stringBuilder.setCharAt(1, c);
                    }
                    numbers.add(Integer.valueOf(stringBuilder.toString()));
                }
            }

            System.out.println(numbers.stream().reduce(0, Integer::sum));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
