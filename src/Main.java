import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String kundfil = "kunddata.txt";

        try {
            Kundinfo kundinfo = new Kundinfo(kundfil);
            System.out.println("Ange personnummer eller namn på kunden:");
            String input = scanner.nextLine();
            kundinfo.skrivUtKundstatus(input);
        } catch (IOException e) {
            System.out.println("Fel vid filhantering: " + e.getMessage());
        }
    }
}