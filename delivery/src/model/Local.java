package model;

public class Local implements Comparable<Local> {
    private String nume;
    private String adresa;
    private Meniu meniu;

    public Local(String nume, String adresa, Meniu meniu) {
        this.nume = nume;
        this.adresa = adresa;
        this.meniu = meniu;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public Meniu getMeniu() {
        return meniu;
    }

    @Override
    public String toString() {
        return "Local{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", meniu=" + meniu +
                '}';
    }

    @Override
    public int compareTo(Local other) {
        return this.nume.compareTo(other.nume);
    }
}
