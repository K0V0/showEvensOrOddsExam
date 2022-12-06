package app;

import static app.Predicates.isEmpty;
import static app.Predicates.isHelpCommand;
import static app.Predicates.isValidCommand;
import static app.Predicates.isValidNumber;
import static app.Utils.getNumbersSequence;
import static app.Utils.noneArgViolates;

public class App {

    private final String[] args;

    public App(String[] args) {
        this.args = args;
    }

    public String getResult() {
        if (args == null || args.length == 0 || noneArgViolates(isEmpty, args)) {
            return Messages.ERROR_NO_ARGUMENTS;
        } else if (args.length == 1 && noneArgViolates(isHelpCommand, args)) {
            return Messages.MANUAL;
        } else if (args.length == 1 && noneArgViolates(isValidCommand, args)) {
            // citanie zo suboru a vypis do konzoly
            FileService fileService = new FileService(args[0]);
            String[] values = fileService.readFile();
            if (values == null) return fileService.getMessage();
            if (!noneArgViolates(isValidNumber, values)) return Messages.ERROR_WRONG_ARGUMENTS;
            return getNumbersSequence(values);
        } else if (args.length == 2 && noneArgViolates(isValidCommand, args)) {
            // citanie zo suboru a zapis do suboru
            FileService fileService = new FileService(args[0], args[1]);
            String[] values = fileService.readFile();
            if (values == null) return fileService.getMessage();
            if (!noneArgViolates(isValidNumber, values)) return Messages.ERROR_WRONG_ARGUMENTS;
            if (!fileService.writeFile(getNumbersSequence(values))) return fileService.getMessage();
            return String.format("%s: %s", Messages.FILE_WRITE_DONE, args[1]);
        } else if (noneArgViolates(isValidNumber, args)) {
            // citanie z konzoly a vypis do konzoly
            return getNumbersSequence(args);
        }
        return Messages.ERROR_WRONG_ARGUMENTS;
    }

}
