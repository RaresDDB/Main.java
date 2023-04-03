package model;

public class Utilizator {
    private int id;
    private String nume;
    private String adresa;
    private String numarTelefon;
    private Card card;

    public Utilizator(int id, String nume, String adresa, String numarTelefon, Card card) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
        this.card = card;
    }

    public Utilizator(int id, String nume, String adresa, String numarTelefon) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                '}';
    }
}


