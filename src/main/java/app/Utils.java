package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    public static String composeMultilineString(String... args) {
        if (args != null && args.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                stringBuffer.append(args[i]).append("\n");
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public static boolean noneArgViolates(Predicate<String> predicate, String... params) {
        if (params == null || params.length == 0) return false;
        return Arrays.stream(params).sequential().allMatch(predicate);
    }

    public static String getNumbersSequence(String[] numbers) {
        List<Long> inputBuffer = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            inputBuffer.add(Long.valueOf(numbers[i].trim()));
        }
        boolean isCountEven = inputBuffer.size() %2 == 0;
        return inputBuffer.stream()
                .filter(num -> (num %2 == 0 && isCountEven) || (num %2 != 0 && !isCountEven))
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

}

