import java.time.LocalDate;

class Kund {
    private String personnummer;
    private String namn;
    private LocalDate senasteBetalning;

    public Kund(String personnummer, LocalDate senasteBetalning, String namn) {
        this.personnummer = personnummer;
        this.senasteBetalning = senasteBetalning;
        this.namn = namn;
    }


    public String getPersonnummer() {
        return personnummer;
    }

    public String getNamn() {
        return namn;
    }

    public boolean ärNuvarandeMedlem() {
        return senasteBetalning.isAfter(LocalDate.now().minusYears(1));
    }


}
