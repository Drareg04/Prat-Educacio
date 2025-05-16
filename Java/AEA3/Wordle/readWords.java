import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class readWords {
    public static String randomWord() {
        Path path = Paths.get("validwords.txt");
        Random randomNum = new Random();
        int targetLineNumber = randomNum.nextInt(681);
        try {
            Optional<String> line = Files.lines(path)
                    .skip(targetLineNumber - 1) // Skip lines before target
                    .findFirst();

            if (line.isPresent()) {
                return(line.get());
            } else {
                return("does not exist");
            }
        } catch (IOException e) {
            return("NULL");
        }
    }
    public static void copyWords(String) {
        Path inputPath = Paths.get("validWords.txt");
        Path outputPath = Paths.get("correctWords.txt");
        int startLine = 3; // Inclusive (1-based)
        int endLine = 5;   // Inclusive (1-based)

        try {
            // Read lines from startLine to endLine
            List<String> extractedLines = Files.lines(inputPath)
                .skip(startLine - 1)              // Skip lines before startLine
                .limit(endLine - startLine + 1)    // Limit to the desired range
                .collect(Collectors.toList());

            // Check if lines were actually extracted
            if (extractedLines.isEmpty()) {
                System.err.println("Invalid line range");
                return;
            }

            // Write to the output file
            Files.write(outputPath, extractedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}