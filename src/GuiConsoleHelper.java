import java.util.Scanner;

/**
 * Pomocná třída pro práci s konzolovou aplikací GUI způsobem.
 * Např na Windows spustím konzolovou aplikaci kliknutím a systém automaticky otevře terminál (příkazový řádek). Po skončení běhu programu se pak terminál automaticky ukončí, což znemožní přečtení výstupu, tedy chyby a/nebo výsledku.
 */
public class GuiConsoleHelper {

    public static void keepConsoleOpen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPro ukončení stiskněte klávesu ENTER.");
        scanner.nextLine();
        System.exit(0);
    }
}
