package app;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Predicates {

    private static final String HELP_COMMAND_1 = "-h";
    private static final String HELP_COMMAND_2 = "--help";
    private static final Pattern NUM_PATTERN = Pattern.compile("^[0-9]*$");
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^.*[a-zA-Z]+.*$");

    public static final Predicate<String> isValidNumber = s ->
            s != null && s.trim().length() != 0 && NUM_PATTERN.matcher(s).find();

    public static final Predicate<String> isValidCommand = s ->
            s != null && s.trim().length() != 0 && COMMAND_PATTERN.matcher(s).find();

    public static final Predicate<String> isHelpCommand = s ->
            isValidCommand.test(s) && (s.equalsIgnoreCase(HELP_COMMAND_1) || s.equalsIgnoreCase(HELP_COMMAND_2));

    public static final Predicate<String> isEmpty = s ->
            s == null || s.trim().length() == 0;
}
