package org.example.year2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final Map<String, Integer> cubesLoad = Map.of("red", 12, "green", 13, "blue", 14);

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inputs/2023/day2.txt"))) {
            String line;
            Map<Integer, Boolean> results = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                Map.Entry<Integer, Boolean> result = checkIfGameIsPossible(line);
                results.put(result.getKey(), result.getValue());
            }
            System.out.println(results
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue())
                    .mapToInt(Map.Entry::getKey)
                    .sum());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map.Entry<Integer, Boolean> checkIfGameIsPossible(String game) {
        Matcher matcher = Pattern.compile("Game\s(\\d+)|(\\d+)\s(blue|red|green)").matcher(game);
        if (!matcher.find()) {
            return Map.entry(0, false);
        }
        int gameId = Integer.parseInt(matcher.group(1));

        while (matcher.find()) {
            //                   cube value                        cube color
            if (Integer.parseInt(matcher.group(2)) > cubesLoad.get(matcher.group(3))) {
                return Map.entry(gameId, false);
            }
        }

        return Map.entry(gameId, true);
    }
}
