import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
    private static List<String> excluded = new ArrayList<>();
    private static boolean exclude = false;

    public static void setExcluded(List<String> excluded) {
        if (!exclude) { // dont change if already set
            Filter.excluded = excluded;
            Filter.exclude = true;
        }
    }

    /**
     * Check if a file or directory is excluded
     * 
     * @param file The file or directory to check
     * @return true if the file or directory is excluded, false otherwise
     */
    public static boolean excluded(File file) {
        return file.isDirectory() ? excludedDir(file) : excludedFile(file);
    }

    /**
     * Check if a directory is excluded
     * 
     * @param file The directory to check
     * @return true if the directory is excluded, false otherwise
     */
    private static boolean excludedDir(File file) {
        return excluded.contains(file.getName());
    }

    /**
     * 
     * Check if a file is excluded
     * 
     * @param file The file to check
     * @return true if the file is excluded, false otherwise
     */
    private static boolean excludedFile(File file) {
        return excluded.contains(getExtension(file.getName()));
    }

    /**
     * Extracts the extension from a file name
     * 
     * @param fileName The file name to extract the extension from
     * @return The extension of the file name
     */
    private static String getExtension(String fileName) {
        Matcher matcher = Pattern.compile("\\.(\\w+)").matcher(fileName);
        return matcher.find() ? "." + matcher.group(1) : "";
    }

    public static List<String> getInputList(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        String[] arr = input.split("\\s+");
        return Arrays.asList(arr);

    }
}
