import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Kundinfo {
    private List<Kund> kunder;

    public Kundinfo(String filnamn) throws IOException {
        kunder = new ArrayList<>();
        läsKunddataFrånFil(filnamn);
    }

    private void läsKunddataFrånFil(String filnamn) throws IOException {
        List<String> rader = Files.readAllLines(Paths.get(filnamn)); //Skapar en lista av strängar med alla rader.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Skapar en formatter för att välja rätt datumsformat

        for (int i = 0; i < rader.size(); i += 2) { // += 2 för att hoppa till rätt rad i filen
            String[] delar = rader.get(i).split(", ");  //Delar upp raden med personnummer och namn
            String personnummer = delar[0];
            String namn = delar[1];
            LocalDate betalningDatum = LocalDate.parse(rader.get(i + 1), formatter); //Parsar för att veta att det är ett datum.
            kunder.add(new Kund(personnummer, betalningDatum, namn)); //Lägger till en ny kund i arraylistan.
        }
    }

    public Kund sökKund(String input) {
        for (Kund kund : kunder) { //For each loop
            if (kund.getPersonnummer().equals(input) || kund.getNamn().equalsIgnoreCase(input)) {
                return kund;
            }
        }
        return null;
    }

    public void sparaTräningsbesök(Kund kund) throws IOException {
        String filnamn = "träning_logg.txt";
        String logg = kund.getNamn() + ", " + kund.getPersonnummer() + ", " + LocalDate.now() + "\n";
        Files.write(Paths.get(filnamn), logg.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void skrivUtKundstatus(String input) throws IOException {
        Kund kund = sökKund(input);
        if (kund == null) {
            System.out.println("Personen finns inte i systemet och har aldrig varit medlem.");
        } else if (kund.ärNuvarandeMedlem()) {
            System.out.println(kund.getNamn() + " är nuvarande medlem.");
            sparaTräningsbesök(kund);
        } else {
            System.out.println(kund.getNamn() + " är före detta medlem.");
        }
    }
}
