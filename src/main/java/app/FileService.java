package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileService {

    private static final Pattern ALLOWED_CHARS_PATTERN = Pattern.compile("^[0-9\\s]*$");
    private static final String SPLIT_BY_SPACES_PATTERN = "\\s+";
    private final String inputPath;
    private final String outputPath;

    private String message;

    public FileService(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public FileService(String inputPath) {
        this(inputPath, null);
    }

    public String[] readFile() {
        if (inputPath == null) {
            message = Messages.ERROR_NO_INPUT_FILE_FOUND;
            return null;
        }
        File file = new File(inputPath);
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            message = Messages.ERROR_NO_INPUT_FILE_FOUND;
            return null;
        }
        bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String tmp;
        try {
            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuffer.append(tmp);
            }
        } catch (IOException e) {
            message = Messages.ERROR_FILE_READING;
            return null;
        }
        String content = stringBuffer.toString();
        if (content.length() == 0) {
            message = Messages.ERROR_FILE_EMPTY;
            return null;
        }
        if (!ALLOWED_CHARS_PATTERN.matcher(content).find()) {
            message = Messages.ERROR_FILE_NOT_ALLOWED_CONTENT;
            return null;
        }
        return content.split(SPLIT_BY_SPACES_PATTERN);
    }

    public boolean writeFile(String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputPath));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            message = Messages.ERROR_WRITING_FILE;
            return false;
        }
        return true;
    }

    public String getMessage() {
        return message;
    }

}
