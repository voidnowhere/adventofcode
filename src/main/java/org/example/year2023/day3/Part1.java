package org.example.year2023.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part1 {
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d+");

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        int result = 0;
        try (Stream<String> stream = Files.lines(Path.of("inputs/2023/day3.txt"))) {
            stream.forEach(lines::add);
        }

        for (int i = 0, m = lines.size() - 1; i <= m; i++) {
            String current = lines.get(i);
            String previous = "";
            String next = "";
            int r;

            if (i > 0) {
                previous = lines.get(i - 1);
            }
            if (i < m) {
                next = lines.get(i + 1);
            }
            r = getLineResult(current, previous, next);

            result += r;
        }

        System.out.println(result);
    }

    private static int getLineResult(String current, String previous, String next) {
        int result = 0;
        int maxIndex = current.length() - 1;
        Matcher matcher = DIGIT_PATTERN.matcher(current);

        while (matcher.find()) {
            int start = matcher.start() - 1;
            int end = matcher.end();
            if ((start > 0 && current.charAt(start) != '.') ||
                    (end < maxIndex && current.charAt(end) != '.')
            ) {
                result += Integer.parseInt(matcher.group());
                continue;
            }
            for (int j = start; j <= end; j++) {
                if (j > 0 && j < maxIndex) {
                    if (!previous.isEmpty() && previous.charAt(j) != '.') {
                        result += Integer.parseInt(matcher.group());
                        break;
                    }
                    if (!next.isEmpty() && next.charAt(j) != '.') {
                        result += Integer.parseInt(matcher.group());
                        break;
                    }
                }
            }
        }

        return result;
    }
}
