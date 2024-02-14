package org.example.year2023.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of("inputs/2023/day2.txt"))) {
            long result = stream.mapToLong(Part2::getGamePower).sum();
            System.out.println(result);
        }
    }

    private static long getGamePower(String game) {
        Matcher matcher = Pattern.compile("(\\d+)\s(blue|red|green)").matcher(game);

        Map<String, Integer> cubes = new HashMap<>();
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String color = matcher.group(2);
            cubes.merge(color, value, Integer::max);
        }

        return cubes
                .values()
                .stream()
                .reduce((v1, v2) -> v1 * v2)
                .orElse(0);
    }
}
