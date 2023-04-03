package model;

import java.util.Date;

public class Plata {
    private int id;
    private Date data;
    private double valoare;
    private Card card;

    public Plata(int id, Date data, double valoare, Card card) {
        this.id = id;
        this.data = data;
        this.valoare = valoare;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public double getValoare() {
        return valoare;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "Plata{" +
                "id=" + id +
                ", data=" + data +
                ", valoare=" + valoare +
                ", card=" + card +
                '}';
    }
}

