import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {

    private enum InputType {
        NUMBER,
        FILE
    }

    private static final String SEPARATOR = ",";
    private final Scanner userInputScanner = new Scanner(System.in, StandardCharsets.UTF_8);
    private final DataHolder dataHolder;
    private int[] inputNumbers;

    DataReader(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    private String determineInputType(String countOrFile) {
        try {
            Integer.parseInt(countOrFile);
            return InputType.NUMBER.toString();
        } catch (NumberFormatException nfe) {
            return InputType.FILE.toString();
        }
    }

    protected void readInputFromStandardInputOrFile(String countOrFile) throws IOException {
        if (determineInputType(countOrFile).equals(InputType.NUMBER.toString())) {
            readFromStandardInput(countOrFile);
        } else {
            readFromFile(countOrFile);
        }
    }

    private int readInput(int index) {
        int number;
        while (true) {
            try {
                System.out.printf("Zadejte %d. číslo: ", index+1);
                number = userInputScanner.nextInt();
                break;
            } catch (InputMismatchException ime) {
                System.err.println("Zadaná hodnota není číslo!");
                userInputScanner.nextLine();
            }
        }
        return number;
    }

    private void readFromStandardInput(String count) {
        int intCount = Integer.parseInt(count);
        try {
            inputNumbers = new int[intCount];
        } catch (NegativeArraySizeException nase) {
            System.err.println("Parametr musí být kladné celé číslo! Vy jste zadali: " + nase.getMessage());
            GuiConsoleHelper.keepConsoleOpen();
        }

        for (int i = 0; i < intCount; i++) {
            int number = readInput(i);
            inputNumbers[i] = number;
            }
    }

    private void parseNumbersFromString(String numbers) {
        inputNumbers = Arrays.stream(numbers.split(SEPARATOR))
                .map(String::trim)
                .filter(str -> {
                    try {
                        Integer.parseInt(str);
                        return true;
                    } catch (NumberFormatException nfe) {
                        return false;
                    }
                })
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private void readFromFile(String filePath) throws IOException {
        if (DataWriter.isFilePath(filePath)) {
            String stringNumbers = DataWriter.readFile();
            parseNumbersFromString(stringNumbers);
        } else {
            System.err.println("Neplatná cesta k souboru! " + filePath);
            GuiConsoleHelper.keepConsoleOpen();
        }
    }

    protected void getOddOrEvenNumbersBasedOnInputLength() {
        if (inputNumbers == null) {
            return;
        }

        for (int number : inputNumbers) {
            if (inputNumbers.length % 2 == 0) {
                if (number % 2 == 0) {
                    dataHolder.result.add(number);
                }
            } else {
                if (number % 2 != 0) {
                   dataHolder.result.add(number);
                }
            }
        }
    }

}
