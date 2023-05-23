package model;

import java.util.ArrayList;
import java.util.List;

public class Sofer extends Utilizator {
    private String numarInmatriculare;
    private List<Comanda> comenzi;

    public Sofer(int id, String nume, String adresa, String numarTelefon, String numarInmatriculare) {
        super(id, nume, adresa, numarTelefon);
        this.numarInmatriculare = numarInmatriculare;
        this.comenzi = new ArrayList<>();
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public void adaugaComanda(Comanda comanda) {
        this.comenzi.add(comanda);
    }

    public void stergeComanda(Comanda comanda) {
        this.comenzi.remove(comanda);
    }

    @Override
    public String toString() {
        return "Sofer{" +
                "numarInmatriculare='" + numarInmatriculare + '\'' +
                ", comenzi=" + comenzi +
                '}';
    }
}

