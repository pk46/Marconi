import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    private static final String SEPARATOR = ", ";
    private static File file;
    private final DataHolder dataHolder;

    DataWriter(DataHolder dataHolder)
    {

        this.dataHolder = dataHolder;
    }

    public void printResult() {
        if (dataHolder.getResult().isEmpty()) {
            System.out.println("Kombinace zadaných čísel a jejich počet způsobila, že výsledek je prázdný.");
            GuiConsoleHelper.keepConsoleOpen();
        }
        dataHolder.getResult().forEach(System.out::println);
        GuiConsoleHelper.keepConsoleOpen();
    }

    protected static boolean isFilePath(String filePath) {
        if (filePath.contains(File.separator)) {
            file = new File(filePath);
        } else {
            String currentDirectory = System.getProperty("user.dir");
            file = new File(currentDirectory + File.separator + filePath);
        }
        return file.exists();
    }

    protected static String readFile() throws IOException {
        StringBuilder fileContent = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException ioe) {
            System.err.println("Načtení řádky ze vstupního souboru selhalo! " + ioe.getMessage());
        } finally {
            fileReader.close();
            bufferedReader.close();
        }
        return fileContent.toString();
    }

    protected void writeToFile(String outputFileName) {
        StringBuilder fileContent = new StringBuilder();
        File outputFile = new File(System.getProperty("user.dir") + File.separator + outputFileName);
        outputFile.getParentFile().mkdirs();

        for (int number : dataHolder.getResult()) {
            fileContent.append(number).append(SEPARATOR);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.flush();
            System.out.printf("Soubor %s byl úspěšně uložen.", outputFile);
            GuiConsoleHelper.keepConsoleOpen();
        } catch (Exception e) {
            System.err.println("Do souboru se nepovedlo zapsat." + e.getMessage());
            GuiConsoleHelper.keepConsoleOpen();
        }
    }

    protected void printHelp() {
        System.out.println("""
                ./program 4 | <input_file> [output_file]

                např.: ./program vstup.txt vystup.txt
                """);
    }
}
