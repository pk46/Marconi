import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataHolder dataHolder = new DataHolder();
        DataReader dataReader = new DataReader(dataHolder);
        DataWriter dataWriter = new DataWriter(dataHolder);

        switch (args.length) {
            case 0 -> {
                System.out.println("Program spusťte alespoň s jedním parametrem.\n");
                dataWriter.printHelp();
            }
            case 1 -> {
                dataReader.readInputFromStandardInputOrFile(args[0]);
                dataReader.getOddOrEvenNumbersBasedOnInputLength();
                dataWriter.printResult();
            }
            case 2 -> {
                dataReader.readInputFromStandardInputOrFile(args[0]);
                dataReader.getOddOrEvenNumbersBasedOnInputLength();
                dataWriter.writeToFile(args[1]);
            }
            default -> {
                System.out.println("Spouštíte program s příliš mnoha parametry! Můžete použit jeden nebo dva parametry, " +
                        "které jsou oddělené mezerou.");
                dataWriter.printHelp();
                GuiConsoleHelper.keepConsoleOpen();
            }
        }
    }
}
